package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class Catalogue {
    private String code;
    private String libelle;

    public Catalogue(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}

@RestController
public class TestController {

    private Connection connection;

    public TestController() throws SQLException {
        //connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/uninov", "devis", "devis");
    }

    @GetMapping("ping")
    public List<Catalogue> ping() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from catalogue");

        List<Catalogue> catalogues = new ArrayList();
        while(rs.next()) {
           catalogues.add(new Catalogue(rs.getString("code"), rs.getString("libelle")));
        }

        return catalogues;
    }

}
