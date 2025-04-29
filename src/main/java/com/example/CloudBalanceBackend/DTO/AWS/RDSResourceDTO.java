package com.example.CloudBalanceBackend.DTO.AWS;


import lombok.Data;

@Data
public class RDSResourceDTO {
    private String resourceId;
    private String resourceName;
    private String engine;
    private String region;
    private String status;

}