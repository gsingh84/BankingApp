package com.bank.model;

public class Employee
{
    private PersonalInfo info;

    public Employee(PersonalInfo info)
    {
        this.info = info;
    }

    public PersonalInfo getEmployee()
    {
        return info;
    }

    @Override
    public String toString()
    {
        return info.toString();
    }
}
