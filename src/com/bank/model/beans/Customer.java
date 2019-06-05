package com.bank.model.beans;

public class Customer
{
    private PersonalInfo info;
    private Address address;

    public Customer(PersonalInfo info, Address address)
    {
        this.info = info;
        this.address = address;
    }

    public Customer()
    {
    }

    public PersonalInfo getCustomer()
    {
        return info;
    }

    public Address getAddress()
    {
        return address;
    }

    @Override
    public String toString()
    {
        return info.toString() + "\n" + address.toString();
    }
}
