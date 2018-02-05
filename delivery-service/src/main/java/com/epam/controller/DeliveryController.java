package com.epam.controller;

import com.epam.model.PackageInfoDTO;
import com.epam.model.enums.Status;
import com.epam.service.DeliveryService;
import com.epam.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class DeliveryController {

  @Autowired
  private DeliveryService deliveryService;
  @Autowired
  private StatusService statusService;

  @PostMapping("send")
  public ResponseEntity<Object> sendNewPackage(@RequestBody PackageInfoDTO packageInfoDTO) {
    deliveryService.sendNewPackage(packageInfoDTO);
    return ResponseEntity.ok().build();
  }

  @GetMapping("track")
  public Status trackPackage(@RequestParam String packageId) {
    return statusService.getPackageStatus(packageId);
  }

}
