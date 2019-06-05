package com.bank.model;

import com.bank.model.beans.Address;
import com.bank.model.beans.Customer;
import com.bank.model.beans.PersonalInfo;
import com.bank.model.database.DatabaseOld;
import com.bank.model.database.DbObject;
import com.bank.model.enums.AccountType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HandleAccounts
{
    private DatabaseOld database;
    private AccountType type;
    private DbObject dbObject;

    public HandleAccounts()
    {
        database = new DatabaseOld();
        dbObject = new DbObject();
    }

    public boolean login(String email, String password)
    {
        boolean isValid = dbObject.isValidAccount(email, password);
        if (isValid)
            type = dbObject.getLoginType(email);

        return isValid;
    }

    public AccountType getAccountType()
    {
        return type;
    }

    public void createCustomer(Map<String, String> data)
    {
        String[] parts = data.get("cityState").split(",");
        Address address = new Address(data.get("street"), parts[0], parts[1], data.get("zip"));


        PersonalInfo info = new PersonalInfo(data.get("firstName"), data.get("lastName"),
                data.get("email"), convertDate(data.get("birthdate")));

        Customer customer = new Customer(info, address);

        dbObject.addCustomer(customer);
    }

    public static java.sql.Date convertDate(String date)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = null;

        try
        {
            Date birthDate = format.parse(date);
            sqlDate = new java.sql.Date(birthDate.getTime());

        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        return sqlDate;
    }

    public List<Customer> getCustomers()
    {
        return database.getCustomers();
    }
}
