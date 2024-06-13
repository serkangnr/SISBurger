package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.entity.Component;
import com.serkanguner.service.ComponentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(EndPoints.COMPONENT)
@RequiredArgsConstructor
public class ComponentController {
    private final ComponentService componentService;

    @GetMapping(EndPoints.FINDALL)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    @GetMapping(EndPoints.FINDBYID)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Component> getComponentById(@RequestParam String id) {
        Optional<Component> component = componentService.getComponentById(id);
        return component.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(EndPoints.SAVE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Component addComponent(@RequestBody Component component) {
        return componentService.addComponent(component);
    }

    @PutMapping(EndPoints.UPDATE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Component> updateComponent(@RequestParam String id, @RequestBody Component component) {
        Component updatedComponent = componentService.updateComponent(id, component);
        return updatedComponent != null ? ResponseEntity.ok(updatedComponent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(EndPoints.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteComponent(@RequestParam String id) {
        componentService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}
