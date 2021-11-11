package com.course.coursers.controller;


import com.course.coursers.components.TableComponent;
import com.course.coursers.models.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class ServController {

    @Autowired
    DataSource dataSource;

    @Autowired
    TableComponent tableComponent;


    @GetMapping("/flop")
    public String fst(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Block a = new Block(2,1, Arrays.asList(),dataSource);
        a.insertSelf();
        int result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM test", Integer.class);
        System.out.println(result);
        return "flop";
    }

    @GetMapping("/json")
    public String returnJson(){
        String res = tableComponent.getTableAsJson();
        System.out.println(res);
        return res;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        String res = tableComponent.deleteBlockById(id);
        System.out.println(res);
        return res;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Block body){
        String res = tableComponent.updateBlockById(id, body.getType(), body.getChilds());
        System.out.println(res);
        return res;
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Block body){
        body.insertSelf();
        return "200";
    }


}
