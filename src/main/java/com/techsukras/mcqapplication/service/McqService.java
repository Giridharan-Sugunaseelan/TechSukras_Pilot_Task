package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.MCQDto;
import com.techsukras.mcqapplication.entities.MCQ;
import com.techsukras.mcqapplication.exceptions.McqNotFoundException;
import com.techsukras.mcqapplication.repositories.McqRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class McqService {

    private McqRepository mcqRepository;

    private ModelMapper modelMapper;

    public MCQDto addMcq(MCQDto dto){
        MCQ mapped = this.modelMapper.map(dto, MCQ.class);
        MCQ saved = this.mcqRepository.save(mapped);
        return this.modelMapper.map(saved, MCQDto.class);
    }

    public MCQDto getMcq(Long id){
        MCQ mcq = this.mcqRepository.findById(id).orElseThrow(() -> new McqNotFoundException("MCQ with the given id not found!!!"));
        return this.modelMapper.map(mcq, MCQDto.class);
    }

    public List<MCQDto> getAllMcqByTopicId(Long id){
        List<MCQ> questions = this.mcqRepository.findAllByTopicId(id);
        return questions.stream().map((question) -> this.modelMapper.map(questions, MCQDto.class)).toList();
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
