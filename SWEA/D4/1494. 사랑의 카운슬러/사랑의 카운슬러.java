import java.util.*;
import java.io.*;

class Solution
{
    public static boolean[] visited;
    public static int n;
    public static int[][] map;
    public static Long min;
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
            n = Integer.parseInt(br.readLine());
            visited = new boolean[n];
            map = new int[n][2];
            for(int i = 0; i < n; i++){
            	st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[i][0] = x;
                map[i][1] = y;
            }
            min =   Long.MAX_VALUE;
            dfs(0, 0);
			sb.append(min).append("\n");
        }
        System.out.print(sb);
	}
    public static void dfs(int count, int index){
    	if(count == n/2){
            int totalX = 0;
            int totalY = 0;
        	for(int i = 0; i < n; i++){
            	if(visited[i]){
                	totalX += map[i][0];
                    totalY += map[i][1];
                }else{
                	totalX -= map[i][0];
                    totalY -= map[i][1];
                }
            }
            min = Math.min((long)totalX*totalX + (long)totalY*totalY, min);
            return;
        }
        for(int i = index; i < n; i++){
            visited[i] = true;
            dfs(count+1, i+1);
            visited[i] = false;
        }
    }
}