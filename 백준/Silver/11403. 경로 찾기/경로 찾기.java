import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new List[n];

        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int d =  Integer.parseInt(st.nextToken());
                if(d == 1 ) graph[i].add(j);
            }
        }
        int[][] minD = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(minD[i], 123456789);
        }

        for(int i = 0; i <n ; i++){
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{i, 0});

             while(!q.isEmpty()){
                int[] cur = q.poll();
                for(int j = 0; j < graph[cur[0]].size(); j++){
                    int next =  graph[cur[0]].get(j);
                    int value = cur[1] + 1;

                    if(minD[i][next] > value){
                        minD[i][next] = value;
                        q.offer(new int[]{next, value});
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(minD[i][j] == 123456789){
                    sb.append(0).append(" ");
                }else{
                    sb.append(1).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
