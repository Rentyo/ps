import java.io.*;
import java.util.*;

public class Main {
	public static final int[][] vector = {{1, 0} , {1, 1}, {0, 1}, {-1, 1} , {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
	public static boolean[][] map;
	public static boolean[][] v;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n1 = Integer.parseInt(st.nextToken());
		int n2 = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();

		while( !(n1 == 0 && n2 ==0)){
			map = new boolean[n2][n1];
			for(int i  = 0; i < n2; i++){
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n1; j++){
					map[i][j] = Integer.parseInt(st.nextToken()) == 1;
				}
			}
			v = new boolean[n2][n1];
			int count = 0;
			for(int i = 0; i < n2; i++){
				for(int j = 0; j < n1; j++){
					if(!v[i][j] && map[i][j]){
						v[i][j] = true;
						dfs(i, j);
						count++;
					} 
				}
			}
			sb.append(count).append("\n");
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
		}
		System.out.println(sb);
	}
	public static void dfs(int row, int col){

		for(int i = 0; i < 8; i++){
			int nextRow = row + vector[i][0];
			int nextCol = col + vector[i][1];
			if(nextRow < 0 || nextCol < 0 || nextRow == map.length || nextCol == map[0].length || !map[nextRow][nextCol] || v[nextRow][nextCol]) continue;
			v[nextRow][nextCol] = true;
			dfs(nextRow, nextCol);
		}
	} 
}