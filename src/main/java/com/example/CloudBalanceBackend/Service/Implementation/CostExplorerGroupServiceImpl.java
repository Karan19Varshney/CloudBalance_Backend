package com.example.CloudBalanceBackend.Service.Implementation;

import com.example.CloudBalanceBackend.DTO.CostExplorerGroupFilterDTO;
import com.example.CloudBalanceBackend.Repository.CostExplorerGroupFilterRepository;
import com.example.CloudBalanceBackend.Service.CostExplorerGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostExplorerGroupServiceImpl implements CostExplorerGroupService {

    private final CostExplorerGroupFilterRepository repository;

    public CostExplorerGroupServiceImpl(CostExplorerGroupFilterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CostExplorerGroupFilterDTO> getAllAvailableServices() {
        return repository.findAll()
                .stream()
                .map(entity -> {
                    CostExplorerGroupFilterDTO dto = new CostExplorerGroupFilterDTO();
                    dto.setId(entity.getId());
                    dto.setDatabaseName(entity.getDatabaseName());
                    dto.setDisplayName(entity.getDisplayName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}