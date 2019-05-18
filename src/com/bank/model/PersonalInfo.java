package com.bank.model;

public class PersonalInfo
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Address address;

    public PersonalInfo(String firstName, String lastName, String email, Address address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName + "\n"
                + email + "\n" + address.toString();
    }
}
