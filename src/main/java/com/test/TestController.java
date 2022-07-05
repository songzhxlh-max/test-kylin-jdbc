package com.test;

import org.apache.kylin.jdbc.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

@RequestMapping("/test_jdbc")
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Value("${kylin.query.sql}")
    private String kylinQuerySql;

    @RequestMapping("/query")
    public String query(){
        try {
            Connection conn = dataSource.getConnection();
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery(kylinQuerySql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
            conn.close();
            return "ok";
        } catch (Exception e) {
            logger.error("query exception", e);
        }
        return "error";
    }

    @RequestMapping("/query2")
    public String query2(){
        try {
            Connection conn = dataSource.getConnection();
            Statement state = conn.createStatement();
            ResultSet resultSet = state.executeQuery(kylinQuerySql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }
            return "ok";
        } catch (Exception e) {
            logger.error("query exception", e);
        }
        return "error";
    }

    @RequestMapping("/test")
    public String test(){
        return "ok";
    }

    @RequestMapping("/test2")
    public void test2() throws Exception {
        Driver driver = (Driver) Class.forName("org.apache.kylin.jdbc.Driver").newInstance();
        Properties info = new Properties();
        info.put("user", "XXX");
        info.put("password", "XXX");
        //info.put("ssl","true");
        Connection conn = driver.connect("jdbc:kylin://localhost:7070/project_name", info);
        Statement state = conn.createStatement();
        ResultSet resultSet = state.executeQuery("select * from test_table");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }
    }

}
