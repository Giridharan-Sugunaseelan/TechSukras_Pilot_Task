package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.MCQDto;
import com.techsukras.mcqapplication.dto.OptionDto;
import com.techsukras.mcqapplication.dto.TopicDto;
import com.techsukras.mcqapplication.entities.MCQ;
import com.techsukras.mcqapplication.entities.Option;
import com.techsukras.mcqapplication.entities.Topic;
import com.techsukras.mcqapplication.exceptions.McqNotFoundException;
import com.techsukras.mcqapplication.exceptions.TopicNotFoundException;
import com.techsukras.mcqapplication.repositories.McqRepository;
import com.techsukras.mcqapplication.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class McqService {

    private McqRepository mcqRepository;

    private TopicRepository topicRepository;

    private OptionService optionService;

    private ModelMapper modelMapper;

    public MCQDto addMcq(MCQDto dto){
        MCQ mapped = this.modelMapper.map(dto, MCQ.class);
        MCQ saved = this.mcqRepository.save(mapped);
        return this.modelMapper.map(saved, MCQDto.class);
    }

    public MCQDto addMcqToTopic(MCQDto dto, Long topicId){
        Topic topic = this.topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic with the given id not found"));
        MCQ newMcq = this.modelMapper.map(dto, MCQ.class);
        newMcq.setTopic(topic);
        MCQ saved = this.mcqRepository.save(newMcq);

        Set<Option> options = this.optionService.addOptionsToMcq(dto, saved.getMcqId());
        saved.setOptions(options);
        saved = this.mcqRepository.save(saved);

        MCQDto mapped = this.modelMapper.map(saved, MCQDto.class);
        Set<OptionDto> optionDtos = options.stream()
                .map((option) -> this.modelMapper.map(option, OptionDto.class)).collect(Collectors.toSet());
        TopicDto topicDto = this.modelMapper.map(topic, TopicDto.class);
        mapped.setTopic(topicDto);
        mapped.setOptions(optionDtos);
        return mapped;
    }

    public MCQDto getMcq(Long id){
        MCQ mcq = this.mcqRepository.findById(id).orElseThrow(() -> new McqNotFoundException("MCQ with the given id not found!!!"));
        return this.modelMapper.map(mcq, MCQDto.class);
    }

    public Set<MCQDto> getAllMcqByTopicId(Long id){
        Set<MCQ> questions = this.mcqRepository.findAllByTopicId(id);
        return questions.stream().map((question) -> this.modelMapper.map(questions, MCQDto.class)).collect(Collectors.toSet());
    }

    public MCQDto updateMcq(MCQ mcq, MCQ mapped){
        mcq.setQuestion(mapped.getQuestion());
        mcq.setOptions(mapped.getOptions());
        mcq.setTopic(mapped.getTopic());
        MCQ saved = this.mcqRepository.save(mcq);
        return this.modelMapper.map(saved, MCQDto.class);
    }

    public MCQDto updateMcqByMcqId(Long id, MCQDto dto){
        MCQ mcq = this.mcqRepository.findById(id).orElseThrow(() -> new McqNotFoundException("MCQ with the given id not found!!!"));
        MCQ mapped = this.modelMapper.map(dto, MCQ.class);
        return updateMcq(mcq, mapped);
    }

    public MCQDto updateMcqByMcqIdAndTopicId(Long mcqId, Long topicId, MCQDto mcqDto){
        MCQ mcq = this.mcqRepository.findByMcqIdAndTopicId(mcqId, topicId).orElseThrow(() -> new McqNotFoundException("MCQ with the given mcqId and topicId not found!!!"));
        MCQ mapped = this.modelMapper.map(mcqDto, MCQ.class);
        return updateMcq(mcq, mapped);
    }

    public Boolean deleteMcqByMcqId(Long id){
        this.mcqRepository.deleteById(id);
        return true;
    }

}
