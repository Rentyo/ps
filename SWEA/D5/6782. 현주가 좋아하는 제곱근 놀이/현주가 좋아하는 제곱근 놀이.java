import java.io.*;
import java.util.*;
public class Solution {
    static long n;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        arr = new long[1_000_000];
        for(long i = 0; i < 1_000_000; i++){
            arr[(int)i] = (i+1)*(i+1);
        }
        for (int test_case = 1; test_case <= t; test_case++) {
            sb.append("#").append(test_case).append(" ");
            long count = 0;
            n = Long.parseLong(br.readLine());
            while(n != 2){
                long sqrt = (long)Math.sqrt(n);
                if(sqrt * sqrt != n){
                    int num = binary_search(n);
                    count += arr[num] - n;
                    n = arr[num];
                }else{
                    n = sqrt;
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
    static int binary_search(long n){
        int start = 0;
        int end = arr.length -1;
        while(start <= end){
            int mid = start + (end - start)/2;
            if(arr[mid] == n){
                return mid;
            }else if(arr[mid] < n){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return start;

    }
}

