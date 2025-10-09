import java.util.*;

class AvoidFloodLake {
    public static int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        TreeSet<Integer> dd = new TreeSet<Integer>();
        Map<Integer, Integer> rd = new HashMap<Integer, Integer>();

        for(int day=0; day<n; day++){
            if(rains[day] == 0){
                dd.add(day);
            } else {
                ans[day] = -1;
                if(rd.containsKey(rains[day])){
                    Integer dryDay = dd.ceiling(rd.get(rains[day]));
                    if(dryDay == null){
                        return new int[0];
                    }
                    ans[dryDay] = rains[day];
                    dd.remove(dryDay);
                }
                rd.put(rains[day], day);
            }
        } 
        return ans;
    }
    
    public static String toString(int[] rains) {
    	if (rains.length == 0 || rains == null) {
    		return "[]";
    	}
    	String st = "[";
		for(int i=0; i<rains.length; i++) {
			st += rains[i];
			if (i != rains.length - 1) {
				st += ", ";
			}
		}
		st += "]";
		return st;
    	
    }
    
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter your number of rain days");
    	int n = input.nextInt();
    	System.out.println("Please enter your rain days for lakes(0 for dry days, lake number for rain days)");
    	int[] rains = new int[n];
    	for(int i=0; i<n; i++) {
    		rains[i] = input.nextInt(); 
    	}
    	
    	System.out.println(AvoidFloodLake.toString(rains));
    	int[] ans = AvoidFloodLake.avoidFlood(rains);
    	if(ans.length != 0) {
    		System.out.println("For these raining days, we could use these approach to avoid flood:");
        	System.out.println(AvoidFloodLake.toString(ans));
    	} else {
        	System.out.println("For these raining days, we couldn't avoid flood!");
    	}
    }
}
