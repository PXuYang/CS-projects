import java.util.*;

class BankAccount {
    private int accountNumber;
    private int pin;
    private String name;
    private double currentBalance;
    
    public BankAccount(int accountNumber, int pin, String name, double currentBalance){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.currentBalance = currentBalance;
    }
    
    public void deposit(double amount){
        if (amount>0){
            currentBalance += amount;
            System.out.println("You successfully deposit $" + amount + "!");
        } else {
            System.out.println("The amount is invalid!");
        }
    }
    
    public void withdraw(double amount){
        if (amount<=currentBalance && amount>0){
            currentBalance -= amount;
            System.out.println("You withdrawed $" + amount + "!");
        } else {
            System.out.println("Sorry! Your current balance is insufficient!");
        }
    }
    
    public void checkBalance(){
        System.out.println("Your balance is $" + currentBalance);
    }
    
    public int getPin(){
        return pin;
    }
    
    public int getAccountNumber(){
        return accountNumber;
    }
    
    public boolean validatePin(int pin1){
        if (pin == pin1){
            return true;
        } else {
            return false;
        }
    }
}

