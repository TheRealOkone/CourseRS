package com.course.coursers.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Component
public class TableComponent {
    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbcTemplate;
    @PostConstruct
    void confTemplate(){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String getTableAsJson(){
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(test))) FROM test;", String.class);
        return str;
    }

    public String getBlockById(int id){
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(test))) FROM test  WHERE id = " + id + ";", String.class);
        return str;
    }

    public String deleteBlockById(int id){
        String str = jdbcTemplate.queryForObject("DELETE FROM test  WHERE id = " + id + ";", String.class);
        return str;
    }

    public String updateBlockById(int id, int type, List<Integer> childs){
        String str = jdbcTemplate.queryForObject("update test  set type = " + type + ", set childs = " + childs.toString().replace("[","{").replace("]","}") + " where id = " + id + ";", String.class);
        return str;
    }


}
