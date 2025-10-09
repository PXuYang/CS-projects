import java.util.*;

class Library {
    ArrayList<Book> books = new ArrayList<>();
    int id;
    
    public void addBook(String title){
        Book bookWithID = new Book(title, id++);
        books.add(bookWithID);
        System.out.println("You added the book " + title + ", the id is " + bookWithID.getID());
        System.out.println("Total books in library: " + books.size());
    }
    
    public void borrowBook(String title){
        boolean found = false;
        for (Book book : books){
        System.out.println("Checking book: " + book.getTitle());
            if (book.getTitle().equalsIgnoreCase(title)){
                found = true;
                if (!book.isBorrowed()){
                   book.borrowBook();
                   return;
                } else {
                    System.out.println("This book is already borrowed");
                    return;
                }       
            }
        }
            if (found==false) {
                   System.out.println("The book not found in the library");
        } 
    }
    
    public void returnBook(String title){
        boolean found = false;
        for (Book book : books){
        System.out.println("Checking book: " + book.getTitle());
            if (book.getTitle().equalsIgnoreCase(title)){
                found = true;
                if (book.isBorrowed()) {
                    book.returnBook();
                    return;
                } else {
                    System.out.println("This book is not borrowed and still available");
                    return;
                }        
            }
        }
            if (found==false) {
                    System.out.println("The book not found in the library");
                }
      }    
    
    public void displayAvailableBooks() {
        System.out.print("Available books are: ");
        int count = 0;
        for (Book book : books){
            if (!book.isBorrowed()) {
                System.out.println(book.getTitle() + "  " + book.getID());
                count++;
            }
        }   if (count == 0){
            System.out.println("There is no book available now");
        }
    }
    
    public void searchBook(String title){
        boolean found = false;
        for (Book book : books){
        System.out.println("Checking book: ");
            if (book.getTitle().equalsIgnoreCase(title)){
                found = true;
                System.out.println("The book " + book.getTitle() + ", ID is " + book.getID() + ", and borrow status is " + book.isBorrowed());
                return;
            } 
      }
            if(found==false) {
                System.out.println("This book is not found in the library");
                return;
            }
      }
 }
