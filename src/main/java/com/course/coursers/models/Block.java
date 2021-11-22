package com.course.coursers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {

    @JsonIgnore
    JdbcTemplate jdbcTemplate;

    private int id;
    private int type;
    private List<Integer> childs;
    private int diagram;
    private String description;


    public Block(int id, int type, List<Integer> childs, int diagram, String description, DataSource dataSource) {
        this.id = id;
        this.type = type;
        this.childs = childs;
        this.diagram = diagram;
        this.description = description;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




    public boolean insertSelf(){
        jdbcTemplate.execute("insert into blocks " +
                                    "values ('" + id + "','" + type + "','" + childs.toString().replace("[","{").replace("]","}") + "','" + diagram + "','" + description + "');");
        return true;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Integer> getChilds() {
        return childs;
    }

    public void setChilds(List<Integer> childs) {
        this.childs = childs;
    }

    public int getDiagram() {
        return diagram;
    }

    public void setDiagram(int diagram) {
        this.diagram = diagram;
    }
}
