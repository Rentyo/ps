import java.io.*;
import java.util.*;
public class Main {
    static int[] sol;
    static boolean[] v;
    static int[] arr;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        sol = new int[M];
        v = new boolean[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        recursive(0, 0, M);
        System.out.println(sb);
    }
    static void recursive(int index ,int count, int max){
        if(count == max){
            for(int i = 0; i < sol.length; i++){
                sb.append(sol[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        if(index == N) return;

        for(int i = 0; i < N; i++){
            if(v[i]) continue;
            v[i] = true;
            sol[index] = arr[i];
            recursive(index + 1, count + 1, max);
            v[i] = false;
        }
    }
}
