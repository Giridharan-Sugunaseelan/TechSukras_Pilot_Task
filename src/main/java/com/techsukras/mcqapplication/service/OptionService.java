package com.techsukras.mcqapplication.service;

import com.techsukras.mcqapplication.dto.MCQDto;
import com.techsukras.mcqapplication.dto.OptionDto;
import com.techsukras.mcqapplication.entities.MCQ;
import com.techsukras.mcqapplication.entities.Option;
import com.techsukras.mcqapplication.exceptions.McqNotFoundException;
import com.techsukras.mcqapplication.exceptions.OptionNotFoundException;
import com.techsukras.mcqapplication.repositories.McqRepository;
import com.techsukras.mcqapplication.repositories.OptionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class OptionService {

    private OptionRepository optionRepository;

    private ModelMapper modelMapper;

    private McqRepository mcqRepository;

    public OptionDto addOption(OptionDto dto){
        Option mapped = this.modelMapper.map(dto, Option.class);
        Option saved = this.optionRepository.save(mapped);
        return this.modelMapper.map(saved, OptionDto.class);
    }

    public Set<Option> addOptionsToMcq(MCQDto dto, Long mcqId){
        MCQ mcq = this.mcqRepository.findById(mcqId).orElseThrow(() -> new McqNotFoundException("MCQ not found!!!"));
        Set<OptionDto> optionsDtos= dto.getOptions();

        Set<Option> options = optionsDtos.stream()
                .map((optionDto) -> this.modelMapper.map(optionDto, Option.class))
                .collect(Collectors.toSet());

        return options.stream().map((option) -> {
            option.setMcq(mcq);
            return this.optionRepository.save(option);
        }).collect(Collectors.toSet());
    }

    public OptionDto getOption(Long id){
        Option option = this.optionRepository.findById(id).orElseThrow(() -> new OptionNotFoundException("Option with the given Id not found!"));
        return this.modelMapper.map(option, OptionDto.class);
    }

    public List<OptionDto> getOptionsByMcqId(Long id){
        List<Option> options = this.optionRepository.findByMcq_McqId(id);
        return options.stream().map((option) -> this.modelMapper.map(option, OptionDto.class)).toList();
    }

    public OptionDto updateOption(Option option, Option mapped) {
        option.setIsCorrect(mapped.getIsCorrect());
        option.setOptionContent(mapped.getOptionContent());
        option.setMcq(mapped.getMcq());
        Option updated = this.optionRepository.save(option);
        return this.modelMapper.map(updated, OptionDto.class);
    }

    public OptionDto updateOptionByOptionId(Long id, OptionDto dto){
        Option option = this.optionRepository.findById(id).orElseThrow(() -> new OptionNotFoundException("Option with the given Id not found!"));
        Option mapped = this.modelMapper.map(dto, Option.class);
        return updateOption(option, mapped);
    }

    public OptionDto updateOptionByOptionIdAndMcqId(Long optionId, Long mcqId, OptionDto dto){
        Option option = this.optionRepository.findByOptionIdAndMcq_McqId(optionId, mcqId).orElseThrow(() -> new OptionNotFoundException("Option with the given OptionId and McqId not found!"));
        Option mapped = this.modelMapper.map(dto, Option.class);
        return updateOption(option, mapped);
    }

    public Boolean deleteOption(Long id){
        this.optionRepository.deleteById(id);
        return true;
    }
}
