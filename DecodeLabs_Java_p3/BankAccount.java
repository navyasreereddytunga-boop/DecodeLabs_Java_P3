public class BankAccount {

    // ✅ Private variables 
    private String accountHolder;
    private String accountNumber;
    private String pin;
    private double balance;

    // ✅ Constructor - Initialize account
    public BankAccount(String accountHolder, String accountNumber, String pin, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
    }

    // ✅ PIN Validation
    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    // ✅ Check Balance (Getter)
    public double getBalance() {
        return balance;
    }

    // ✅ Get Account Holder Name
    public String getAccountHolder() {
        return accountHolder;
    }

    // ✅ Get Account Number
    public String getAccountNumber() {
        return accountNumber;
    }

    // ✅ Deposit Method - Business Rules
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount! Deposit must be greater than 0.");
            return false;
        }
        balance += amount;
        System.out.println("✅ Successfully deposited ₹" + String.format("%.2f", amount));
        System.out.println("   New Balance: ₹" + String.format("%.2f", balance));
        return true;
    }

    // ✅ Withdraw Method 
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount! Withdrawal must be greater than 0.");
            return false;
        }
        if (amount > balance) {
            System.out.println("❌ Insufficient funds!");
            System.out.println("   Requested: ₹" + String.format("%.2f", amount));
            System.out.println("   Available: ₹" + String.format("%.2f", balance));
            return false;
        }
        balance -= amount;
        System.out.println("✅ Successfully withdrew ₹" + String.format("%.2f", amount));
        System.out.println("   Remaining Balance: ₹" + String.format("%.2f", balance));
        return true;
    }
}
