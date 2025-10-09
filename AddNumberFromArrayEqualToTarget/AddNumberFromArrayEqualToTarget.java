import java.util.*;

class AddNumberFromArrayEqualToTarget {
    public int[] twoSum(int[] nums, int target) {
    	int[] twoSum = new int[2];
        for(int i=0; i<nums.length; i++){
        	for(int j=i+1; j<nums.length; j++) {
                int sum = nums[i] + nums[j];
                if(sum == target){
                    twoSum[0] = i;
                    twoSum[1] = j;
                    return twoSum;
        	}
            }
        } return null;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your array length");
        int n = input.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter numbers");
        for(int i=0; i<n; i++){
            arr[i] = input.nextInt();
        }
        System.out.println("Enter your target");
        int tar = input.nextInt();

        AddNumberFromArrayEqualToTarget add = new AddNumberFromArrayEqualToTarget();
        int[] twoSum = add.twoSum(arr, tar);
        if(twoSum == null) {
        	System.out.println("No result found");
        } else {
        System.out.println("[" + twoSum[0] + "," + twoSum[1] + "]");
        }
    }
}