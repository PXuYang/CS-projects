
 // Definition for singly-linked list.

import java.util.Scanner;

class ListNode {
     int val;
     ListNode next;
     
     ListNode() {
    	 
     }
     
     ListNode(int val) {
    	 this.val = val; 
    	 }
     
     ListNode(int val, ListNode next) { 
    	 this.val = val; 
    	 this.next = next; 
    	 }
     
     public String toString() {
    	 String st = "";
    	 ListNode temp = this;
    	 while(temp != null) {
    		 st += temp.val;
    		 if(temp.next != null) {
    		 st += " => ";
    	 }
    		 temp = temp.next;
    	 }
    	 return st;
     }
}

class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode moveNode = result;
        int carry = 0;

        while(l1 != null || l2 != null || carry != 0){
            int num1 = 0;
            int num2 = 0;
            if(l1 != null){
                num1 = l1.val;
            }
            if(l2 != null){
                num2 = l2.val;
            }

            int sum = num1 + num2 + carry;
            int node = sum % 10;
            carry = sum / 10;

            ListNode nextNode = new ListNode(node);
            moveNode.next = nextNode;
            moveNode = moveNode.next;

            if(l1 != null){
                l1 = l1.next;
            } else {
                l1 = null;
            }
            if(l2 != null){
                l2 = l2.next;
            } else {
                l2 = null;
            }
        }

        return result.next;
    }
    
    public static ListNode convertNumber(int number) {
    	if(number ==0) {
    		return new ListNode(0);
    	}
    	
    	ListNode result = new ListNode(0);
    	ListNode moveNode = result;
    	
    	while(number > 0) {
    		int nodes = number % 10;
    		ListNode newNode = new ListNode(nodes);
    		moveNode.next = newNode;
    		moveNode = moveNode.next;
    		number /= 10;
    	}
    	
    	return result.next;
    }
}

public class Node {
	public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.println("Please enter a number");
	int number = input.nextInt();
	
	System.out.println("Please enter another number");
	int number1 = input.nextInt();

	ListNode num = Solution2.convertNumber(number);
	ListNode num1 = Solution2.convertNumber(number1);
	
	Solution2 sum = new Solution2();
	String st = sum.addTwoNumbers(num, num1).toString();
	
	System.out.println(number + " + " + number1 + " = " +st);
	}
}