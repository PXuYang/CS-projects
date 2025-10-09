import java.util.*;

class Bank{
    ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    
    public Bank(){
        bankAccounts.add(new BankAccount(1001, 1234, "John Doe", 500.00));
        bankAccounts.add(new BankAccount(1002, 2345, "Jane Smith", 1200.00));
        bankAccounts.add(new BankAccount(1003, 3456, "Alice Johnson", 850.00));
    }
    
    public BankAccount findAccount(int accountNumber, int pin){
        for (BankAccount bankAccount : bankAccounts) {
            if (accountNumber == bankAccount.getAccountNumber() && bankAccount.validatePin(pin)){
                return bankAccount;
            } 
        } throw new InputMismatchException("Invalid pin or account number");
    }
    
    public void performTransaction(BankAccount bankAccount){
        Scanner input = new Scanner(System.in);
        
        while(true){
            System.out.println("1. Check balance");
            System.out.println("2. Deposit");
            System.out.println("3. withdraw");
            System.out.println("4. Do another transaction or exit");
            
            int menu = input.nextInt();
            input.nextLine();
            
            if (menu>=1 && menu<=4){
                switch(menu){
                    case 1:
                        bankAccount.checkBalance();
                        break;
                    case 2:
                        System.out.println("Enter the amount that you want to deposit: $");
                        double depositAmount = input.nextDouble();
                        bankAccount.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.println("Enter the amount that you want to withdraw $");
                        double withdrawAmount = input.nextDouble();
                        bankAccount.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you! System ended!");
                        return;
                }
            } else if (menu<1 || menu>4) {
                System.out.println("Please select the correct option from 1 to 4!");
                continue;
            } else {
                throw new InputMismatchException("Invalid input");
            }
        }
    }
}
