import java.io.*;
import java.util.*;
public class Solution {
    static class Location {
        int x;
        int y;
        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static ArrayList<Location> arr;
    static boolean[] v;
    static int n;
    static int min;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int t= 1; t<= T; t++){
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            v = new boolean[n];
            dist = new int[n+2][n+2];
            st = new StringTokenizer(br.readLine());
            arr = new ArrayList<>();
            for(int i = 0; i < n+2; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr.add(new Location(x, y));
            }

            for(int i = 0; i < n + 2 ; i++){
                for(int j = 0; j < n + 2; j++){
                    if(i == j || (i==0 && j == 1) || (i == 1 && j ==0)) continue;
                    int d = Math.abs(arr.get(i).x - arr.get(j).x) + Math.abs(arr.get(i).y - arr.get(j).y);
                    dist[i][j] = d;
                    dist[j][i] = d;
                }
            }
            min = Integer.MAX_VALUE;
            dfs(0, 0, 0);
            sb.append(min).append("\n");
        }
        System.out.print(sb);
    }
    private static void dfs(int node, int sum, int count){
        if (sum >= min) return; 
        
        if(count == n){
            min = Math.min(min, sum + dist[node][1]);
            return;
        }

         for(int i = 2; i < n+2; i++){
             if(!v[i-2]){
                v[i-2] = true;
                dfs(i, sum + dist[node][i], count+1);
                v[i-2] = false;
            }
        }

    }
}
