import java.util.*;
import java.io.*;
class Main
{   
    static ArrayList<Integer> dp;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st= new StringTokenizer(br.readLine());
        for(int i = 0 ; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new ArrayList<>();
        dp.add(arr[0]);
        for(int i = 1; i < n ; i++){
            if(dp.get(dp.size()-1) > arr[i]){
                dp.add(arr[i]);
            }else{
                int index = binary_search(arr[i]);
                dp.remove(index);
                dp.add(index, arr[i]);
            }
        }
        System.out.println(dp.size());

	}
    static int binary_search(int target){
        int start = 0;
        int end = dp.size()-1;

        while(start <= end){
            int mid = start + (end-start)/2;

            if(dp.get(mid) > target) start = mid+1;
            else if(dp.get(mid) < target) end = mid-1;
            else return mid;
        }
        return start;
    }
}