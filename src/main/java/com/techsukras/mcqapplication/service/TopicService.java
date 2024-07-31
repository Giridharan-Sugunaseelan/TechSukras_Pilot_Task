package com.techsukras.mcqapplication.service;


import com.techsukras.mcqapplication.dto.TopicDto;
import com.techsukras.mcqapplication.entities.Standard;
import com.techsukras.mcqapplication.entities.Subject;
import com.techsukras.mcqapplication.entities.Topic;
import com.techsukras.mcqapplication.exceptions.StandardNotFoundException;
import com.techsukras.mcqapplication.exceptions.SubjectNotFoundException;
import com.techsukras.mcqapplication.exceptions.TopicNotFoundException;
import com.techsukras.mcqapplication.repositories.StandardRepository;
import com.techsukras.mcqapplication.repositories.SubjectRepository;
import com.techsukras.mcqapplication.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {

    private TopicRepository topicRepository;

    private SubjectRepository subjectRepository;

    private StandardRepository standardRepository;

    private ModelMapper modelMapper;

    public TopicDto addTopic(TopicDto dto){
        Topic mapped = this.modelMapper.map(dto, Topic.class);
        Topic saved = this.topicRepository.save(mapped);
        return this.modelMapper.map(saved, TopicDto.class);
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
        // add method to add questions to topic
        Topic saved = this.topicRepository.save(topic);
        TopicDto topicDto = this.modelMapper.map(saved, TopicDto.class);
        topicDto.setSubjectId(saved.getSubject().getSubId());
        topicDto.setStandardId(saved.getStandard().getStdId());
        //add method to add questionDtos
        return topicDto;
    }

    public TopicDto getTopicById(Long id){
        Topic topic = this.topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic with the given Id not found!!!"));
        return this.modelMapper.map(topic, TopicDto.class);
    }

    public List<TopicDto> getTopicsBySubjectIdAndStandardId(Long subjectId, Long standardId){
        List<Topic> topics = this.topicRepository.findAllBySubjectIdAndStandardId(subjectId, standardId);
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
        List<Topic> topics = this.topicRepository.findAllBySubjectIdAndStandardId(subjectId, standardId);
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

