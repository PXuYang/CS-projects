import java.util.*;

class FindMedianOfTwoArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0;
        int n = nums1.length;
        int m = nums2.length;
        int[] all = new int[n+m];

        System.arraycopy(nums1, 0, all, 0, n);
        System.arraycopy(nums2, 0, all, n, m);
        Arrays.sort(all);
        for(int i=0; i<all.length; i++) {
        	System.out.print(all[i] + " ");
        }

        if(all.length % 2 == 1){
            int mid = all.length / 2;
            median = all[mid];
        } else if (all.length % 2 == 0){
            int mid1 = all.length / 2;
            int mid2 = mid1 - 1;
            median = (all[mid1] + all[mid2]) / 2.0;
        }
        return median;
    }
}

public class MedianOfTwoArrays{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the first array length");
		int l1 = input.nextInt();
		System.out.println("Please enter the other array length");
		int l2 = input.nextInt();
		
		System.out.println("Please enter the first array elements");
		int[] nums1 = new int[l1];
		for(int i=0; i<l1; i++) {
			nums1[i] = input.nextInt();
			System.out.print(nums1[i] + " ");
		}
		System.out.println();
		System.out.println("Please enter the other array elements");
		int[] nums2 = new int[l2];
		for(int i=0; i<l2; i++) {
			nums2[i] = input.nextInt();
			System.out.print(nums2[i] + " ");
		}
		System.out.println();
		input.close();

		System.out.println("The median of two arrays is " + FindMedianOfTwoArrays.findMedianSortedArrays(nums1, nums2));
	}
}