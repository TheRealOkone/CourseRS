package com.course.coursers.controller;


import com.course.coursers.components.BlockComponent;
import com.course.coursers.components.DiagramComponent;
import com.course.coursers.components.TableComponent;
import com.course.coursers.models.Block;
import com.course.coursers.models.Diagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class UtilController {

    @Autowired
    DataSource dataSource;

    @Autowired
    TableComponent tableComponent;
    @Autowired
    BlockComponent blockComponent;
    @Autowired
    DiagramComponent diagramComponent;


    @GetMapping("/json")
    public String returnJson(){
        String res = tableComponent.getTableAsJson();
        System.out.println(res);
        return res;
    }

    @GetMapping("/flop")    public String fst(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Diagram diag = new Diagram(1,"flop","flopper",dataSource);
        diag.insertSelf();
        Block a = new Block(1,1, Arrays.asList(2,3),1,dataSource);
        a.insertSelf();
        Block b = new Block(2,1, Arrays.asList(4),1,dataSource);
        b.insertSelf();
        Block c = new Block(3,1, Arrays.asList(4),1,dataSource);
        c.insertSelf();
        Block d = new Block(4,2, Arrays.asList(0),1,dataSource);
        d.insertSelf();
        int result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM blocks", Integer.class);
        System.out.println(result);

        return "flop";
    }

    @GetMapping("/moder")
    public String rebase() {
        tableComponent.prepareBase();
        return "ok";
    }

    @GetMapping("/trunc")
    public String debase() {
        tableComponent.deleteBase();
        return "ok";
    }



}
