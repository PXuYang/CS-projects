import java.util.*;
  
public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Bank bank = new Bank();
        System.out.println("Welcome to our bank application! Please log in to your account: ");
        
        int accountNumber = input.nextInt();
        int pin1 = input.nextInt();
        
        try {
            BankAccount bankAccount =  bank.findAccount(accountNumber, pin1);
            System.out.println("Log in successfully!");
            bank.performTransaction(bankAccount);
        } catch (Exception e) {
            System.out.println("Invalid pin or account number");
        }
    }
}