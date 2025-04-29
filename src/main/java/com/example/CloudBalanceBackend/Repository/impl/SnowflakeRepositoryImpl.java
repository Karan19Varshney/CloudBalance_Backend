package com.example.CloudBalanceBackend.Repository.impl;

import com.example.CloudBalanceBackend.Repository.SnowflakeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SnowflakeRepositoryImpl implements SnowflakeRepository {

    private final JdbcTemplate snowflakeJdbcTemplate;

    public SnowflakeRepositoryImpl(@Qualifier("snowflakeJdbcTemplate") JdbcTemplate snowflakeJdbcTemplate) {
        this.snowflakeJdbcTemplate = snowflakeJdbcTemplate;
    }

    @Override
    public List<Map<String, Object>> getAllAccountsData() {
        String sql = "Select * from COST_EXPLORER limit 1000";
        return snowflakeJdbcTemplate.queryForList(sql);
    }

    @Override
    public List<String> getAllServices() {
        String sql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = 'COST_EXPLORER' AND TABLE_SCHEMA = 'COST'";
        return snowflakeJdbcTemplate.query(sql,  (rs, rowNum) -> rs.getString("COLUMN_NAME"));
    }

    @Override
    public List<Map<String, Object>> getDynamicGraphData(String query) {
        return snowflakeJdbcTemplate.queryForList(query);
    }

    @Override
    public List<String> fetchDistinctValues(String filterName) {
        String sql = String.format(
                "SELECT DISTINCT %s FROM COST_EXPLORER WHERE %s IS  NOT NULL ORDER BY %s",
                filterName, filterName, filterName
        );
        return snowflakeJdbcTemplate.queryForList(sql, String.class);
    }
}