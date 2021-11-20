package com.course.coursers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Diagram {

    @JsonIgnore
    JdbcTemplate jdbcTemplate;

    private  int id;
    private String name;
    private String description;


    public Diagram(int id, String name, String description, DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public boolean insertSelf(){
        jdbcTemplate.execute("insert into diagrams " +
                "values ('" + id + "','" + name + "','" + description + "');");
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
