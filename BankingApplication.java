import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    public Account(String accountHolderName, String accountNumber) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited Rs." + amount);
        } else {
            System.out.println("Error: Deposit amount must be greater than 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew Rs." + amount);
        } else if (amount > balance) {
            System.out.println("Error: Insufficient funds.");
        } else {
            System.out.println("Error: Withdrawal amount must be greater than 0.");
        }
    }
}

public class BankingApplication {

    private static ArrayList<Account> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Banking Application Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter a unique account number: ");
        String accountNumber = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.println("Error: Account number already exists.");
                return;
            }
        }

        accounts.add(new Account(name, accountNumber));
        System.out.println("Account created successfully!");
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        scanner.nextLine();
        String accountNumber = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: Rs.");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        scanner.nextLine();
        String accountNumber = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: Rs.");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        scanner.nextLine();
        String accountNumber = scanner.nextLine();

        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Account Balance: Rs." + account.getBalance());
        } else {
            System.out.println("Error: Account not found.");
        }
    }

    private static Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
