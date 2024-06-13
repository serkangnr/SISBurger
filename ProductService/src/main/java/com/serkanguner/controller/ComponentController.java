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

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public List<Component> getAllComponents() {
        return componentService.getAllComponents();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Component> getComponentById(@PathVariable String id) {
        Optional<Component> component = componentService.getComponentById(id);
        return component.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Component addComponent(@RequestBody Component component) {
        return componentService.addComponent(component);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Component> updateComponent(@PathVariable String id, @RequestBody Component component) {
        Component updatedComponent = componentService.updateComponent(id, component);
        return updatedComponent != null ? ResponseEntity.ok(updatedComponent) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteComponent(@PathVariable String id) {
        componentService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}
