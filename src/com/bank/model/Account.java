package com.bank.model;

import java.util.Random;

public class Account
{
    private int customerId;
    private String accountType;
    private int accountNo;
    private double depositAmt;
    private double withdrawAmt;
    private double totalBalance;

    public Account(String accountType, int accountNo)
    {
        this.accountType = accountType;
        this.accountNo = accountNo;
    }

    public void setDepositAmt(double depositAmt)
    {
        this.depositAmt = depositAmt;
    }

    public void setWithdrawAmt(double withdrawAmt)
    {
        this.withdrawAmt = withdrawAmt;
    }

    public int getAccountNo()
    {
        return accountNo;
    }

    public double getWithdrawAmt()
    {
        return withdrawAmt;
    }

    public double getTotalBalance()
    {
        return totalBalance;
    }
}
