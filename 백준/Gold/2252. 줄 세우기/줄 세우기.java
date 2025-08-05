import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] indegree = new int[n + 1];
		List<Integer>[] graph = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			graph[start].add(end);
			indegree[end]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int now = queue.poll();
			sb.append(now).append(" ");
			
			for (int next : graph[now]) {
				if (--indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		System.out.println(sb);
	}
}