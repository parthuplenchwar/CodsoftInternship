class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public String withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return "Withdrawn: " + amount + ". New balance: " + this.balance;
        } else {
            return "Insufficient funds";
        }
    }

    public double checkBalance() {
        return this.balance;
    }
}

class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String withdraw(double amount) {
        String result = this.userAccount.withdraw(amount);
        return result;
    }

    public String deposit(double amount) {
        this.userAccount.deposit(amount);
        return "Deposited: " + amount + ". New balance: " + this.userAccount.checkBalance();
    }

    public String checkBalance() {
        return "Current balance: " + this.userAccount.checkBalance();
    }
}

// Example usage:
public class codsoft2 {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000);
        ATM atm = new ATM(userAccount);

        System.out.println(atm.withdraw(600)); // Output: Withdrawn: 500.0. New balance: 500.0
        System.out.println(atm.deposit(200));  // Output: Deposited: 200.0. New balance: 700.0
        System.out.println(atm.checkBalance()); // Output: Current balance: 700.0
    }
}
