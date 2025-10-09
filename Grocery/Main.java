import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        final int MAX = 50;
        String[] names = new String[MAX];
        double[] prices = new double[MAX];
        
        loadGroceryData("groceries.txt", names, prices);
        
        int count = 0;
        for (int i=0; i<MAX; i++){
            if (names[i]!=null){
                count++;
            } else if(count == 0){
                System.out.println("Items are not found!");
                return;
            }
        }
        
        double average = calculateAveragePrice(prices, count);
        writeReport(names, prices, count, average);
    }
        
        public static void loadGroceryData(String filename, String[] names, double[] prices){
            try{
                File file = new File("groceries.txt");
                Scanner input = new Scanner(file);
                int j = 0;
            
                while(input.hasNextLine()){
                    String line = input.nextLine();
                    String[] itemAndPrice = line.split(",\\s+");
                    if(itemAndPrice.length == 2){
                        names[j] = itemAndPrice[0].trim();
                        prices[j] = Double.parseDouble(itemAndPrice[1].trim());
                        j++;
                        }
                    } input.close();
            } catch (FileNotFoundException e){
                System.out.println("File is not found!");
            }
          }
          
          public static double calculateAveragePrice(double[] prices, int count){
              double sum = 0;
              for (int i=0; i<count; i++){
                  sum+=prices[i];
              }
              return sum/count;
          }
          
          public static void writeReport(String[] names, double[] prices, int count, double average){
              try{
                  PrintWriter output = new PrintWriter("grocery_report.txt");
                  output.println("Grocery Items Report");
                  output.println("--------------------");
                  for(int i=0; i<count; i++){
                      output.println(names[i] + ":      " + prices[i]);
                  }
                  output.println("");
                  output.println("Average Price:    " + average);
                  output.close();
              } catch (IOException e){
                  System.out.println("File can be written");
              }
          }
}
