import java.util.*;

public class Main{
    public static void main(String[] args){
        ArrayList<Movie> movieList = new ArrayList<>();

    // Add movie to the system
        movieList.add(new Movie("NaZha", 5));
        movieList.add(new Movie("Joker", 5));
        movieList.add(new Movie("Spider man", 5));
        movieList.add(new Movie("Iron man", 5));
        
        Scanner input = new Scanner(System.in);
        
    // Menu options
        while(true){
        System.out.println("Welcome to our movie booking system, please choose the option below!");
        System.out.println("1. View available movies;");
        System.out.println("2. Book a ticket;");
        System.out.println("3. cancel a ticket;");
        System.out.println("4. Exit!");
        int menu = input.nextInt();
        input.nextLine();
        
        switch (menu){
    // Show the movie details to customers
            case 1:
                for (int i = 0; i<movieList.size(); i++){
                    System.out.println(movieList.get(i).getTitle());
                    movieList.get(i).showDetails();
                    System.out.println();
                }
                break;
            
    // Book a ticket for customers
            case 2:
                System.out.println("Enter your name please:");
                String bookName = input.nextLine();
                for(int j = 0; j<movieList.size(); j++){
                    System.out.println(j+ ". " + movieList.get(j).getTitle());
                }
                System.out.println("Please enter the movie number that you want to book:");
                int ticketBook = input.nextInt();
                if (movieList.get(ticketBook).bookTicket(bookName)){
                    System.out.println("You booked the ticket for " + movieList.get(ticketBook).getTitle());
                } else {
                    System.out.println("The seats are full or you already had this ticket!");
                }
                break;
            
    // Cancel a ticket for customers
            case 3:
                System.out.println("Enter your name please:");
                String cancelName = input.nextLine();
                for(int j = 0; j<movieList.size(); j++){
                    System.out.println(j+ ". " + movieList.get(j).getTitle());
                }
                System.out.println("Please enter the movie number that you want to book:");
                int ticketCancel = input.nextInt();
                if (movieList.get(ticketCancel).cancelTicket(cancelName)){
                    System.out.println("You canceled this ticket!");
                } else {
                    System.out.println("You don't have this ticket!");
                }
                break;
                
    // Exit system
            case 4:
                System.out.println("Thanks for using our system! Bye!");
                input.close();
                break;
                
            default:
                System.out.println("Please choose from 1 to 4!");
        }
    }
}
}