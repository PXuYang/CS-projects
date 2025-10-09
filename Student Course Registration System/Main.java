import java.util.*;
import java.io.*;

public class Main{

public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Course> courses = new ArrayList<>();
    
    courses.add(new Course("CSC 161", 20));
    courses.add(new Course("CSC 162", 20));
    courses.add(new Course("CSC 263", 20));
    
    for (Course c : courses){
        c.loadFile();
    }
    
    System.out.println("Enter your name: ");
    String studentName = input.nextLine();
    System.out.println("Enter your id: ");
    String id = input.nextLine();
    
    while (true){
        System.out.println("CSC 161, " + "CSC 162, " + "CSC 263");
        System.out.println("Option 1: Register for a course from above.");
        System.out.println("Option 2: Drop a course.");
        System.out.println("Option 3: View course details.");
        System.out.println("Option 4: Exit the program.");
        System.out.println("Enter your choice: ");
        int menu = input.nextInt();
        input.nextLine();
    
    if (menu>=1 && menu<4){
        
        switch (menu){
            case 1:
                System.out.println("Enter the course name that you want to register: ");
                String addCourse = input.nextLine();
                boolean available = false;
                for (Course c : courses){
                    if (c.getCourseName().equalsIgnoreCase(addCourse)){
                        c.registeredStudent(studentName, id);
                        available = true;
                        break;
                        } 
                    } if (!available){
                        System.out.println("This class was not found!");
                    }
                break;
            case 2:
                System.out.println("Enter the course name that you want to drop: ");
                String dropCourse = input.nextLine();
                available = false;
                for (Course c : courses){
                    if (c.getCourseName().equalsIgnoreCase(dropCourse)){
                        c.dropStudent(studentName, id);
                        available = true;
                        break;
                        } 
                    } if (!available){
                        System.out.println("This class was not found!");
                    }
                break;
            case 3:
                System.out.println("Enter the course name: ");
                String disCourse = input.nextLine();
                available = false;
                for (Course c : courses){
                    if (c.getCourseName().equalsIgnoreCase(disCourse)){
                        System.out.println("Found course: " + c.getCourseName());
                        c.displayCourseDetails();
                        available = true;
                        break;
                    }
                } if (!available){
                    System.out.println("This class was not found!");
                }
                break;
            } 
        } else if (menu == 4){
            System.out.println("Thank you for using the system!");
            for (Course c : courses){
                c.fileHandling();
            }
            input.close();
            return;
        } else if (menu<1 || menu>4){
            System.out.println("Please choose 1 to 4!");
            continue;
        } else {
        throw new InputMismatchException("Choose option 1 to 4");
    }
  }
 }
}