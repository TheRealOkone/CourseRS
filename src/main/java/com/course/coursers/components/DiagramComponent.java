package com.course.coursers.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
public class DiagramComponent {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;
    @PostConstruct
    void confTemplate(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




    public String getDiagramById(int id){
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(diagrams))) FROM diagrams  WHERE id = " + id + ";", String.class);
        return str;
    }



    public String deleteDiagramById(int id){
        String str = jdbcTemplate.queryForObject("DELETE FROM diagrams  WHERE id = " + id + ";", String.class);
        return str;
    }



    public String updateDiagramById(int id, String name, String decsription){
        String str = jdbcTemplate.queryForObject("update diagrams  set name = " + name + ", set decsription = " + decsription + " where id = " + id + ";", String.class);
        return str;
    }





}
