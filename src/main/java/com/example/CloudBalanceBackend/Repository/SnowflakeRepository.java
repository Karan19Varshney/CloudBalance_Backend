package com.example.CloudBalanceBackend.Repository;

import java.util.List;
import java.util.Map;

public interface SnowflakeRepository {
    List<Map<String, Object>>  getAllAccountsData();

    List<String> getAllServices();

    List<Map<String, Object>> getDynamicGraphData(String query);

    List<String> fetchDistinctValues(String filterName);
}