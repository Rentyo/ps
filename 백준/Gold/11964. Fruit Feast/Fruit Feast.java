import java.io.*;
import java.util.*;
public class Main {
    static boolean[][] v;
    static int A;
    static int B;
    static int limit;
    static int max;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        limit = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        v = new boolean[2][limit+1];
        max = 0;
        dfs(0, 0);
        System.out.println(max);
    }
    public static void dfs(int now, int count){
        if(now > limit) return;
        if(v[count][now]) return;
        v[count][now] = true;
        
        if(max < now ){
            max = now;
        }

        dfs(now + A, count);
        dfs(now + B, count);
        if(count == 0){
            dfs(now /2, count+1);
        }

    }
    
}