package ru.mtuci.rbpo_2024_praktika.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpo_2024_praktika.model.Demo;
import ru.mtuci.rbpo_2024_praktika.model.Details;
import ru.mtuci.rbpo_2024_praktika.repository.DetailsRepository;
import ru.mtuci.rbpo_2024_praktika.service.DemoService;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailsController {

    private final DetailsRepository detailsRepository;
    private final DemoService demoService;

    @PostMapping("/{demo_id}/save")
    public void save(@PathVariable(value = "demo_id") Long demoId,
                     @RequestBody Details details) {
        Demo demo = demoService.findById(demoId);
        details.setDemo(demo);
        detailsRepository.save(details);
    }
}
