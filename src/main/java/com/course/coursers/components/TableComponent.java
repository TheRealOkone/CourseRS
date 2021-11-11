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
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(blocks))) FROM test;", String.class);
        return str;
    }

    public String getBlockById(int id){
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(blocks))) FROM blocks  WHERE id = " + id + ";", String.class);
        return str;
    }

    public String deleteBlockById(int id){
        String str = jdbcTemplate.queryForObject("DELETE FROM blocks  WHERE id = " + id + ";", String.class);
        return str;
    }

    public String updateBlockById(int id, int type, List<Integer> childs){
        String str = jdbcTemplate.queryForObject("update blocks  set type = " + type + ", set childs = " + childs.toString().replace("[","{").replace("]","}") + " where id = " + id + ";", String.class);
        return str;
    }

    public void prepareBase(){
        String sql =
                "CREATE TABLE IF NOT EXISTS public.blocks\n" +
                "(\n" +
                "    id integer,\n" +
                "    type integer,\n" +
                "    childs integer[]\n" +
                ")\n" +
                "\n" +
                "TABLESPACE pg_default;\n" +
                "\n" +
                "ALTER TABLE public.blocks\n" +
                "    OWNER to postgres;";

        jdbcTemplate.execute(sql);
    }

    public void deleteBase(){
        jdbcTemplate.execute("DROP DATABASE pg_course;");
    }



}
