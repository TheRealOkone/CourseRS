package com.course.coursers.controller;


import com.course.coursers.models.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class ServController {

    @Autowired
    DataSource dataSource;


    @GetMapping("/flop")
    public String fst(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Block a = new Block(1,1, Arrays.asList(2, 3),dataSource);
        a.insertSelf();
        int result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM test", Integer.class);
        System.out.println(result);
        return "flop";
    }


}
