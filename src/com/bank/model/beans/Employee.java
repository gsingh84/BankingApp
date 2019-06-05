package com.bank.model.beans;

public class Employee
{
    private PersonalInfo info;
    private Address address;

    public Employee(PersonalInfo info, Address address)
    {
        this.info = info;
        this.address = address;
    }

    public Employee()
    {
    }

    public PersonalInfo getEmployee()
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
