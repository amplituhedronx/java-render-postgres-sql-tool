package com.amplituhedron.sqltool.controller;

import com.amplituhedron.sqltool.service.SqlExecutorService;
import com.amplituhedron.sqltool.service.SqlResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SqlController {

    private final SqlExecutorService sqlExecutorService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/execute")
    public String execute(@RequestParam("sql") String sql, Model model) {
        SqlResult result = sqlExecutorService.execute(sql);
        model.addAttribute("result", result);
        model.addAttribute("sql", sql);
        return "index :: results";
    }
}