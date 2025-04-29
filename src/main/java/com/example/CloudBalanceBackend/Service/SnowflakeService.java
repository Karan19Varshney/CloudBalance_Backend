package com.example.CloudBalanceBackend.Service;



import com.example.CloudBalanceBackend.DTO.SnowflakeDto;

import java.util.List;
import java.util.Map;

public interface SnowflakeService {
    List<Map<String, Object>> fetchAllAccounts();
    List<String> fetchAllServices();
    List<Map<String, Object>> fetchGraphData(SnowflakeDto snowflakeDto);
    List<String> fetchFilterData(String filterName);
}