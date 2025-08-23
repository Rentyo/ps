import java.util.*;
import java.io.*;
class Solution
{
    static int[][]map;
    static boolean[][] visited;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int destCol;
    static int destRow;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		StringBuilder sb = new StringBuilder();
        
		for(int test_case = 1; test_case <= 10; test_case++)
		{
			sb.append("#").append(Integer.parseInt(br.readLine())).append(" ");
            map = new int[100][100];
            visited = new boolean[100][100];
            int startCol = 0;
            int startRow = 0;
            for(int i = 0; i < 100; i++){
            	String s=  br.readLine();
                for(int j = 0; j < 100; j++){
                	map[i][j] = s.charAt(j)-'0';
                    if(map[i][j] == 2){
                    	startCol=i;
                        startRow = j;
                    }
                    if(map[i][j] == 3){
                    	destCol = i;
                        destRow = j;
                    }
                }
            }
            if(dfs(startCol, startRow)) sb.append(1).append("\n");
            else sb.append(0).append("\n");
		}
        System.out.print(sb);
	}
    public static boolean dfs(int col, int row){
        if(col == destCol && row == destRow) return true;
        visited[col][row] = true;
        for(int i = 0; i < 4; i++){
        	if(col + dy[i] >= 0 && col + dy[i] < 100 && row + dx[i] >= 0 && row + dx[i] < 100 && !visited[col + dy[i]][row + dx[i]] && (map[col + dy[i]][row + dx[i]] == 0 ||  map[col + dy[i]][row + dx[i]] == 3) ){
            	if(dfs(col + dy[i] , row + dx[i])) return true; 
            }
        }
        return false;
    	
    }
}