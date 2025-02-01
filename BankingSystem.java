import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: ₹" + String.format("%.2f", balance));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Available balance: ₹" + String.format("%.2f", balance));
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: ₹" + String.format("%.2f", balance));
        }
    }

    public void displayAccountDetails() {
        System.out.println("--------------------------------------");
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Account Holder   : " + accountHolder);
        System.out.println("Balance          : ₹" + String.format("%.2f", balance));
        System.out.println("--------------------------------------");
    }

    public void transferMoney(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Transfer successful. ₹" + amount + " sent to " + recipient.getAccountHolder());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }
}

public class BankingSystem {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        scanner.nextLine();
        System.out.print("Enter Account Holder Name: ");
        String accountHolder = scanner.nextLine();
        System.out.print("Enter Initial Deposit Amount: ");
        double balance = scanner.nextDouble();
        accounts.add(new BankAccount(accountNumber, accountHolder, balance));
        System.out.println("Account created successfully!");
    }

    public static BankAccount findAccount(String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public static void depositMoney() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        BankAccount acc = findAccount(accountNumber);
        if (acc != null) {
            System.out.print("Enter Deposit Amount: ");
            acc.deposit(scanner.nextDouble());
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void withdrawMoney() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        BankAccount acc = findAccount(accountNumber);
        if (acc != null) {
            System.out.print("Enter Withdrawal Amount: ");
            acc.withdraw(scanner.nextDouble());
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void checkBalance() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        BankAccount acc = findAccount(accountNumber);
        if (acc != null) {
            System.out.println("Current Balance: ₹" + String.format("%.2f", acc.getBalance()));
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        System.out.println("\nList of All Accounts:");
        for (BankAccount acc : accounts) {
            acc.displayAccountDetails();
        }
    }

    public static void transferMoney() {
        System.out.print("Enter Your Account Number: ");
        String senderAccount = scanner.next();
        BankAccount sender = findAccount(senderAccount);
        if (sender == null) {
            System.out.println("Sender account not found.");
            return;
        }
        System.out.print("Enter Recipient Account Number: ");
        String recipientAccount = scanner.next();
        BankAccount recipient = findAccount(recipientAccount);
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }
        System.out.print("Enter Transfer Amount: ");
        double amount = scanner.nextDouble();
        sender.transferMoney(recipient, amount);
    }

    public static void deleteAccount() {
        System.out.print("Enter Account Number to Delete: ");
        String accountNumber = scanner.next();
        BankAccount acc = findAccount(accountNumber);
        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Banking System!");
            System.out.println("1. Create a New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Show All Accounts");
            System.out.println("6. Transfer Money");
            System.out.println("7. Delete Account");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> checkBalance();
                case 5 -> displayAllAccounts();
                case 6 -> transferMoney();
                case 7 -> deleteAccount();
                case 8 -> {
                    System.out.println("Thank you for using the Banking System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please enter a number between 1 and 8.");
            }
        }
    }
}
