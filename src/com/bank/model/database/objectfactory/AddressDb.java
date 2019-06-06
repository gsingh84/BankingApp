package com.bank.model.database.objectfactory;

import com.bank.model.beans.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDb implements IObject
{
    @Override
    public int insert(PreparedStatement psmt, Object object) throws SQLException
    {
        Address address = (Address) object;

        psmt.setInt(1, address.getCustomerId());
        psmt.setString(2, address.getStreet());
        psmt.setString(3, address.getCity());
        psmt.setString(4, address.getState());
        psmt.setString(5, address.getZip());

        return psmt.executeUpdate();
    }

    @Override
    public Object fetch(ResultSet resultSet) throws SQLException
    {
        return null;
    }
}
