package com.example.CloudBalanceBackend.Service.Implementation;

import com.example.CloudBalanceBackend.DTO.SnowflakeDto;
import com.example.CloudBalanceBackend.Repository.SnowflakeRepository;
import com.example.CloudBalanceBackend.Service.SnowflakeService;
import com.example.CloudBalanceBackend.query.QueryBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class SnowflakeServiceImpl implements SnowflakeService {

    private final SnowflakeRepository snowflakeRepository;

    public SnowflakeServiceImpl(SnowflakeRepository snowflakeRepository) {
        this.snowflakeRepository = snowflakeRepository;
    }

    @Override
    public List<Map<String, Object>> fetchAllAccounts() {
        return snowflakeRepository.getAllAccountsData();
    }

    @Override
    public List<String> fetchAllServices() {
        return snowflakeRepository.getAllServices();
    }

    @Override
    public List<Map<String, Object>> fetchGraphData(SnowflakeDto snowflakeDto) {
        LocalDate startDate = snowflakeDto.getStartLocalDate();
        LocalDate endDate = snowflakeDto.getEndLocalDate();

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start Date and End Date must not be null or invalid.");
        }

        String query = QueryBuilder.queryConverter(snowflakeDto, startDate, endDate);
        return snowflakeRepository.getDynamicGraphData(query);
    }

    @Override
    public List<String> fetchFilterData(String filterName) {
        return snowflakeRepository.fetchDistinctValues(filterName);
    }
}