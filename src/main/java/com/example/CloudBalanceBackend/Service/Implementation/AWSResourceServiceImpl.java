package com.example.CloudBalanceBackend.Service.Implementation;

import com.example.CloudBalanceBackend.DTO.AWS.ASGResourceDTO;
import com.example.CloudBalanceBackend.DTO.AWS.EC2ResourceDTO;
import com.example.CloudBalanceBackend.DTO.AWS.RDSResourceDTO;
import com.example.CloudBalanceBackend.Model.Aws.ASGClientBuilder;
import com.example.CloudBalanceBackend.Model.Aws.RDSClientBuilder;
import com.example.CloudBalanceBackend.Service.AwsResourceService;
import com.example.CloudBalanceBackend.Model.Aws.EC2ClientBuilder;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.autoscaling.model.AutoScalingGroup;
import software.amazon.awssdk.services.autoscaling.model.DescribeAutoScalingGroupsResponse;
import software.amazon.awssdk.services.ec2.model.Reservation;
import software.amazon.awssdk.services.ec2.model.Tag;
import software.amazon.awssdk.services.rds.model.DBInstance;
import software.amazon.awssdk.services.rds.model.DescribeDbInstancesResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class AWSResourceServiceImpl implements AwsResourceService {


    @Override
    public List<EC2ResourceDTO> getEC2Instances(String roleArn) {
        var ec2Client = EC2ClientBuilder.buildEc2Client(roleArn);
        var response = ec2Client.describeInstances();
        List<EC2ResourceDTO> result = new ArrayList<>();

        for (Reservation reservation : response.reservations()) {
            reservation.instances().forEach(instance -> {
                EC2ResourceDTO ec2ResourceDTO = new EC2ResourceDTO();
                ec2ResourceDTO.setResourceId(instance.instanceId());
                ec2ResourceDTO.setResourceName(instance
                        .tags()
                        .stream()
                        .filter(t -> t.key().equalsIgnoreCase("Name"))
                        .findFirst()
                        .map(Tag::value)
                        .orElse("N/A"));
                ec2ResourceDTO.setRegion(instance.placement().availabilityZone());
                ec2ResourceDTO.setStatus(instance.state().nameAsString());
                result.add(ec2ResourceDTO);
            });
        }
        return result;
    }

    @Override
    public List<RDSResourceDTO> getRDSInstances(String roleArn) {
        var rdsClient = RDSClientBuilder.buildRdsClient(roleArn);
        DescribeDbInstancesResponse response = rdsClient.describeDBInstances();
        List<RDSResourceDTO> result = new ArrayList<>();

        for (DBInstance dbInstance : response.dbInstances()) {
            RDSResourceDTO dto = new RDSResourceDTO();
            dto.setResourceId(dbInstance.dbInstanceIdentifier());
            dto.setResourceName(dbInstance.dbInstanceIdentifier());
            dto.setEngine(dbInstance.engine());
            dto.setRegion(dbInstance.availabilityZone());
            dto.setStatus(dbInstance.dbInstanceStatus());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<ASGResourceDTO> getASGInstances(String roleArn) {
        var asgClient = ASGClientBuilder.buildAsgClient(roleArn);
        DescribeAutoScalingGroupsResponse response = asgClient.describeAutoScalingGroups();
        List<ASGResourceDTO> result = new ArrayList<>();

        for (AutoScalingGroup group : response.autoScalingGroups()) {
            ASGResourceDTO dto = new ASGResourceDTO();
            dto.setResourceId(group.autoScalingGroupARN());
            dto.setResourceName(group.autoScalingGroupName());
            dto.setRegion(group.availabilityZones().isEmpty() ? "N/A" : group.availabilityZones().get(0));
            dto.setDesiredCapacity(Long.valueOf(String.valueOf(group.desiredCapacity())));
            dto.setMinSize(Long.valueOf(String.valueOf(group.minSize())));
            dto.setMaxSize(Long.valueOf(String.valueOf(group.maxSize())));
            dto.setStatus(group.status() != null ? group.status() : "Active");
            result.add(dto);
        }
        return result;
    }

}