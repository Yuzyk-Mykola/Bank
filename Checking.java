package org.example;

public class Checking extends Account {
    private static final double WITHDRAWFEE = 3.0;
    private static final double MONTHLYFEE = 10.0;

    public Checking(double balance, int accNumber) {
        super(balance, accNumber);
    }

    public double getWithdrawFee() {
        return WITHDRAWFEE;
    }

    @Override
    public double withdraw(double value) {
        if (getBalance() >= value + WITHDRAWFEE) {
            super.withdraw(value); // Call the parent class withdraw method
            super.withdraw(WITHDRAWFEE); // Deduct the withdraw fee
        } else {
            System.out.println("Insufficient balance including withdrawal fee.");
        }
        return getBalance();
    }

    @Override
    public void endOfMonth() {
        super.withdraw(MONTHLYFEE); // Deduct the monthly fee
        System.out.println("Monthly fee of " + MONTHLYFEE + " has been deducted.");
    }

    @Override
    public String toString() {
        return super.toString() + "\nThis is a checking account";
    }
}
