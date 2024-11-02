package org.example;

public class Bank {
    public static void main(String[] args) {
        Account[] accounts = new Account[4];

        // Initializing accounts
        accounts[0] = new Checking(5000, 100);
        accounts[1] = new Checking(1100, 101);
        accounts[2] = new Saving(40000, 102);
        accounts[3] = new Saving(20000, 103);

        // Connecting the savings account to the checking account
        ((Saving) accounts[2]).connect(accounts[1]);

        // Example usage of the currency conversion API
        double exchangeRate = CurrencyConverter.getExchangeRate("USD", "EUR");
        System.out.println("Exchange rate from USD to EUR: " + exchangeRate);

        // Displaying converted balances for all accounts
        System.out.println("\nBalances converted to EUR:\n-----------------------------------");
        for (Account account : accounts) {
            double convertedBalance = account.getBalance() * exchangeRate;
            System.out.printf("Account ID: %d, Balance in EUR: %.2f%n", account.getAccNumber(), convertedBalance);
        }

        // Performing operations on the accounts
        accounts[0].withdraw(1000);
        accounts[1].deposit(1200);
        accounts[2].withdraw(1243);
        accounts[3].deposit(1300);

        // End of month actions for all accounts
        System.out.println("\nEnd of month actions:\n-----------------------------------");
        for (Account account : accounts) {
            account.endOfMonth();
        }

        // Final end of month report
        System.out.println("\nEnd of month Report:\n-----------------------------------");
        for (Account account : accounts) {
            System.out.println(account);
        }

        // Additional statistics
        System.out.println("\nSummary of All Accounts:\n-----------------------------------");
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getBalance();
        }
        System.out.println("Total balance across all accounts: " + totalBalance);
        System.out.println("Average balance per account: " + (totalBalance / accounts.length));
    }
}
