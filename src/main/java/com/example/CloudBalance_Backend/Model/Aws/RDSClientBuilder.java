package com.example.CloudBalance_Backend.Model.Aws;

import com.example.CloudBalance_Backend.config.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rds.RdsClient;

public class RDSClientBuilder {
    public static RdsClient buildRdsClient(String roleArn) {
        return RdsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(AwsCredentials.getSessionCredentials(roleArn))
                .build();
    }

}
