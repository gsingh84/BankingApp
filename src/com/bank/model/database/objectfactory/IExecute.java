package com.bank.model.database.objectfactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IExecute
{
    public int insert(PreparedStatement psmt, Object object) throws SQLException;
    public Object fetch(ResultSet resultSet) throws SQLException;

}
