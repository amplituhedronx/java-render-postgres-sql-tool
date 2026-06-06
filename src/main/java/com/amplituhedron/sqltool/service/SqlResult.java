package com.amplituhedron.sqltool.service;

import java.util.List;
import java.util.Map;

public record SqlResult(
    boolean success,
    String message,
    int affectedRows,
    List<Map<String, Object>> rows,
    String errorType
) {}