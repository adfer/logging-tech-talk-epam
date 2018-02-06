package com.epam.service;

import com.epam.model.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class StatusService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${status.service.url}")
    private String statusServiceUrl;

    public void setPackagePaid(String packageId) {
        Status paidStatus = Status.PAID;
        log.info("Changing status to {} for package with ID {}", paidStatus, packageId);
        URI url = UriComponentsBuilder
                .fromHttpUrl(statusServiceUrl)
                .path("/status")
                .path("/{packageId}")
                .buildAndExpand(packageId)
                .toUri();
        restTemplate.postForObject(url, paidStatus, Void.class);
        log.info("Package with ID {} has been send", packageId);
    }
}
