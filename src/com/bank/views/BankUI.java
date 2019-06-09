package com.bank.views;

import com.bank.controller.Controller;
import com.bank.model.beans.Customer;
import com.bank.model.database.ConnectionConfig;
import com.bank.model.enums.AccountType;
import external.BatchProcessor;

import java.util.*;

public class BankUI
{
    private static Scanner console = new Scanner(System.in);
    private static Controller controller = new Controller();

    public static void main(String[] args)
    {
        BatchProcessor processor = new BatchProcessor();
        processor.monitorDir();
//        processor.readXml();
//        ConnectionConfig.closeConnection();
    }

    public static void printMenus()
    {
        printMainMenu();
    }

    public static void printMainMenu()
    {
        System.out.println("Welcome to Online Banking");
        System.out.println("-------------------------");
        System.out.println("Press enter to Login...");
        String input = console.nextLine();

        printLoginMenu();
    }

    public static void printLoginMenu()
    {
        boolean isValid = false;

        while (!isValid)
        {
            System.out.println("Enter your email: ");
            String email = console.nextLine();
            System.out.println("Enter your password: ");
            String password = console.nextLine();

            isValid = controller.login(email, password);

            if (!isValid)
            {
                System.out.println("Incorrect email or password, please try again!!");
            }
        }

        if (controller.getLoginType().equals(AccountType.EMPLOYEE))
        {
            printEmployeeMenu();
        }
        else
        {
            printCustomerMenu();
        }
    }

    public static void printEmployeeMenu()
    {
        String input = "0";

        while (!input.equals("5"))
        {
            employeeMenu();
            input = console.nextLine();

            switch (input)
            {
                case "1":
                    form();
                    break;
                case "2":
                    printCustomerMenu();
                    break;
                case "3":
                    break;
                case "4":
                    printCustomers();
                    break;
                case "5":
                    System.out.println("Logging Out...");
                    break;
                default:
                    System.out.println("Invalid Choice !");
            }
        }

        printLoginMenu();
    }
    private static void employeeMenu()
    {
        System.out.println("1. Create customer account");
        System.out.println("2. Close customer account");
        System.out.println("3. Add Employee");
        System.out.println("4. Customers list");
        System.out.println("5. Log Out");
    }

    public static void printCustomerMenu()
    {
        String input = "0";

        while (!input.equals("4"))
        {
            input = console.nextLine();
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Log Out");
        }

        printLoginMenu();
    }

    public static void form()
    {
        Map<String, String> data = new HashMap<>();

        System.out.println("First name: ");
        String firstName = console.nextLine();
        data.put("firstName", firstName);

        System.out.println("Last name: ");
        String lastName = console.nextLine();
        data.put("lastName", lastName);

        System.out.println("Birth Date: ");
        String birthDate = console.nextLine();
        data.put("birthdate", birthDate);

        System.out.println("Email: ");
        String newEmail = console.nextLine();
        data.put("email", newEmail);

        System.out.println("Address");
        System.out.println("Street: ");
        String street = console.nextLine();
        data.put("street", street);

        System.out.println("Enter city & state (separated by comma): ");
        String cityState = console.nextLine();
        data.put("cityState", cityState);

        System.out.println("Zip: ");
        String zip = console.nextLine();
        data.put("zip", zip);

        controller.createCustomer(data);
    }

    public static void printCustomers()
    {
        List<Customer> customers = controller.getCustomers();

        for (Customer customer : customers)
        {
            System.out.println(customer.toString());
            System.out.println("---");
        }
    }
}
