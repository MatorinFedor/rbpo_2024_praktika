package ru.mtuci.rbpo_2024_praktika.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpo_2024_praktika.model.Demo;
import ru.mtuci.rbpo_2024_praktika.service.DemoService;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('read')")
    public List<Demo> findAll() {
        return demoService.findAll();
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('modification')")
    public void save(@RequestBody Demo demo) {
        demoService.save(demo);
    }
}