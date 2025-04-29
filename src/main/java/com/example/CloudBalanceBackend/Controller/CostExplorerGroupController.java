package com.example.CloudBalanceBackend.Controller;

import com.example.CloudBalanceBackend.DTO.CostExplorerGroupFilterDTO;
import com.example.CloudBalanceBackend.Service.CostExplorerGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cost-explorer")
public class CostExplorerGroupController {
    private final CostExplorerGroupService costExplorerGroupService;

    @GetMapping
    public ResponseEntity<List<CostExplorerGroupFilterDTO>> getAvailableServices() {
        return ResponseEntity.ok(costExplorerGroupService.getAllAvailableServices());
    }
}