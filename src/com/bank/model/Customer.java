package com.bank.model;

public class Customer
{
    private PersonalInfo info;

    public Customer(PersonalInfo info)
    {
        this.info = info;
    }

    public PersonalInfo getCustomer()
    {
        return info;
    }

    @Override
    public String toString()
    {
        return info.toString();
    }
}
