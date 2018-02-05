package com.epam.controller;

import com.epam.model.enums.Status;
import com.epam.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

  @Autowired
  private StatusService statusService;

  @GetMapping
  public Status getStatusByPackageId(@RequestParam String packageId) {
    return statusService.getStatusByPackageId(packageId);
  }

  @PostMapping("{packageId}")
  public void changeStatusByPackageId(@PathVariable String packageId, @RequestBody Status newStatus) {
    statusService.setStatusForPackageId(packageId, newStatus);
  }
}
