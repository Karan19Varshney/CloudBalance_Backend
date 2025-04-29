package com.example.CloudBalanceBackend.Service;

import com.example.CloudBalanceBackend.DTO.AWS.ASGResourceDTO;
import com.example.CloudBalanceBackend.DTO.AWS.EC2ResourceDTO;
import com.example.CloudBalanceBackend.DTO.AWS.RDSResourceDTO;

import java.util.List;

public interface AwsResourceService {
    List<EC2ResourceDTO> getEC2Instances(String roleArn);
    List<RDSResourceDTO> getRDSInstances(String roleArn);
    List<ASGResourceDTO> getASGInstances(String roleArn);
}