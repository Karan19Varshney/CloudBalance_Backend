package com.example.CloudBalanceBackend.DTO.AWS;

import lombok.Data;

@Data
public class EC2ResourceDTO {
    private String resourceId;
    private String resourceName;
    private String region;
    private String status;
}