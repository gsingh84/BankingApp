package com.bank.model.beans;

import com.bank.model.enums.AccountType;

import java.util.Random;

public class Account
{
    private int customerId;
    private AccountType accountType;
    private int accountNo;
    private double depositAmt;
    private double withdrawAmt;
    private double totalBalance;

    public Account(AccountType accountType, int accountNo)
    {
        this.accountType = accountType;
        this.accountNo = accountNo;
    }

    public Account(int accountNo)
    {
        this.accountNo = accountNo;
    }

    public static int generateAccount(int nums) {
        int fraction = (int) Math.pow(10, nums - 1);
        return fraction + new Random().nextInt(9 * fraction);
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
