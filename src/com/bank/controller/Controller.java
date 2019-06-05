package com.bank.controller;

import com.bank.model.beans.Customer;
import com.bank.model.HandleAccounts;
import com.bank.model.enums.AccountType;

import java.util.List;
import java.util.Map;

public class Controller
{
    private HandleAccounts bank;

    public Controller()
    {
        bank = new HandleAccounts();
    }

    public boolean login(String email, String password)
    {
        return bank.login(email, password);
    }

    public AccountType getLoginType()
    {
        return bank.getAccountType();
    }

    public void createCustomer(Map<String, String> data)
    {
        bank.createCustomer(data);
    }

    public List<Customer> getCustomers()
    {
        return bank.getCustomers();
    }
}
