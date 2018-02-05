package com.epam.model;

import com.epam.model.enums.Status;
import lombok.Data;

@Data
public class PackageInfoDTO {
  private String packageId;
  private String deliveryCountry;
  private Status status;
}
