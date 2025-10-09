/* This code is a movie ticket management system
 * The purpose is:
 * view available movies
 * book a ticket for a movie
 * cancel tickets for a movie
 * exit system
 */

import java.util.*;

public class Movie{
    String title;
    int totalSeats;
    ArrayList<String> bookedCustomers;
    
    // Constructor for creating a movie with total seats
    public Movie(String title, int totalSeats){
        this.title = title;
        this.totalSeats = totalSeats;
        this.bookedCustomers = new ArrayList<>();
    }
    
    // Check if movie is available to book and book a ticket for the customer if it's available
    public boolean bookTicket(String name){
        if(bookedCustomers.size()<totalSeats && !bookedCustomers.contains(name)){
            bookedCustomers.add(name);
            return true;
        } else {
            return false;
        }
    }

    // Cancel a ticket for the customer if they already booked tickets    
    public boolean cancelTicket(String name){
        if(bookedCustomers.contains(name)){
            bookedCustomers.remove(name);
            return true;
        } else {
            System.out.println("You did not have this ticket!");
            return false;
        }
    }
    
    public String getTitle(){
        return title;
    }
    
    public int getTotalSeats(){
        return totalSeats;
    }
    
    // Show the details of the movie 
    public void showDetails(){
        System.out.println(title + ", total seats is " + totalSeats + ", and available seats is " + (totalSeats - bookedCustomers.size()));
        System.out.println("Customers are " + bookedCustomers);
    }
}