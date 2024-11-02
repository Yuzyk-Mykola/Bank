package org.example;

public class Saving extends Account {
    private static final double WITHDRAWFEE = 15.0;
    private static final double INFCONST = 0.005; // 0.5% as a constant
    private boolean connectedToSaving = false;
    private Account checking;

    public Saving(double balance, int accNumber) {
        super(balance, accNumber);
    }

    public void connect(Account checkingAccount) {
        if (checkingAccount instanceof Checking) {
            this.checking = checkingAccount;
            this.connectedToSaving = true;
            System.out.println("Connected to checking account.");
        } else {
            System.out.println("Provided account is not a Checking account.");
        }
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
        double inflationCompensation = getBalance() * INFCONST;
        deposit(inflationCompensation); // Add inflation compensation

        if (connectedToSaving && checking != null) {
            double transferAmount = checking.getBalance() * 0.05;
            checking.withdraw(transferAmount); // Withdraw 5% from checking
            deposit(transferAmount); // Deposit to savings
            System.out.println("Transferred " + transferAmount + " from checking to savings.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nThis is a saving account";
    }
}
