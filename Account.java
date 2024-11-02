package org.example;

public abstract class Account {
    private double balance;
    private int accNumber;

    public Account(double balance, int accNumber) {
        this.balance = balance;
        this.accNumber = accNumber;
    }

    public double deposit(double value) {
        balance += value;
        return balance;
    }


    public double withdraw(double value) {
        if (balance >= value) {
            balance -= value;
        } else {
            System.out.println("Insufficient balance");
        }
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccNumber() {
        return accNumber;
    }

    public abstract void endOfMonth();

    @Override
    public String toString() {
        return "Account information: ID: " + accNumber + ", Balance: " + balance;
    }
}
