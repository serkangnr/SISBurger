package com.serkanguner.service;

import com.serkanguner.entity.Option;
import com.serkanguner.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Optional<Option> getOptionById(String id) {
        return optionRepository.findById(id);
    }

    public Option addOption(Option option) {
        return optionRepository.save(option);
    }

    public Option updateOption(String id, Option option) {
        if (optionRepository.existsById(id)) {
            option.setId(id);
            return optionRepository.save(option);
        } else {
            return null;
        }
    }

    public void deleteOption(String id) {
        optionRepository.deleteById(id);
    }

}
