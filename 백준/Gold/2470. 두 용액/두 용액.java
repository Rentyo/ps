import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for(int i = 0; i < n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 0;
        int end = arr.length-1;
        long min = Long.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        long result1 = 0;
        long result2 = 0;
        while(start < end){
            if(min == 0) break;
            long n1 = arr[start];
            long n2 = arr[end];

            if(min > Math.abs(n1 + n2)){
                min = Math.abs(n1+n2);
                result1 = n1;
                result2 = n2;
            }else{
                if(n1 + n2 >= 0){
                    end--;
                }else{
                    start++;
                }
            }
        }
        sb.append(result1).append(" ").append(result2);
        System.out.println(sb);

    }
}
