package com.example.jobfinder.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

@Service
public class UsaJobsService {
    @Value("${usajobs.api.url}")
    private String apiUrl;

    @Value("${usajobs.api.key}")
    private String apiKey;

    @Value("${usajobs.user.agent}")
    private String userAgent;

    public String fetchJobsList(String type) {
        RestTemplate restTemplate = new RestTemplate();

        return "";

    }
}
