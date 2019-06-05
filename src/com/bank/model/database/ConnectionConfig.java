package com.bank.model.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConnectionConfig
{
    private static Connection conn = null;

    public static Connection getConnection()
    {
        try
        {
            List<String> config = readFile();
            conn = DriverManager.getConnection(config.get(0), config.get(1), config.get(2));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return conn;
    }

    public static void closeConnection()
    {
        if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                };
        }
    }

    public static List<String> readFile()
    {
        List<String> list = new LinkedList<>();

        try(Scanner input = new Scanner(new File("resources/config.text")))
        {
            while (input.hasNextLine())
            {
                String line = input.nextLine();
                String[] parts = line.split("->");
                list.add(parts[1]);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found! " + e.getMessage());
        }

        return list;
    }
}
