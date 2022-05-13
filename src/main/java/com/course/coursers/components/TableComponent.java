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
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(blocks))) FROM blocks;", String.class);
        return str;
    }

    public String getTableAsJson(int diagram){
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(blocks))) FROM blocks where diagram =" + diagram + ";", String.class);
        return str;
    }

    public String getTableAsJsonDiag(){
        String str = jdbcTemplate.queryForObject("SELECT array_to_json(array_agg(row_to_json(diagrams))) FROM diagrams;", String.class);
        return str;
    }



    public void prepareBase(){
        String sqltab1 =
                "CREATE TABLE IF NOT EXISTS public.blocks\n" +
                        "(\n" +
                        "    id integer,\n" +
                        "    type integer,\n" +
                        "    childs integer[],\n" +
                        "    diagram integer,\n" +
                        "    description text,\n" +
                        "    CONSTRAINT blocks_diagram_fkey FOREIGN KEY (diagram)\n" +
                        "        REFERENCES public.diagrams (id) MATCH SIMPLE\n" +
                        "        ON UPDATE NO ACTION\n" +
                        "        ON DELETE NO ACTION\n" +
                        ")\n" +
                        "\n" +
                        "TABLESPACE pg_default;\n" +
                        "\n" +
                        "ALTER TABLE public.blocks\n" +
                        "    OWNER to qzdhjgztbhxcft;";


        String sqltab2 =
                "CREATE TABLE IF NOT EXISTS public.diagrams\n" +
                        "(\n" +
                        "    id integer NOT NULL,\n" +
                        "    name text COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                        "    description text COLLATE pg_catalog.\"default\",\n" +
                        "    CONSTRAINT diagrams_pkey PRIMARY KEY (id)\n" +
                        ")\n" +
                        "\n" +
                        "TABLESPACE pg_default;\n" +
                        "\n" +
                        "ALTER TABLE public.diagrams\n" +
                        "    OWNER to qzdhjgztbhxcft;";

        jdbcTemplate.execute(sqltab2);
        jdbcTemplate.execute(sqltab1);
    }

    public void deleteBase(){
        jdbcTemplate.execute("TRUNCATE public.diagrams CASCADE;");
        jdbcTemplate.execute("TRUNCATE public.blocks CASCADE;");
    }



}
