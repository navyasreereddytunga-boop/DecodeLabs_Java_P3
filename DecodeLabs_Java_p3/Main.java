public class Main {

    public static void main(String[] args) {

        // ✅ Create a BankAccount object
        // Parameters: Name, Account Number, PIN, Initial Balance
        BankAccount myAccount = new BankAccount(
            "Tunga Navyasree",   // Account Holder Name
            "XXXX-XXXX-1234", // Account Number
            "1234",           // PIN (4-digit)
            10000.00          // Initial Balance ₹10,000
        );

        // ✅ Create ATM and link it to the account
        ATM atm = new ATM(myAccount);

        // ✅ Start the ATM
        atm.start();
    }
}
