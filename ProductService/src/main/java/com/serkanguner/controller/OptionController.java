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

    @GetMapping(EndPoints.FINDALL)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Option> getAllOptions() {
        return optionService.getAllOptions();
    }

    @GetMapping(EndPoints.FINDBYID)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Option> getOptionById(@RequestParam String id) {
        Optional<Option> option = optionService.getOptionById(id);
        return option.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(EndPoints.SAVE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Option addOption(@RequestBody Option option) {
        return optionService.addOption(option);
    }

    @PutMapping(EndPoints.UPDATE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Option> updateOption(@RequestParam String id, @RequestBody Option option) {
        Option updatedOption = optionService.updateOption(id, option);
        return updatedOption != null ? ResponseEntity.ok(updatedOption) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(EndPoints.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteOption(@RequestParam String id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }
}
