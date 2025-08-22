package com.example.jobfinder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;


public class UsaJobsHeadersFactory {
    @Value("${usajobs.api.key}")
    private String apiKey;

    @Value("${usajobs.user.agent}")
    private String userAgent;

    public HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization-Key", apiKey);
        headers.set("User-Agent", userAgent);
        return headers;
    }
}
