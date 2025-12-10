import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while(A != 0 && B != 0 && C !=0 ){
            int max = Math.max(A, Math.max(B, C));
            if(max == A){
                sb.append( (Math.pow(max, 2) == (Math.pow(B, 2) + Math.pow(C,2))) ? "right" : "wrong").append("\n");
            }else if(max == B){
                sb.append( (Math.pow(max, 2) == (Math.pow(A, 2) + Math.pow(C,2))) ? "right" : "wrong").append("\n");
            }else{
                sb.append( (Math.pow(max, 2) == (Math.pow(B, 2) + Math.pow(A,2))) ? "right" : "wrong").append("\n");
            }
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }
}