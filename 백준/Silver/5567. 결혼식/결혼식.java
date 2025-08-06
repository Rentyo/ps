import java.io.*;
import java.util.*;

public class Main {
	static boolean[] v;
	static boolean[] f;
	static ArrayList<ArrayList<Integer>> rel;
	static int count = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		v = new boolean[n+1];

		int m = Integer.parseInt(br.readLine());
		rel = new ArrayList<>();
		for(int i = 0; i <= n; i++){
			rel.add(new ArrayList<>());
		}
		f = new boolean[n+1];
		for (int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			rel.get(start).add(end);
			rel.get(end).add(start);
			if(start == 1){
				count++;
				f[end] = true;
				v[end] = true;
			}
		}
		v[1] = true;
		for(int i = 2; i <= n; i++){
			if(f[i]){
				for(int j = 0; j < rel.get(i).size(); j++){
					int next = rel.get(i).get(j);
					if(!v[next]){
						v[next] = true;
						count++;
					}
				}
			}
		}
		System.out.println(count);
	}
}