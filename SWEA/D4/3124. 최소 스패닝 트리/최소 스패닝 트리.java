import java.util.*;
import java.io.*;
class Solution
{
    public static class Node implements Comparable<Node>{
    	int node1;
        int node2;
        int value;
        public Node(int node1, int node2, int value){
        	this.node1 = node1;
            this.node2 = node2;
            this.value = value;
        }
        @Override
        public int compareTo(Node o){
        	return this.value - o.value;
        }
    }
    static int[] arr;
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
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            arr = new int[n+1];
            for(int i =1; i<=n ;i++){
            	arr[i] = i;
            }
            int m = Integer.parseInt(st.nextToken());
            long sum = 0;
			ArrayList<Node> arr = new ArrayList<>();
            for(int i = 0; i < m; i++){
            	st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                arr.add(new Node(a, b, value));
            }
            Collections.sort(arr);
            int eCnt = 0;
            for(int i = 0; i < arr.size(); i++){
                Node nd = arr.get(i);
                int node1 = nd.node1;
                int node2 = nd.node2;
                if(find(node1) != find(node2)){
                	union(node1, node2);
                    sum+= nd.value;
                    eCnt++;
                    if(eCnt == n-1) break;
                }
            }
            sb.append(sum).append("\n");
		}
        System.out.print(sb);
	}
    public static void union(int a, int b){
    	int rootA = find(a);
        int rootB = find(b);
        if(rootA >= rootB) arr[rootA] = rootB;
        else arr[rootB] = rootA;
    }
    public static int find(int a){
    	if(arr[a] == a) return a;
        return arr[a] = find(arr[a]);
    }
}