package com.techsukras.mcqapplication.service;


import com.techsukras.mcqapplication.dto.MCQDto;
import com.techsukras.mcqapplication.dto.TopicDto;
import com.techsukras.mcqapplication.entities.MCQ;
import com.techsukras.mcqapplication.entities.Standard;
import com.techsukras.mcqapplication.entities.Subject;
import com.techsukras.mcqapplication.entities.Topic;
import com.techsukras.mcqapplication.exceptions.StandardNotFoundException;
import com.techsukras.mcqapplication.exceptions.StudentNotFoundException;
import com.techsukras.mcqapplication.exceptions.SubjectNotFoundException;
import com.techsukras.mcqapplication.exceptions.TopicNotFoundException;
import com.techsukras.mcqapplication.repositories.*;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TopicService {

    private TopicRepository topicRepository;

    private SubjectRepository subjectRepository;

    private StandardRepository standardRepository;

    private StudentRepository studentRepository;

    private McqService mcqService;

    private McqRepository mcqRepository;

    private ModelMapper modelMapper;

    public TopicDto addTopic(TopicDto dto){
        Topic topic = this.modelMapper.map(dto, Topic.class);
        Subject subject = this.subjectRepository.findById(dto.getSubjectId()).
                orElseThrow(() -> new TopicNotFoundException("Topic not found!!!"));
        Standard standard = this.standardRepository.findById(dto.getStandardId())
                .orElseThrow(() -> new StandardNotFoundException("Standard not found!!!"));
        topic.setStandard(standard);
        topic.setSubject(subject);
        Topic saved = this.topicRepository.save(topic);
        return this.modelMapper.map(saved, TopicDto.class);
    }

    public List<String> getTopicTitles(Long subId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long stdId = this.studentRepository.findByEmail(email).orElseThrow(() -> new StudentNotFoundException("Student not found exception")).getStandard().getStdId();
        List<Topic> topics = this.topicRepository.findAllBySubject_SubIdAndStandard_StdId(subId, stdId);
        return topics.stream().map(Topic::getTopicTitle).toList();
    }

    public TopicDto addTopicToSubject(TopicDto dto){
        Subject subject = this.subjectRepository.findByStandardIdAndSubjectId(dto.getStandardId(), dto.getSubjectId())
                        .orElseThrow(() -> new SubjectNotFoundException("Subject Not found with the given standard id and subject id"));
        Standard standard = this.standardRepository.findById(dto.getStandardId())
                        .orElseThrow(() -> new StandardNotFoundException("Standard with the given Id not found"));
        Topic topic = new Topic();
        topic.setTopicTitle(dto.getTopicTitle());
        topic.setSubject(subject);
        topic.setStandard(standard);
        Topic saved = this.topicRepository.save(topic);

        Long topicId = saved.getTopicId();
        Set<MCQDto> mcqDtos = dto.getQuestions().stream().map((question) -> this.mcqService.addMcqToTopic(question, topicId)).collect(Collectors.toSet());
        Set<MCQ> questions = this.mcqRepository.findAllByTopic_TopicId(saved.getTopicId());

        saved.setQuestions(questions);
        saved = this.topicRepository.save(saved);

        TopicDto topicDto = this.modelMapper.map(saved, TopicDto.class);
        topicDto.setSubjectId(saved.getSubject().getSubId());
        topicDto.setStandardId(saved.getStandard().getStdId());
        topicDto.setQuestions(mcqDtos);
        return topicDto;
    }


    public TopicDto getTopicById(Long id){
        Topic topic = this.topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic with the given Id not found!!!"));
        return this.modelMapper.map(topic, TopicDto.class);
    }

    public List<TopicDto> getTopicsBySubjectIdAndStandardId(Long subjectId){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Long stdId = this.studentRepository.findByEmail(email).orElseThrow(() -> new StudentNotFoundException("Student not found exception")).getStandard().getStdId();
        List<Topic> topics = this.topicRepository.findAllBySubject_SubIdAndStandard_StdId(subjectId, stdId);

        return topics.stream().map((topic) -> this.modelMapper.map(topic, TopicDto.class)).toList();
    }

    public TopicDto updateTopic(Topic topic, Topic mapped){
        topic.setTopicTitle(mapped.getTopicTitle());
        topic.setQuestions(mapped.getQuestions());
        topic.setSubject(mapped.getSubject());
        topic.setStandard(mapped.getStandard());
        Topic updated = this.topicRepository.save(topic);
        return this.modelMapper.map(updated, TopicDto.class);
    }

    public TopicDto updateTopicByTopicId(Long id, TopicDto dto){
        Topic topic = this.topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic with the given topic id not found!!!"));
        Topic mapped = this.modelMapper.map(dto, Topic.class);
        return updateTopic(topic, mapped);
    }

    public TopicDto updateTopicBySubjectIdAndStandardId(Long subjectId, Long standardId, TopicDto dto){
        List<Topic> topics = this.topicRepository.findAllBySubject_SubIdAndStandard_StdId(subjectId, standardId);
        Topic currentTopic = topics.stream().filter((topic) -> topic.getTopicId().equals(dto.getTopicId())).findFirst()
                            .orElseThrow(() -> new TopicNotFoundException("Topic with the given topic id not found!!!"));
        Topic mapped = this.modelMapper.map(dto, Topic.class);
        return updateTopic(currentTopic, mapped);
    }

    public Boolean deleteTopic(Long id){
        this.topicRepository.deleteById(id);
        return true;
    }

}

