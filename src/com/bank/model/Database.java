package com.bank.model;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Database
{
    private List<Customer> customers;
    private List<Employee> employees;

    private Map<String, Pair<String, LoginType>> accounts;

    public Database()
    {
        customers = new LinkedList<>();
        employees = new LinkedList<>();
        accounts = new HashMap<>();
        addEmployee();
    }

    public void addEmployee()
    {
        String[] emails = {"admin@bank.com", "employee1@bank.com", "employee2@bank.com"};

        for (String email : emails)
        {
            PersonalInfo info = new PersonalInfo("", "", email, null);
            info.setPassword("passw01");
            accounts.put(email, new Pair<>(info.getPassword(), LoginType.EMPLOYEE));
            employees.add(new Employee(info));
        }
    }

    public void addCustomer(Customer customer)
    {
        customers.add(customer);
        PersonalInfo info = customer.getCustomer();
        accounts.put(info.getEmail(), new Pair<>(info.getPassword(), LoginType.CUSTOMER));
    }

    public boolean isValidAccount(String email, String password)
    {
        Pair obj = accounts.get(email);
        return accounts.containsKey(email) && obj.getKey().equals(password);
    }

    public LoginType getLoginType(String email)
    {
        Pair obj = accounts.get(email);
        return (LoginType) obj.getValue();
    }

    public List<Customer> getCustomers()
    {
        return customers;
    }

    public List<Employee> getEmployees()
    {
        return employees;
    }
}
