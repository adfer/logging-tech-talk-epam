package com.epam.service;

import com.epam.model.enums.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class StatusService {

  private static final Map<String, Status> statusMap = new ConcurrentHashMap<>();

  public Status getStatusByPackageId(String packageId) {
    log.info("Getting status for package with ID {}", packageId);
    Status status = statusMap.get(packageId);
    log.info("Status for package with ID is {}", packageId, status);
    return status;
  }

  public void setStatusForPackageId(String packageId, Status newStatus) {
    log.info("Changing status to {} for package with ID {}", newStatus, packageId);
    Status previousStatus = statusMap.put(packageId, newStatus);
    log.info("Status for package with ID {} changed from {} to {}", packageId, previousStatus, newStatus);
  }
}
