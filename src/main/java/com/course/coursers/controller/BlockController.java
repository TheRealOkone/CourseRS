package com.course.coursers.controller;


import com.course.coursers.components.BlockComponent;
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
@RequestMapping("/api/v1/block")
public class BlockController {

    @Autowired
    DataSource dataSource;

    @Autowired
    BlockComponent blockComponent;





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String res = blockComponent.deleteBlockById(id);
        System.out.println(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Block body){
        String res = blockComponent.updateBlockById(id, body.getType(), body.getChilds(), body.getDiagram(), body.getDescription());
        System.out.println(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public HttpStatus insert(@RequestBody Block body){
        body.insertSelf();
        return HttpStatus.OK;
    }


}
