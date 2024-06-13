package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.entity.Option;
import com.serkanguner.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoints.OPTIONS)
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Option> getOptionById(@PathVariable String id) {
        Optional<Option> option = optionService.getOptionById(id);
        return option.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Option addOption(@RequestBody Option option) {
        return optionService.addOption(option);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Option> updateOption(@PathVariable String id, @RequestBody Option option) {
        Option updatedOption = optionService.updateOption(id, option);
        return updatedOption != null ? ResponseEntity.ok(updatedOption) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteOption(@PathVariable String id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
