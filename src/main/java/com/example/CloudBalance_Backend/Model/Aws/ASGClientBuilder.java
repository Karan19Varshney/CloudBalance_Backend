package com.example.CloudBalance_Backend.Model.Aws;

import com.example.CloudBalance_Backend.config.AwsCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.autoscaling.AutoScalingClient;

public class ASGClientBuilder {

    public static AutoScalingClient buildAsgClient(String roleArn) {
        return AutoScalingClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(AwsCredentials.getSessionCredentials(roleArn))
                .build();

    }
}
