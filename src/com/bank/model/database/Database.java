package com.bank.model.database;

import com.bank.model.beans.PersonalInfo;
import com.bank.model.database.objectfactory.ObjectFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database
{
    private Connection conn;
    private ObjectFactory factory;
    private Map<String, PersonalInfo> users;

    public Database()
    {
        this.conn = ConnectionConfig.getConnection();
        this.factory = new ObjectFactory();
        users = new HashMap<>();
    }

    public Map<String, PersonalInfo> fetch()
    {
        ResultSet result = select("users", new HashMap<>());
        users.clear();

        try
        {
            while (result.next())
            {
                PersonalInfo info = (PersonalInfo) factory.fetch(result, new PersonalInfo());

                users.put(result.getString("email"), info);
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return users;
    }

    public int insert(String table, String[] columns, Object object)
    {
        String query = insertQuery(table, columns);
        int lastInsertedId = -1;

        try
        {
            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            lastInsertedId = factory.insertObject(pstmt, object);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return lastInsertedId;
    }

    private String insertQuery(String table, String[] columns)
    {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO " + table + "(");

        String prefix = "";
        for (String col : columns)
        {
            query.append(prefix);
            prefix = ", ";
            query.append(col);
        }
        query.append(") VALUES (");

        prefix = "";
        for (int i = 0; i < columns.length; i++)
        {
            query.append(prefix);
            prefix = ", ";
            query.append("?");
        }
        query.append(")");

        return query.toString();
    }

    public ResultSet select(String table, Map<String, String> options)
    {
        String query = selectQuery(table, options);
        ResultSet resultSet = null;

        try
        {
            PreparedStatement psmt = conn.prepareStatement(query);

            resultSet = psmt.executeQuery();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return resultSet;
    }

    private String selectQuery(String table, Map<String, String> options)
    {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM " + table);

        if (!options.isEmpty())
        {
            query.append(" WHERE ");
            String prefix = "";

            for (String col : options.keySet())
            {
                query.append(prefix);
                prefix = " AND ";
                query.append(col + " = '" + options.get(col) + "'");
            }
        }

        return query.toString();
    }
}
