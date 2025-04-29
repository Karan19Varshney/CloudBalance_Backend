package com.example.CloudBalanceBackend.Model.Aws;

import com.example.CloudBalanceBackend.config.AwsCredentials;
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
