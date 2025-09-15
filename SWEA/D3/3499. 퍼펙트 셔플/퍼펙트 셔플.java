import java.io.*;
import java.util.*;
public class Solution {
    static int[] arr;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= t; test_case++) {
            sb.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            String[] s = new String[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < n; i++){
                s[i] = st.nextToken();
            }
            if(n % 2 == 0){
                for(int i = 0; i < n/2; i++){
                    sb.append(s[i]).append(" ").append(s[n/2 + i]).append(" ");
                }
            }else{
                for(int i = 0; i < n/2; i++){
                    sb.append(s[i]).append(" ").append(s[n/2 + 1 + i]).append(" ");
                }
                sb.append(s[n/2]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
     

