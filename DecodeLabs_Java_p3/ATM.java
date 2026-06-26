import java.util.Scanner;

public class ATM {

    // ✅ ATM has a BankAccount (Composition)
    private BankAccount account;
    private Scanner scanner;
    private boolean isAuthenticated;

    // ✅ Constructor
    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
        this.isAuthenticated = false;
    }

    // ============================================================
    //  DISPLAY METHODS (Customer Lobby / UI Layer)
    // ============================================================

    private void printDivider() {
        System.out.println("====================================");
    }

    private void printWelcome() {
        printDivider();
        System.out.println(" WELCOME TO DECODELABS BANK ATM");
        printDivider();
    }

    private void printMenu() {
        printDivider();
        System.out.println("   Account: " + account.getAccountHolder());
        System.out.println("   Card No: " + account.getAccountNumber());
        printDivider();
        System.out.println("  Please select an option:");
        System.out.println("  1️⃣  Check Balance");
        System.out.println("  2️⃣  Deposit Money");
        System.out.println("  3️⃣  Withdraw Money");
        System.out.println("  4️⃣  Exit / Eject Card");
        printDivider();
        System.out.print("  Enter your choice: ");
    }

    // ============================================================
    //  INPUT VALIDATION (Security Gate)
    // ============================================================

    // ✅ Safe integer input - never crashes
    private int getValidIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("    Invalid input! Enter a number: ");
            scanner.next(); // clear buffer
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // clear newline
        return value;
    }

    // ✅ Safe double input - never crashes
    private double getValidDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("    Invalid input! Enter a valid amount: ");
            scanner.next(); // clear buffer
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // clear newline
        return value;
    }

    // ============================================================
    //  AUTHENTICATION (PIN Entry)
    // ============================================================

    private boolean authenticate() {
        System.out.println("\n   Please enter your 4-digit PIN:");
        int attempts = 0;
        int maxAttempts = 3;

        while (attempts < maxAttempts) {
            System.out.print("  PIN: ");
            String enteredPin = scanner.nextLine().trim();

            if (account.validatePin(enteredPin)) {
                System.out.println("   Authentication successful!");
                return true;
            } else {
                attempts++;
                int remaining = maxAttempts - attempts;
                if (remaining > 0) {
                    System.out.println("  Wrong PIN! " + remaining + " attempt(s) remaining.");
                } else {
                    System.out.println("   Card blocked! Too many wrong attempts.");
                }
            }
        }
        return false;
    }

    // ============================================================
    //  TRANSACTION METHODS
    // ============================================================

    private void checkBalance() {
        printDivider();
        System.out.println("   BALANCE ENQUIRY");
        printDivider();
        System.out.println("  Account Holder : " + account.getAccountHolder());
        System.out.println("  Account Number : " + account.getAccountNumber());
        System.out.println("  Current Balance: ₹" + String.format("%.2f", account.getBalance()));
        printDivider();
    }

    private void depositMoney() {
        printDivider();
        System.out.println("   DEPOSIT");
        printDivider();
        System.out.print("  Enter deposit amount: ₹");
        double amount = getValidDoubleInput();
        account.deposit(amount);
        printDivider();
    }

    private void withdrawMoney() {
        printDivider();
        System.out.println("   WITHDRAWAL");
        printDivider();
        System.out.println("  Current Balance: ₹" + String.format("%.2f", account.getBalance()));
        System.out.print("  Enter withdrawal amount: ₹");
        double amount = getValidDoubleInput();
        account.withdraw(amount);
        printDivider();
    }

    // ============================================================
    //  MAIN FLOW (ATM State Management)
    // ============================================================

    public void start() {
        printWelcome();

        // STATE 1: Idle → Authenticate
        System.out.println("\n   Card Inserted...");
        isAuthenticated = authenticate();

        if (!isAuthenticated) {
            System.out.println("\n   Session ended. Please contact your bank.");
            printDivider();
            scanner.close();
            return;
        }

        // STATE 2: Authenticated → Transaction Loop
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getValidIntInput();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    running = false;
                    printDivider();
                    System.out.println("  👋 Thank you for using  ATM!");
                    System.out.println("   Card Ejected. Goodbye!");
                    printDivider();
                    break;
                default:
                    System.out.println("    Invalid option! Please choose 1-4.");
            }
        }

        scanner.close();
    }
}
