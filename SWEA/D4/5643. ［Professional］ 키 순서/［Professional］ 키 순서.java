import java.util.*;
import java.io.*;
 
class Solution
{   

    static boolean[][] edges;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            edges = new boolean[n][n];
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken())-1;
                int end = Integer.parseInt(st.nextToken())-1;
                edges[start][end] = true;
            }

            Queue<Integer> q = new ArrayDeque<>();
            boolean[] v;
            int[] d = new int[n];            
            for(int i = 0; i < n; i++){
                v = new boolean[n];
                v[i] = true;
                q.offer(i);
                while(q.size() > 0){
                    int cur = q.poll();
                    for(int j = 0; j < n; j++){
                        if(edges[cur][j] && !v[j]){
                            q.offer(j);
                            v[j] = true;
                            d[i]++;                     
                        }
                    }
                }
                q.offer(i);
                while(q.size() > 0){
                    int cur = q.poll();
                    for(int j = 0; j < n; j++){
                        if(edges[j][cur] && !v[j]){
                            q.offer(j);
                            v[j] = true;
                            d[i]++;                     
                        }
                    }
                }
            }
            int count = 0;
            for(int i = 0; i < n; i++){
                if(d[i] == n-1) count++;
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}

