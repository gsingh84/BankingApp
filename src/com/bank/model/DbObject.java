package com.bank.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DbObject
{
    private Connection conn;

    public DbObject()
    {
        this.conn = ConnectionConfig.getConnection();
    }

    public void addCustomer()
    {
        StringBuilder query = new StringBuilder();
    }

    public List<String> getColumns(String table)
    {
        List<String> columns = new LinkedList<>();

        try
        {
            Statement stmt = conn.createStatement();
            String query = "SELECT COLUMN_NAME\n" +
                    "  FROM INFORMATION_SCHEMA.COLUMNS\n" +
                    "  WHERE TABLE_SCHEMA = 'bank' AND TABLE_NAME = '" + table + "'";
            ResultSet results = stmt.executeQuery(query);

            while (results.next())
            {
                columns.add(results.getString(1));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return columns;
    }
}



//            Statement stmt = conn.createStatement();
//
//            ResultSet results = stmt.executeQuery("select * from demo");
//
//            while (results.next())
//            {
//                System.out.println(results.getString("first") + " " + results.getString("last"));
//            }
