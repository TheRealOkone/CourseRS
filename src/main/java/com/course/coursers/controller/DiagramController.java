package com.course.coursers.controller;


import com.course.coursers.components.DiagramComponent;
import com.course.coursers.components.TableComponent;
import com.course.coursers.models.Block;
import com.course.coursers.models.Diagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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




    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String res = diagramComponent.deleteDiagramById(id);
        System.out.println(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Diagram body){
        String res = diagramComponent.updateDiagramById(id, body.getName(),  body.getDescription());
        System.out.println(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public HttpStatus insert(@RequestBody Diagram body){
        body.insertSelf();
        return HttpStatus.OK;
    }


}
