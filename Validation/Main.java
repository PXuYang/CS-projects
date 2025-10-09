import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<String> users = new ArrayList<>(); //To save user info
        
        // loop for entering user info and checking if it is valid
        while(true){
            System.out.println("Please enter your name, age, email and phone number: ");
            String name = input.nextLine();
            String age = input.nextLine();
            String email = input.nextLine();
            String phone = input.nextLine();
            
            try {
                String validName = UserInputValidator.isValidName(name);
                int validAge = UserInputValidator.isValidAge(age);
                String validEmail = UserInputValidator.isValidEmail(email);
                String validPhone = UserInputValidator.isValidPhoneNumber(phone);
                
                // saving user info as one string to add it to arraylist
                String user = "Name: " + validName + ", Age: " + validAge + ", Email: " +validEmail + ", Phone: " + validPhone;
                users.add(user);
                System.out.println("User added successfully!");
                
                // Add more user if needed
                System.out.println("Do you want to add more users?(Yes or No)");
                String moreUser = input.nextLine();
                if(!moreUser.equalsIgnoreCase("Yes")){
                    break;
                }
            } catch (InvalidInputException e){
                System.out.println("Wrong input, please try again!");
            }
        }
        
        // print out all added user
        for (String u : users){
            System.out.println(u);
        }
    }
}