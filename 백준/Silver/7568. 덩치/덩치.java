import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];
        int[] score = new int[N];
        Arrays.fill(score, 1);
        for(int i = 0; i < N; i++ ){
            StringTokenizer st= new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(x[i] < x[j] && y[i] < y[j] ) score[i]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append(score[i]).append(" ");
        }
        System.out.println(sb);
    }
}