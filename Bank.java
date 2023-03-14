

import java.io.*;
import java.util.Properties;

public  class Bank {
    public Properties bank_info;
    public FileInputStream filer = null;
     public boolean info_changed = false;
     BufferedReader reader = null;
/*Bank Constructor --- Advisable to have only one Bank object in the Main Class
* Not yet implemented Multi Threading and Networking to the class*/
    Bank(){

        bank_info = new Properties();
        try{
            filer=new FileInputStream("bank_info.dat");
        }catch (FileNotFoundException e){
            // Ignore missing file
        }
        reader = new BufferedReader(new BufferedReader(new InputStreamReader(System.in)));
        loadData();


    }
    // Method for Creation of an Account in the Bank, also registers the account with the Bank
    public void createAccount() throws IOException{
        System.out.println("You have chosen to create an account, Please type in your name");
        String account_name = reader.readLine();
        Account created = new Account(account_name, 0);

       registerAccount(created);

    }
    // Loading data into the Properties object from a file to be saved ----- Mini Local Database
   public void loadData (){
        try {
            bank_info.load(filer);
        }catch (Exception e){
         // Don't do anything
        }

}
// Storing data into a file from A Properties class
public  void storeData(){
        if (info_changed){
            try{
                FileOutputStream filee = new FileOutputStream("bank_info.dat");
                bank_info.store(filee,"Updated Transactions");
                filee.close();
            }catch ( Exception e){
                System.err.println("Something went wrong");
            }
        }

}
// Registers an Account
public void registerAccount(Account user){
        bank_info.setProperty(user.user_name, String.valueOf(user.user_amount));
    System.out.println(user.user_name + ", Your account has been registered with the bank.\n Thanks for banking with us");
    storeData();
}

// Shows your Balance as long as you have an Account with the Bank
public void balance ()throws IOException{
    System.out.println("Type in your name");
    String tname = reader.readLine() ;
    //Search for the user
    if (bank_info.containsKey(tname)){
        System.out.println("Yor balance is "+bank_info.getProperty(tname));
}}




// Allows user to deposit money into the Bank --- Transactions are stored and recorded
public void deposit( )throws IOException{
    System.out.println("You wish to deposit, Type in your name");

        String name = reader.readLine() ;
        //Search for the user
    if (bank_info.containsKey(name)){
        System.out.println("Type in the amount you want to deposit");
        int amount = Integer.parseInt(reader.readLine());
        int user_balance = Integer.parseInt(bank_info.getProperty(name));
        user_balance+=amount;
        bank_info.setProperty(name, String.valueOf(user_balance));
        System.out.println("Your balance is "+ bank_info.getProperty(name));
    }else System.out.println("Your account is not found. Try creating a new account");
   info_changed = true;
storeData();
}

/* Allows user to withdraw money from Account.
* If balance in the User's  Account is less than the Withdrawal, Withdrawal cancelled*/
public void withdraw () throws IOException{
    System.out.println("You wish to withdraw, Type in your name");

    String name = reader.readLine() ;
    //Search for the user
    if (bank_info.containsKey(name)){
        System.out.println("Type in the amount you want to withdraw");
        int amount = Integer.parseInt(reader.readLine());

        int user_balance = Integer.parseInt(bank_info.getProperty(name));
        if (user_balance ==0){
            System.out.println("You don't have any money");
            if (amount>user_balance) System.out.println("You have withdrawn above your balance");
        }else {
            System.out.println("You have withdrawn "+ amount);
            user_balance-=amount;
        }
        bank_info.setProperty(name, String.valueOf(user_balance));
      }else System.out.println("Your account is not found. Try creating a new account");
      info_changed = true;
       storeData();
}
// Allows User to delete Account from Bank
public void deleteAccount() throws IOException{
    System.out.println("You wish to delete an account");
    System.out.println("Type in your account name");
    String tname = reader.readLine() ;
    //Search for the user
    if (bank_info.containsKey(tname)){
        bank_info.remove(tname);
    }
}
//Closes the program
    public void close() throws IOException{
   if (info_changed){
       storeData();
       reader.close();
   }
    }

}
