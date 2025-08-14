import java.io.*;
import java.util.*;
public class Main {
	static int n;
	static int m;
	static ArrayList<Vtime> virus;
	static int[][] map;
	static int[] sel;
	static boolean[][] v;
	static int[][] d = {{1,0}, {0,1}, {-1, 0}, {0,-1}};
	static int min;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		virus = new ArrayList<>();
		sel = new int[m];
		map = new int[n][n];
		v = new boolean[n][n];
		for(int i = 0; i < n ; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2){
					virus.add(new Vtime(i, j, 0));
				}else if(map[i][j] ==1){
					v[i][j] = true;
				}
			}
		}
		min = Integer.MAX_VALUE;
		combination(0, 0);

		if(min != Integer.MAX_VALUE){
			System.out.println(min);
		}else{
			System.out.println(-1);
		}
	}
	static void combination(int count, int index){
		if(count == m){
			simulate();
			return;
		}
		if(index == virus.size()) return;

		sel[count] = index;
		combination(count+1, index+1);
		combination(count, index+1); 

		
	}
	static void simulate(){
		boolean [][] visited = new boolean[n][n];
		for(int i =0; i < n; i++){
			System.arraycopy(v[i],0,visited[i],0, n);
		}
		PriorityQueue<Vtime> queue = new PriorityQueue<>();
		for(int i = 0; i < sel.length; i++){
			queue.offer(virus.get(sel[i]));
			visited[virus.get(sel[i]).row][virus.get(sel[i]).col] = true;
		}
		int totalTime = 0;
		while(queue.size() > 0){
			Vtime now = queue.poll();
			if(map[now.row][now.col] != 2){
				totalTime = now.time;
			}
			for(int i = 0; i < 4; i++){
				int nextRow = now.row +d[i][0];
				int nextCol = now.col +d[i][1];
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(!visited[nextRow][nextCol]){
					visited[nextRow][nextCol] = true;
					queue.offer(new Vtime(nextRow, nextCol , now.time+1));
				}
			}
		}
		boolean check = false;
		for(int i = 0; i < n; i++){
			if(check) break;
			for(int j = 0; j < n; j++){
				if(!visited[i][j] && map[i][j] != 2){
					check = true;
					break;
				}
			}
		}
		if(!check){
			min = Math.min(min,totalTime);
		}
	}
	static class Vtime implements Comparable<Vtime>{
		int row;
		int col;
		int time;
		public Vtime(int row, int col, int time){
			this.row = row;
			this.col = col;
			this.time= time;
		}
		@Override
		public int compareTo(Vtime o){
			return this.time - o.time;
		}
	}
}
