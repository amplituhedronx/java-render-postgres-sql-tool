package com.amplituhedron.sqltool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SqlExecutorService {

    private final JdbcTemplate jdbcTemplate;

    public SqlResult execute(String sql) {
        try {
            String trimmed = sql.trim().toUpperCase();
            if (trimmed.startsWith("SELECT") || trimmed.startsWith("WITH") ||
                trimmed.startsWith("EXPLAIN") || trimmed.startsWith("SHOW")) {
                List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
                return new SqlResult(true, "Query successful", rows.size(), rows, null);
            } else {
                int affected = jdbcTemplate.update(sql);
                return new SqlResult(true, "Statement executed", affected, null, null);
            }
        } catch (Exception e) {
            log.error("SQL execution failed", e);
            return new SqlResult(false, e.getMessage(), 0, null, e.getClass().getSimpleName());
        }
    }
}