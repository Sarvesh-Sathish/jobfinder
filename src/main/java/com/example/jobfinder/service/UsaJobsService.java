package com.example.jobfinder.service;
import lombok.extern.slf4j.Slf4j;

import com.example.jobfinder.config.UsaJobsHeadersFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class UsaJobsService {
    private UsaJobsHeadersFactory usaJobsHeadersFactory;
    private final String apiUrl;

    public UsaJobsService(UsaJobsHeadersFactory usaJobsHeadersFactory, @Value("${usajobs.api.url}") String apiUrl) {
        this.usaJobsHeadersFactory = usaJobsHeadersFactory;
        this.apiUrl = apiUrl;
    }

    public String fetchCodeList(String type) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = usaJobsHeadersFactory.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = apiUrl + "/codelist/"  + type;

        try {
            log.info("Calling USAJobs API: {} with type={}", apiUrl, type);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Failed to fetch codelist: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching codelist from USAJobs API", e);
        }
    }

    public String searchJobs(String keyword, String location, int resultsPerPage) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = usaJobsHeadersFactory.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(usaJobsHeadersFactory.createHeaders());

        String url = UriComponentsBuilder.fromHttpUrl(apiUrl + "/search")
                .queryParam("Keyword", keyword)
                .queryParam("LocationName", location)
                .queryParam("ResultsPerPage", resultsPerPage)
                .toUriString();

        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new RuntimeException("USAJobs returned " + response.getStatusCode() + ": " + response.getBody());
    }
}
