package com.example.demo.services;

import com.example.demo.api.LoveCalculatorApi;
import com.example.demo.api.LoveResult;
import com.example.demo.configuration.DbConfiguration;
import com.example.demo.dto.LoveHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LoveCalculatorService {

    @Autowired
    private DbConfiguration dbConfiguration;

    @Autowired
    private LoveCalculatorApi loveCalculatorApi;

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        connection = DriverManager.getConnection(
                dbConfiguration.getUrl(),
                dbConfiguration.getUser(),
                dbConfiguration.getPwd()
        );
    }

    public LoveResult testApi(String firstName, String secondName) {
        return this.loveCalculatorApi.bringMeLove(firstName, secondName);
    }

    public List<String> getAllNames() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("select * from names");
        List<String> names = new ArrayList<>();
        while(rs.next()) {
            names.add(rs.getString("name"));
        }
        return names;
    }

    public List<LoveHistory> getHistory(Optional<String> name) throws SQLException {
        String query = "select f.name firstname, s.name secondname, h.result"
                     + " from history h"
                     + " left join names f on h.firstname_id = f.id"
                     + " left join names s on h.secondname_id = s.id";
        if (name.isPresent()) {
            query =  query.concat(" where f.name = ? or s.name = ?");
        }

        PreparedStatement st = connection.prepareStatement(query);
        if (name.isPresent()) {
            st.setString(1, name.get());
            st.setString(2, name.get());
        }

        ResultSet rs = st.executeQuery();
        List<LoveHistory> history = new ArrayList<>();
        while(rs.next()) {
            history.add(new LoveHistory(
                    rs.getString("firstname"),
                    rs.getString("secondname"),
                    rs.getInt("result")
            ));
        }
        return history;
    }

    public LoveHistory love(String firstname, String secondname) throws SQLException {
        LoveResult loveResult = loveCalculatorApi.bringMeLove(firstname,secondname);
        PreparedStatement st = connection.prepareStatement("insert into history values (?, ?, ?)");
        st.setInt(1, findOrCreateName(firstname));
        st.setInt(2, findOrCreateName(secondname));
        st.setInt(3, loveResult.getPercentage());
        st.executeUpdate();
        return new LoveHistory(firstname, secondname, loveResult.getPercentage());
    }

    private Integer findOrCreateName(String name) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "select id from names where name = ?",
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        st.setString(1, name);
        ResultSet rs = st.executeQuery();
        rs.last();

        if (rs.getRow() == 0) {
            st = connection.prepareStatement(
                    "insert into names (name) values (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, name);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }

        return rs.getInt("id");
    }

}
