import java.util.*;

class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> charSet = new HashSet<>();
        int longest = 0;
        int left = 0;
        String longestString = "";

        for (int i=0; i<s.length(); i++){
            if (!charSet.contains(s.charAt(i))){
                charSet.add(s.charAt(i));
                if (longest<i-left+1) {
                    longest = i-left+1;
                    longestString = s.substring(left, i+1);
            }
                System.out.println("add " + s.charAt(i) + " " + charSet);
            } else {
                while(charSet.contains(s.charAt(i))){
                    charSet.remove(s.charAt(left));
                    System.out.println("remove " + s.charAt(left) + " " + charSet);
                    left++;
                }
                charSet.add(s.charAt(i));
                System.out.println("add " + s.charAt(i) + " " + charSet);
            }
        }
        System.out.println("The longest of substring is " + longestString);
        return longest;
    }
    
    public static void main(String[] args) {
    	
    	Scanner input = new Scanner(System.in);
    	
    	System.out.println("Please enter a string of characters");
    	String st = input.nextLine();
    	
    	System.out.println("The string " + st + "'s longest substring length is " + lengthOfLongestSubstring(st));
    }
}