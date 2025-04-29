package com.example.CloudBalanceBackend.Controller;

import com.example.CloudBalanceBackend.DTO.AWS.ASGResourceDTO;
import com.example.CloudBalanceBackend.DTO.AWS.EC2ResourceDTO;
import com.example.CloudBalanceBackend.DTO.AWS.RDSResourceDTO;
import com.example.CloudBalanceBackend.Service.AccountService;
import com.example.CloudBalanceBackend.Service.AwsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aws")
public class AWSResourceController {

    @Autowired
    private AwsResourceService awsResourceService;


    @Autowired
    private AccountService accountService;


    @GetMapping("/ec2")
    public ResponseEntity<List<EC2ResourceDTO>> getEC2(@RequestParam Long accountId) {
        String arn = accountService.getArnByAccountId(accountId);
        return ResponseEntity.ok(awsResourceService.getEC2Instances(arn));
    }

    @GetMapping("/rds")
    public ResponseEntity<List<RDSResourceDTO>> getRDS(@RequestParam Long accountId) {
        String arn = accountService.getArnByAccountId(accountId);
        return ResponseEntity.ok(awsResourceService.getRDSInstances(arn));
    }

    @GetMapping("/asg")
    public ResponseEntity<List<ASGResourceDTO>> getASG(@RequestParam Long accountId) {
        String arn = accountService.getArnByAccountId(accountId);
        return ResponseEntity.ok(awsResourceService.getASGInstances(arn));
    }
}