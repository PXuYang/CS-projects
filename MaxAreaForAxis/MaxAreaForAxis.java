import java.util.*;

class MaxAreaForAxis {
    public int maxArea(int[] height) {
        int maxArea = 0;
        //for (int i=0; i<height.length; i++){
            //for(int j=i+1; j<height.length; j++){
                //int left = height[i];
                //int right = height[j];
                //int area = Math.min(height[i], height[j]) * (j-i);
                //if(area>maxArea){
                    //maxArea = area;
                //}
            //}
        //}
        int left = 0;
        int right = height.length - 1;
        
        do {
        	int area = Math.min(height[left], height[right]) * (right - left);
        	maxArea = Math.max(maxArea, area);
        	if(height[left]<height[right]) {
        		left++;
        	} else {
        		right--;
        	}
        }while(left<right);
        return maxArea;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your array length");
        int n = input.nextInt();

        int[] height = new int[n];
        System.out.println("Please enter your height for your array");
        for(int i=0; i<n; i++){
            height[i] = input.nextInt();
        }

        MaxAreaForAxis max = new MaxAreaForAxis();
		System.out.println("The max area is " + max.maxArea(height));
    }
}