package batchprocessor;

import com.bank.model.HandleAccounts;
import com.bank.model.beans.Account;
import com.bank.model.beans.Address;
import com.bank.model.beans.PersonalInfo;
import com.bank.model.database.Database;
import com.bank.model.enums.AccountType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXml implements Runnable
{
    private Database database;
    private String filename;

    public ReadXml(Database database, String filename)
    {
        this.database = database;
        this.filename = filename;
    }

    @Override
    public void run()
    {
        readXml();
    }

    private void readXml()
    {
        File file = new File("resources/batchprocessor/" + filename);
        DocumentBuilderFactory dcBuilderFac = DocumentBuilderFactory.newInstance();

        try
        {
            DocumentBuilder dbBuilder = dcBuilderFac.newDocumentBuilder();
            Document doc = dbBuilder.parse(file);

            NodeList list = doc.getElementsByTagName("Account");

//            addToDb(list);
            boolean delete = file.delete();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void addToDb(NodeList list)
    {
        String[] usersCols = {"firstname", "lastname", "birthdate", "email", "password"};
        String[] addressCols = {"customer_id", "street", "city", "state", "zip"};
        String[] accCols = {"customer_id", "accountType", "accountnumber", "bank"};

        for (int i = 0; i < list.getLength(); i++)
        {
            Node item = list.item(i);

            if (item.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) item;

                PersonalInfo details = customerDetails(element);
                int lastId = database.insert("users", usersCols, details);

                Address address = customerAddress(element);
                address.setCustomerId(lastId);
                database.insert("address", addressCols, address);

                Account account = customerAcc(element);
                account.setCustomerId(lastId);
                database.insert("accounts", accCols, account);
            }
        }
    }

    private PersonalInfo customerDetails(Element element)
    {
        String first = element.getElementsByTagName("Firstname").item(0).getTextContent();
        String last = element.getElementsByTagName("Lastname").item(0).getTextContent();
        String birthDate = element.getElementsByTagName("Birthdate").item(0).getTextContent();

        PersonalInfo details = new PersonalInfo(first, last, "", HandleAccounts.convertDate(birthDate));
        details.setPassword("");

        return details;
    }

    private Address customerAddress(Element element)
    {
        String street = element.getElementsByTagName("Street").item(0).getTextContent();
        String city = element.getElementsByTagName("City").item(0).getTextContent();
        String state = element.getElementsByTagName("State").item(0).getTextContent();
        String zip = element.getElementsByTagName("Zip").item(0).getTextContent();

        return new Address(street, city, state, zip);
    }

    private Account customerAcc(Element element)
    {
        int accountNum = Integer.parseInt(element.getElementsByTagName("AccountNum").item(0).getTextContent());
        AccountType accType = AccountType.valueOf(element.getElementsByTagName("AccType").item(0).getTextContent());
        String bank = element.getElementsByTagName("Bank").item(0).getTextContent();

        Account account = new Account(accType, accountNum);
        account.setBank(bank);

        return account;
    }
}
