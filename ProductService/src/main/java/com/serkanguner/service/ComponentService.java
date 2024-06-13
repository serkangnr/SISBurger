package com.serkanguner.service;

import com.serkanguner.entity.Component;
import com.serkanguner.repository.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComponentService {
    private final ComponentRepository componentRepository;
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public Optional<Component> getComponentById(String id) {
        return componentRepository.findById(id);
    }

    public Component addComponent(Component component) {
        return componentRepository.save(component);
    }

    public Component updateComponent(String id, Component component) {
        if (componentRepository.existsById(id)) {
            component.setId(id);
            return componentRepository.save(component);
        } else {
            return null;
        }
    }

    public void deleteComponent(String id) {
        componentRepository.deleteById(id);
    }
}
