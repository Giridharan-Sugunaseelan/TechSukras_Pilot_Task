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
import com.techsukras.mcqapplication.repositories.OptionRepository;
import com.techsukras.mcqapplication.repositories.TopicRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class McqService {

    private McqRepository mcqRepository;

    private TopicRepository topicRepository;

    private OptionService optionService;

    private OptionRepository optionRepository;

    private ModelMapper modelMapper;


//    public MCQDto addMcq(MCQDto dto){
//        MCQ mapped = this.modelMapper.map(dto, MCQ.class);
//        MCQ saved = this.mcqRepository.save(mapped);
//        Set<Option> options = this.optionService.addOptionsToMcq(dto.getOptions(), saved.getMcqId());
//        saved.setOptions(options);
//        MCQDto mcqDto = this.modelMapper.map(saved, MCQDto.class);
//        mcqDto.setTopicId(dto.getTopicId());
//        return mcqDto;
//    }


    public MCQDto addMcqToTopic(MCQDto dto, Long topicId){

        Topic topic = this.topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic with the given id not found"));

        Set<Option> options = dto.getOptions().stream().map((optionDto) -> this.optionService.addOption(optionDto)).collect(Collectors.toSet());

        MCQ newMcq = this.modelMapper.map(dto, MCQ.class);

        newMcq.setTopic(topic);

        newMcq.setOptions(options);

        MCQ savedMcq = this.mcqRepository.save(newMcq);

        savedMcq.getOptions().forEach((option) -> {
            option.setMcq(savedMcq);
            this.optionRepository.save(option);
        });

        MCQDto mapped = this.modelMapper.map(savedMcq, MCQDto.class);
        Set<OptionDto> optionDtos = savedMcq.getOptions().stream()
                .map(option -> this.modelMapper.map(option, OptionDto.class))
                .collect(Collectors.toSet());
        mapped.setTopicId(topicId);
        mapped.setOptions(optionDtos);

        return mapped;

    }

    public MCQDto getMcq(Long id){
        MCQ mcq = this.mcqRepository.findById(id).orElseThrow(() -> new McqNotFoundException("MCQ with the given id not found!!!"));
        return this.modelMapper.map(mcq, MCQDto.class);
    }

    public Set<MCQ> getAllMcqByTopicId(Long id){
        Set<MCQ> questions = this.mcqRepository.findAllByTopic_TopicId(id);
//        return questions.stream().map((question) -> this.modelMapper.map(questions, MCQDto.class)).collect(Collectors.toSet());
        return questions;
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
        MCQ mcq = this.mcqRepository.findByMcqIdAndTopic_TopicId(mcqId, topicId).orElseThrow(() -> new McqNotFoundException("MCQ with the given mcqId and topicId not found!!!"));
        MCQ mapped = this.modelMapper.map(mcqDto, MCQ.class);
        return updateMcq(mcq, mapped);
    }

    public Boolean deleteMcqByMcqId(Long id){
        this.mcqRepository.deleteById(id);
        return true;
    }

}
