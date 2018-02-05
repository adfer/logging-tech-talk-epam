package com.epam.service;

import com.epam.model.PackageInfoDTO;
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
public class DeliveryService {

  @Autowired
  private RestTemplate restTemplate;
  @Value("${billing.service.url}")
  private String billingServiceUrl;

  public void sendNewPackage(PackageInfoDTO packageInfoDTO) {
    log.info("Sending new package with ID {}", packageInfoDTO.getPackageId());
    URI url = UriComponentsBuilder
        .fromHttpUrl(billingServiceUrl)
        .path("/payment")
        .path("/{packageId}")
        .buildAndExpand(packageInfoDTO.getPackageId())
        .toUri();
    Status changedStatus = restTemplate.postForObject(url, packageInfoDTO.getDeliveryCountry(), Status.class);
    log.info("Status for package with ID {} is {}", packageInfoDTO.getPackageId(), changedStatus);
  }
}
