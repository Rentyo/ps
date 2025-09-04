import java.util.*;
import java.io.*;
public class Main {
    public static final int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = i == j ? 0 : INF;
            }
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a-1][b-1] = Math.min(c, graph[a-1][b-1]);
        }

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    graph[j][k] = Math.min(graph[j][k], graph[j][i]+graph[i][k]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if(graph[i][j] == INF ||  i == j){
                    graph[i][j] = 0;
                }
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}