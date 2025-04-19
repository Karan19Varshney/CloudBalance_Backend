package com.example.CloudBalance_Backend.Service;

import com.example.CloudBalance_Backend.DTO.AWS.ASGResourceDTO;
import com.example.CloudBalance_Backend.DTO.AWS.EC2ResourceDTO;
import com.example.CloudBalance_Backend.DTO.AWS.RDSResourceDTO;

import java.util.List;

public interface AwsResourceService {
    List<EC2ResourceDTO> getEC2Instances(String roleArn);
    List<RDSResourceDTO> getRDSInstances(String roleArn);
    List<ASGResourceDTO> getASGInstances(String roleArn);
}