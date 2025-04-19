package com.example.CloudBalance_Backend.DTO.AWS;


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
