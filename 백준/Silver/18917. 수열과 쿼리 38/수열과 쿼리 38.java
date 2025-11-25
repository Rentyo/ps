import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long sum = 0;
        long xorSum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cal = Integer.parseInt(st.nextToken());
            int num;
            switch(cal){
                case 1 :
                    num = Integer.parseInt(st.nextToken());
                    sum += num;
                    xorSum ^= num;
                    break;
                case 2 :
                    num = Integer.parseInt(st.nextToken());
                    sum -= num;
                    xorSum ^= num;
                    break;
                case 3 :
                    sb.append(sum).append("\n");
                    break;
                case 4 : 
                    sb.append(xorSum).append("\n");
                    break;
            }
        }
        System.out.println(sb);
        
        

    }
}
