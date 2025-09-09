import java.util.*;
import java.io.*;
class Solution
{   
    static ArrayList<Integer> dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t_c = 1; t_c <= t; t_c++){
            sb.append("#").append(t_c).append(" ");
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            dp = new ArrayList<>();
            dp.add(arr[0]);
            for(int i = 0; i < n; i++){
                if(arr[i] > dp.get(dp.size()-1)) dp.add(arr[i]);
                else{
                    int index = binary_search(arr[i]);
                    dp.remove(index);
                    dp.add(index, arr[i]);
                }
            }
            sb.append(dp.size()).append("\n");
        }
        System.out.println(sb);
    }
    public static int binary_search(int target){
        int start = 0;
        int end = dp.size()-1;
        while(start <= end){
            int mid = start + (end-start)/2;

            if(dp.get(mid) > target) end = mid-1;
            else if(dp.get(mid) < target) start = mid+1;
            else return mid;
        }
        return start;
    }
}