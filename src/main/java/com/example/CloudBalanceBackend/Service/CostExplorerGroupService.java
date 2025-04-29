package com.example.CloudBalanceBackend.Service;


import com.example.CloudBalanceBackend.DTO.CostExplorerGroupFilterDTO;
import java.util.List;

public interface CostExplorerGroupService {
    List<CostExplorerGroupFilterDTO> getAllAvailableServices();
}