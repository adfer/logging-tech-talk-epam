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

  public Status getPackageStatus(String packageId) {
    log.info("Tracking package with ID {}", packageId);
    URI url = UriComponentsBuilder
        .fromHttpUrl(statusServiceUrl)
        .path("status")
        .queryParam("packageId", packageId)
        .build()
        .toUri();
    Status currentStatus = restTemplate.getForObject(url, Status.class);
    log.info("Package with ID {} has status {}", packageId, currentStatus);
    return currentStatus;
  }
}
