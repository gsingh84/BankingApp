package com.bank.model.beans;

import com.bank.model.enums.AccountType;

import java.sql.Date;

public class PersonalInfo
{
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String password;
    private AccountType userType;

    public PersonalInfo(String firstName, String lastName, String email, Date birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public PersonalInfo()
    {
    }

    public void setUserType(AccountType userType)
    {
        this.userType = userType;
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

    public Date getBirthDate()
    {
        return birthDate;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public AccountType getUserType()
    {
        return userType;
    }

    @Override
    public String toString()
    {
        return firstName + " " + lastName + "\n"
                + email + "\n" + birthDate;
    }
}
