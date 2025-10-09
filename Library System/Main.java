import java.util.*;

public class Main{
public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Library library = new Library();
    while (true) {
    System.out.println("1. Add new books");
    System.out.println("2. Borrow books");
    System.out.println("3. Return books");
    System.out.println("4. Display available books");
    System.out.println("5. Search book by title");
    System.out.println("6. Exit");
    System.out.println("Enter your choice");
    int menu = input.nextInt();
    input.nextLine();
    
    if (menu>=1&&menu<=6){
        switch (menu){
            case 1:
                System.out.print("Enter the book name: ");
                String bookAdd=input.nextLine();
                library.addBook(bookAdd);
                break;
            case 2:
                System.out.print("Enter the book name: ");
                String bookBorrow=input.nextLine();
                library.borrowBook(bookBorrow);
                break;
            case 3:
                System.out.print("Enter the book name: ");
                String bookReturn=input.nextLine();
                library.returnBook(bookReturn);
                break;
            case 4:
                library.displayAvailableBooks();
                break;
            case 5:
                System.out.print("Enter the book name: ");
                String bookSearch=input.nextLine();
                library.searchBook(bookSearch);
                break;
            case 6:
                System.out.println("System ends! Thank you!");
                input.close();
        }
    } else if (menu<1 || menu>6){
        System.out.println("Please input option 1 to 6");
        continue;
    } else {
        throw new InputMismatchException("Choose option 1 to 6");
    }
}
}
}