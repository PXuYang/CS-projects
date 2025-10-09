import java.util.*;
import java.io.*;

public class Exercise {
    public static void main (String[] args) throws FileNotFoundException{
        // if(args.length != 1){
        // System.out.println("Please enter file name in java Exercise");
        // return;
    // }
    //File file = new File(args[0]);
    
    File file = new File("InvalidInputException.java");
    if(!file.exists()){
        throw new FileNotFoundException ("FIle is not found!");
    } 
    Scanner read = new Scanner(file);

    int chars = 0;
    int words = 0;
    int lines = 0;

    while (read.hasNextLine()){
        String line = read.nextLine();
        lines++;
        chars += line.length() + 1;
        Scanner lineRead = new Scanner(line);
        while(lineRead.hasNext()){
            lineRead.next();
            words++;
    }
    }
    read.close();

    System.out.println("File FileA has");
    System.out.println(chars - 1 + " characters");
    System.out.println(words + " words");
    System.out.println(lines + " lines");
}

    }
