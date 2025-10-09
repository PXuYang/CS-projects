import java.util.*;
import java.io.*;

class Course {
    private String courseName;
    private int maxSeats;
    ArrayList<String> registeredStudents;
    
    public Course(String courseName, int maxSeats){
        this.courseName = courseName;
        this.maxSeats = maxSeats;
        this.registeredStudents = new ArrayList<>();  
    }
    
    public void registeredStudent(String studentName, String id){
        String studentInfo = studentName + " " + id;
        if (registeredStudents.size() < maxSeats){
            if (!registeredStudents.contains(studentInfo)){
                registeredStudents.add(studentInfo);
            System.out.println("You sucessfully registered this class!");
        } else if(registeredStudents.contains(studentInfo)){
            System.out.println("You have already registered this class or the class is full now!");
        }
     }
    }
    
    public void dropStudent(String studentName, String id){
        String studentInfo = studentName + " " + id;
        if (!registeredStudents.contains(studentInfo)){
            System.out.println("You did not enroll this class!");
        } else if (registeredStudents.contains(studentInfo)){
            registeredStudents.remove(studentInfo);
            System.out.println("You have sucessfully dropped this class.");
        }
    }
    
    public String getCourseName(){
        return courseName;
    }
    
    public int getMaxSeats(){
        return maxSeats;
    }
    
    public void displayCourseDetails(){
        System.out.println("The course " + courseName + ", mas seats is " + maxSeats + ", and available seats is " + (maxSeats-registeredStudents.size()));
        for (int i = 0; i<registeredStudents.size(); i++){
            System.out.println("Students who registered for this class: " + registeredStudents.get(i));
        }
    }
    
    public void fileHandling(){
        try (PrintWriter output = new PrintWriter(new File(courseName + ".txt"))){
            for (String studentInfo : registeredStudents){
                output.println(studentInfo);
            }
        } catch (IOException e){
            System.out.println("Data saving failed!");
        }
    }
    
    public void loadFile(){
        try (Scanner input = new Scanner(new File(courseName + ".txt"))){
            while (input.hasNextLine()){
                registeredStudents.add(input.nextLine());
            }
        } catch (IOException e){
            System.out.println("Data loading failed!");
        }
    }
}