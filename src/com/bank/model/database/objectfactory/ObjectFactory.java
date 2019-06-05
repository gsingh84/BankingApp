package com.bank.model.database.objectfactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ObjectFactory
{
    private Map<String, IExecute> instances;

    public ObjectFactory()
    {
        instances = new HashMap<>();
        instances.put("PersonalInfo", new PersonalInfoDb());
        instances.put("Address", new AddressDb());
    }

    public int insertObject(PreparedStatement psmt, Object object) throws SQLException
    {
        IExecute obj = instances.get(object.getClass().getSimpleName());
        return obj.insert(psmt, object);
    }

    public Object fetch(ResultSet resultSet, Object object) throws SQLException
    {
        IExecute obj = instances.get(object.getClass().getSimpleName());
        return obj.fetch(resultSet);
    }
}
