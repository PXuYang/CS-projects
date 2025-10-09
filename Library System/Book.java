import java.util.*;

class Book {
    private String title;
    private boolean borrowed;
    private int id;
    
    public Book(String title, int id){
        this.title = title;
        this.id = id;
        borrowed = false;
    }
    
    public void borrowBook(){
        if (!borrowed) {
            borrowed = true;
            System.out.println("You borrowed the book " + title);
        } else {
            System.out.println("This book is already borrowed");
        }
    }
    
    public void returnBook(){
        if (borrowed) {
            borrowed = false;
            System.out.println("You returned the book " + title);
        } else {
            System.out.println("This book is not borrowed and still available");
        }
    }
    
    public String getTitle(){
        return title;
    }
    
    public boolean isBorrowed(){
        return borrowed;
    }
    
    public int getID(){
        return id;
    }
}