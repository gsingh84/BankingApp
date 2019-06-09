package com.bank.model.database;

import com.bank.model.beans.Address;
import com.bank.model.beans.Customer;
import com.bank.model.beans.PersonalInfo;
import com.bank.model.enums.AccountType;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class DbObject
{
    private Database database;
    private Map<String, PersonalInfo> users;

    public DbObject()
    {
        database = new Database();
        users = new HashMap<>();
    }

    public void addCustomer(Customer customer)
    {
        String[] cols = {"firstname", "lastname", "birthdate", "email", "password"};
        PersonalInfo info = customer.getCustomer();
        info.setPassword(encrypt("password01"));
        int lastId = database.insert("users", cols, customer.getCustomer());

        cols = new String[]{"customer_id", "street", "city", "state", "zip"};
        Address address = customer.getAddress();
        address.setCustomerId(lastId);
        database.insert("address", cols, address);
    }

    public AccountType getLoginType(String email)
    {
        return users.get(email).getUserType();
    }

    public boolean isValidAccount(String email, String password)
    {
        fetch();

        if (!users.containsKey(email))
            return false;

        return users.get(email).getPassword().equals(encrypt(password));
    }

    private void fetch()
    {
        users = database.fetch();
    }

    private String encrypt(String password)
    {
        String sha1 = "";

        if (!password.isEmpty())
        {
            try
            {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                digest.update(password.getBytes("utf8"));
                sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }

        return sha1;
    }
}








//    public Map<String, PersonalInfo> fetch()
//    {
//        ResultSet result = select("users", new HashMap<>());
//        users.clear();
//
//        try
//        {
//            while (result.next())
//            {
//                PersonalInfo info = (PersonalInfo) factory.fetch(result, new PersonalInfo());
//
//                users.put(result.getString("email"), info);
//            }
//        }
//        catch (SQLException ex)
//        {
//            ex.printStackTrace();
//        }
//
//        return users;
//    }

//    private ResultSet select(String table, Map<String, String> options)
//    {
//        String query = selectQuery(table, options);
//        ResultSet resultSet = null;
//
//        try
//        {
//            PreparedStatement psmt = conn.prepareStatement(query);
//
//            resultSet = psmt.executeQuery();
//
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return resultSet;
//    }
//
//    private String selectQuery(String table, Map<String, String> options)
//    {
//        StringBuilder query = new StringBuilder();
//
//        query.append("SELECT * FROM " + table);
//
//        if (!options.isEmpty())
//        {
//            query.append(" WHERE ");
//            String prefix = "";
//
//            for (String col : options.keySet())
//            {
//                query.append(prefix);
//                prefix = " AND ";
//                query.append(col + " = '" + options.get(col) + "'");
//            }
//        }
//
//        return query.toString();
//    }
//
//    private int insert(String table, String[] columns, Object object)
//    {
//        String query = insertQuery(table, columns);
//        int lastInsertedId = -1;
//
//        try
//        {
//            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            lastInsertedId = factory.insertObject(pstmt, object);
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//
//        return lastInsertedId;
//    }
//
//    private String insertQuery(String table, String[] columns)
//    {
//        StringBuilder query = new StringBuilder();
//        query.append("INSERT INTO " + table + "(");
//
//        String prefix = "";
//        for (String col : columns)
//        {
//            query.append(prefix);
//            prefix = ", ";
//            query.append(col);
//        }
//        query.append(") VALUES (");
//
//        prefix = "";
//        for (int i = 0; i < columns.length; i++)
//        {
//            query.append(prefix);
//            prefix = ", ";
//            query.append("?");
//        }
//        query.append(")");
//
//        return query.toString();
//    }