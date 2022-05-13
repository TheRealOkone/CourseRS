package com.course.coursers.controller;


import com.course.coursers.components.BlockComponent;
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



    @GetMapping("/ping")
    public ResponseEntity<String> ping(){
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/json")
    public ResponseEntity<String> returnJson(@RequestParam int diagram){
        String res;
        if(diagram >= 1) {
            res = tableComponent.getTableAsJson(diagram);
        }else{
            res = tableComponent.getTableAsJson();
        }
        System.out.println(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }


    @GetMapping("/jsondiag")
    public ResponseEntity<String> returnJsonDiag(){
        String res = tableComponent.getTableAsJsonDiag();
        System.out.println(res);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @GetMapping("/flop")
    public HttpStatus fst(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Diagram diag = new Diagram(1,"flop","flopper",dataSource);
        diag.insertSelf();
        Block a = new Block(1,1, Arrays.asList(2,3),1,"flop1", dataSource);
        a.insertSelf();
        Block b = new Block(2,1, Arrays.asList(4),1,"flop2", dataSource);
        b.insertSelf();
        Block c = new Block(3,1, Arrays.asList(4),1,"flop3", dataSource);
        c.insertSelf();
        Block d = new Block(4,2, Arrays.asList(0),1,"flop4", dataSource);
        d.insertSelf();
        Diagram diag2 = new Diagram(2,"flop","flopper",dataSource);
        diag2.insertSelf();
        Block a2 = new Block(5,1, Arrays.asList(6,7),2,"flop1", dataSource);
        a2.insertSelf();
        Block b2 = new Block(6,1, Arrays.asList(8),2,"flop2", dataSource);
        b2.insertSelf();
        Block c2 = new Block(7,1, Arrays.asList(8),2,"flop3", dataSource);
        c2.insertSelf();
        Block d2 = new Block(8,2, Arrays.asList(0),2,"flop4", dataSource);
        d2.insertSelf();
        Diagram diag3 = new Diagram(3,"flop","flopper",dataSource);
        diag3.insertSelf();
        Block a3 = new Block(9,1, Arrays.asList(10,11),3,"flop1", dataSource);
        a3.insertSelf();
        Block b3 = new Block(10,1, Arrays.asList(12),3,"flop2", dataSource);
        b3.insertSelf();
        Block c3 = new Block(11,1, Arrays.asList(12),3,"flop3", dataSource);
        c3.insertSelf();
        Block d3 = new Block(12,2, Arrays.asList(0),3,"flop4", dataSource);
        d3.insertSelf();
        int result = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM blocks", Integer.class);
        System.out.println(result);

        return HttpStatus.OK;
    }

    @GetMapping("/moder")
    public HttpStatus rebase() {
        tableComponent.prepareBase();
        return HttpStatus.OK;
    }

    @GetMapping("/trunc")
    public HttpStatus debase() {
        tableComponent.deleteBase();
        return HttpStatus.OK;
    }



}
