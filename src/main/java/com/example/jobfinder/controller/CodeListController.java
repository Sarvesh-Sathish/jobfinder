package com.example.jobfinder.controller;

import com.example.jobfinder.service.UsaJobsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/codelist")
public class CodeListController {
    private final UsaJobsService usaJobsService;

    public CodeListController(UsaJobsService usaJobsService) {
        this.usaJobsService = usaJobsService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<String> getCodeList(@PathVariable("type") String type) {
        String result = usaJobsService.fetchCodeList(type);
        return ResponseEntity.ok(result);
    }
}
