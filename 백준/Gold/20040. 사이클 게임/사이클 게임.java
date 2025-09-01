import java.io.*;
import java.util.*;
public class Main {

	static int[] root;
	public static void main(String[] args) throws IOException {
		//--------------솔루션 코드를 작성하세요.---------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		root = new int[n];
		for(int i = 0; i < n; i++) {
			root[i] = i;
		}
		int count = 0;
		boolean cycle = false;
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			count++;
			if(find(a) != find(b)) {
				union(a, b);
			}else {
				cycle = true;
				break;
			}
		}
		System.out.println(count == m && !cycle ? 0 : count);
		
	}
	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA > rootB) root[rootA] = rootB;
		else root[rootB] = rootA;
	}
	public static int find(int a) {
		if(a == root[a]) return a;
		return root[a] = find(root[a]);
	}

}
