package com.bank.model;

import java.util.List;
import java.util.Map;

public class HandleAccounts
{
    private Database database;
    private LoginType type;

    public HandleAccounts()
    {
        database = new Database();
    }

    public boolean login(String email, String password)
    {
        boolean isValid = database.isValidAccount(email, password);
        if (isValid)
            type = database.getLoginType(email);

        return isValid;
    }

    public LoginType getAccountType()
    {
        return type;
    }

    public void createCustomer(Map<String, String> data)
    {
        String[] parts = data.get("cityState").split(",");
        Address address = new Address(data.get("street"), parts[0], parts[1], data.get("zip"));

        PersonalInfo info = new PersonalInfo(data.get("firstName"), data.get("lastName"),
                data.get("email"), address);
        info.setPassword("password01");

        Customer customer = new Customer(info);

        database.addCustomer(customer);
    }

    public List<Customer> getCustomers()
    {
        return database.getCustomers();
    }
}
