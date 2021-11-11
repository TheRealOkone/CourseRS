package com.course.coursers.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {

    @JsonIgnore
    JdbcTemplate jdbcTemplate;

    private int id;
    private int type;
    private List<Integer> childs;

    public Block(int id, int type, List<Integer> childs, DataSource dataSource) {
        this.id = id;
        this.type = type;
        this.childs = childs;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




    public boolean insertSelf(){
        jdbcTemplate.execute("insert into blocks " +
                                    "values ('" + id + "','" + type + "','" + childs.toString().replace("[","{").replace("]","}") + "');");
        return true;
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
}
