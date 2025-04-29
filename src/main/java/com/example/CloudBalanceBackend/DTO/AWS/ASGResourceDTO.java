package com.example.CloudBalanceBackend.DTO.AWS;


import lombok.Data;

@Data
public class ASGResourceDTO {
    private String resourceId;
    private String resourceName;
    private String region;
    private Long desiredCapacity;
    private Long minSize;
    private Long maxSize;
    private String status;
}
