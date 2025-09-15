import java.io.*;
import java.util.*;
public class Main {

	static int[] d;
	static ArrayList<int[]>[] map;
	static int N;
	public static void main(String[] args) throws IOException {
		//-------여기에 해결 코드를 작성하세요.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N =Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		d = new int[N];
		map = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			map[i] = new ArrayList<>();
			d[i] = Integer.MAX_VALUE;
		}
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken())-1;
			int node2 = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			
			map[node1].add(new int[] {node2, weight});
			
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		dijkstra(start);
		System.out.print(d[end]);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		pq.offer(new int[] {start, 0});
		
		while(pq.size() > 0) {
			
			int[] cur = pq.poll();
			if(d[cur[0]] != Integer.MAX_VALUE) continue;
			d[cur[0]] = cur[1];
			
			for(int i = 0; i < map[cur[0]].size(); i++) {
				pq.offer(new int[] {map[cur[0]].get(i)[0], map[cur[0]].get(i)[1] + cur[1] });
			}
		}
	}

}
