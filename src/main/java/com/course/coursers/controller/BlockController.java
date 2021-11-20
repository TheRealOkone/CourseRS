package com.course.coursers.controller;


import com.course.coursers.components.BlockComponent;
import com.course.coursers.components.TableComponent;
import com.course.coursers.models.Block;
import com.course.coursers.models.Diagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/block")
public class BlockController {

    @Autowired
    DataSource dataSource;

    @Autowired
    BlockComponent blockComponent;





    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        String res = blockComponent.deleteBlockById(id);
        System.out.println(res);
        return res;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Block body){
        String res = blockComponent.updateBlockById(id, body.getType(), body.getChilds(), body.getDiagram());
        System.out.println(res);
        return res;
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Block body){
        body.insertSelf();
        return "200";
    }


}
