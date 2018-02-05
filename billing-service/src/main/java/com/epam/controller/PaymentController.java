package com.epam.controller;

import com.epam.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PaymentController {

  @Autowired
  private StatusService statusService;

  @PostMapping("/payment/{packageId}")
  public void makePaymentForPackage(@PathVariable String packageId, @RequestBody String deliveryCountry) {
    log.info("Making payment for package with ID {} and delivery country {}", packageId, deliveryCountry);
    statusService.setPackagePaid(packageId);
    log.info("Package with ID {} has been paid", packageId);
  }
}
