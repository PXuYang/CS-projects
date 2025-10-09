public class UserInputValidator{
    // checking if name is valid
    public static String isValidName(String name) throws InvalidInputException{
        if (name.trim().isEmpty()){
            throw new InvalidInputException("Name cannot be empty!");
        }
        return name;
    }
    
    // checking if age is integer number
    public static int isValidAge(String age) throws InvalidInputException{
        try {
            int age1 = Integer.parseInt(age);
            
            if(age1<0){
                throw new InvalidInputException("Age should be positive integer!");
        }
        return age1;
        } catch (NumberFormatException e){
            throw new InvalidInputException("Age should be integrated number!");
        }
    }
    
    // checking if email is correct format
    public static String isValidEmail(String email) throws InvalidInputException{
        if(!email.contains("@") || (!email.endsWith(".com") && !email.endsWith(".edu"))){
            throw new InvalidInputException("Invalid Email input");
        }
        return email;
    }
    
    // checking if phone number is correct format and 10 digits
    public static String isValidPhoneNumber(String phone) throws InvalidInputException{
        if(phone.length()!=10){
            throw new InvalidInputException("Phone number must be exactly 10 digits");
        }
            for (int i=0; i<phone.length(); i++){
                char c = phone.charAt(i);
                if(!Character.isDigit(c)){
                    throw new InvalidInputException("Phone number must be number");
                }
            }
        return phone;
    }
}