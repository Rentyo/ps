import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)  throws IOException{
        BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] human = new int[6];
        for(int i = 0; i < 6; i++){
            human[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 0; i < 6; i++){
            if(human[i] % T != 0){
                count += (human[i] / T) + 1;
            }else{
                count += human[i] / T;
            }
        }
        sb.append(count).append("\n");

        sb.append(N / P).append(" ").append(N % P);
        System.out.println(sb);
    }
}
