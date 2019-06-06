package com.bank.model.database.objectfactory;

import com.bank.model.HandleAccounts;
import com.bank.model.beans.PersonalInfo;
import com.bank.model.enums.AccountType;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalInfoDb implements IObject
{
    @Override
    public int insert(PreparedStatement psmt, Object object) throws SQLException
    {
        PersonalInfo info = (PersonalInfo) object;
        psmt.setString(1, info.getFirstName());
        psmt.setString(2, info.getLastName());
        psmt.setDate(3, info.getBirthDate());
        psmt.setString(4, info.getEmail());
        psmt.setString(5, info.getPassword());

        psmt.executeUpdate();

        int id;
        try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        return id;
    }

    @Override
    public Object fetch(ResultSet result) throws SQLException
    {
        Date birthDate = HandleAccounts.convertDate(result.getString("birthdate"));
        PersonalInfo info = new PersonalInfo(result.getString("firstname"),
                result.getString("lastname"),
                result.getString("email"), birthDate);
        AccountType loginType = AccountType.valueOf(result.getString("usertype"));
        info.setUserType(loginType);
        info.setPassword(result.getString("password"));

        return info;
    }
}
