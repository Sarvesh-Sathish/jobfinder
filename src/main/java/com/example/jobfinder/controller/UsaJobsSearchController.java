package com.example.jobfinder.controller;

import com.example.jobfinder.service.UsaJobsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
public class UsaJobsSearchController {
    private final UsaJobsService usaJobsService;

    public UsaJobsSearchController(UsaJobsService usaJobsService) {
        this.usaJobsService = usaJobsService;
    }

    @GetMapping
    public ResponseEntity<String> getJobs(
            @RequestParam("keyword") String keyword,
            @RequestParam("location") String location,
            @RequestParam(value = "resultsPerPage", defaultValue = "10") int resultsPerPage) {

        String result = usaJobsService.searchJobs(keyword, location, resultsPerPage);
        return ResponseEntity.ok(result);
    }
}
