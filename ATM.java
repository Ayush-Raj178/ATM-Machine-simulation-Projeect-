import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {

    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    // Constructor to initialize the ATM simulation with a balance
    public ATMSimulation(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    // Function to set a new PIN
    public void setPIN(String newPin) {
        this.pin = newPin;
        System.out.println("PIN successfully set.");
    }

    // Function to display the main menu
    public void displayMenu() {
        System.out.println("\n===== ATM Menu =====");
        System.out.println("1. Check Account Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Change PIN");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
    }

    // Function to check account balance
    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
        transactionHistory.add("Checked balance: ₹" + balance);
    }

    // Function to withdraw cash
    public void withdrawCash(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a valid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Successfully withdrawn: ₹" + amount);
            transactionHistory.add("Withdrew: ₹" + amount);
        }
    }

    // Function to deposit cash
    public void depositCash(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a valid amount.");
        } else {
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
            transactionHistory.add("Deposited: ₹" + amount);
        }
    }

    // Function to change PIN
    public void changePIN(String currentPin, String newPin) {
        if (currentPin.equals(pin)) {
            pin = newPin;
            System.out.println("PIN successfully changed.");
            transactionHistory.add("PIN changed.");
        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    // Function to display transaction history
    public void displayTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("===== Transaction History =====");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Function to verify PIN
    public boolean verifyPIN(String enteredPin) {
        return enteredPin.equals(pin);
    }

    public static void main(String[] args) {
        // Initializing the ATM with a starting balance of ₹1000
        ATMSimulation atm = new ATMSimulation(1000.00);
        Scanner scanner = new Scanner(System.in);

        // Set the PIN when the program starts
        System.out.print("Please set a new PIN for your account: ");
        String newPin = scanner.nextLine();
        atm.setPIN(newPin);

        boolean running = true;
        while (running) {
            // User PIN verification at the start of each session
            System.out.print("Enter your PIN: ");
            String enteredPin = scanner.nextLine();
            if (!atm.verifyPIN(enteredPin)) {
                System.out.println("Incorrect PIN. Exiting...");
                return;  // Exiting if the PIN is wrong
            }

            boolean sessionActive = true;
            while (sessionActive) {
                atm.displayMenu();
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                // Replacing switch-case with if-else conditions
                if (choice == 1) {
                    atm.checkBalance();
                } else if (choice == 2) {
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdrawCash(withdrawAmount);
                } else if (choice == 3) {
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.depositCash(depositAmount);
                } else if (choice == 4) {
                    scanner.nextLine();  // Consume newline left-over
                    System.out.print("Enter current PIN: ");
                    String currentPin = scanner.nextLine();
                    System.out.print("Enter new PIN: ");
                    String newPIN = scanner.nextLine();
                    atm.changePIN(currentPin, newPIN);
                } else if (choice == 5) {
                    atm.displayTransactionHistory();
                } else if (choice == 6) {
                    System.out.println("Exiting session...");
                    sessionActive = false;
                } else {
                    System.out.println("Invalid option. Please choose again.");
                }
            }

            // Ask if the user wants to exit the program
            System.out.print("Do you want to exit the ATM system? (yes/no): ");
            scanner.nextLine(); // To consume the leftover newline
            String exitResponse = scanner.nextLine();
            if (exitResponse.equalsIgnoreCase("yes")) {
                running = false;
            }
        }

        scanner.close();
        System.out.println("Thank you for using our ATM. Goodbye!");
    }
}
