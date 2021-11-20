package com.course.coursers.controller;


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
@RequestMapping("/api/v1/diagram")
public class DiagramController {

    @Autowired
    DataSource dataSource;

    @Autowired
    DiagramComponent diagramComponent;




    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        String res = diagramComponent.deleteDiagramById(id);
        System.out.println(res);
        return res;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable int id, @RequestBody Diagram body){
        String res = diagramComponent.updateDiagramById(id, body.getName(),  body.getDescription());
        System.out.println(res);
        return res;
    }

    @PostMapping("/insert")
    public String insert(@RequestBody Diagram body){
        body.insertSelf();
        return "200";
    }


}
