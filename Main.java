import java.io.IOException;
import java.util.Scanner;
/* A Bank Console Program
* User can create an account, deposit money and withdraw money
* User must create an account to use the Bank
* -------------- Release 1.0.0----------
* There will be more updates*/
public class Main {
    public static void main(String[] args) {
        Bank access = new Bank();
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the BANK");
        String option;
        do {
            System.out.println("""
                    Choose an operation from the following options\s
                    Type 'Create Account' to create a new account\s
                    Type 'Deposit' to deposit a particular amount of money\s
                    Type 'Withdraw' to withdraw a particular amount of money\s
                    Type 'Close' to close the app\s
                    Type 'Balance' to get your balance\s
                    Type 'Delete Account' to delete your account""");
             option = input.nextLine();
            try {
                switch (option.toUpperCase()) {
                    case "CREATE ACCOUNT" -> access.createAccount();
                    case "DEPOSIT" -> access.deposit();
                    case "BALANCE" -> access.balance();
                    case "WITHDRAW" -> access.withdraw();
                    case "CLOSE" -> access.close();
                    default -> System.out.println("Something went wrong");
                }
            } catch (IOException e) {
                System.out.println("An error occurred");
            }

        } while (!option.equalsIgnoreCase("Close"));
    }
}