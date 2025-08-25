import java.util.*;
import java.io.*;
class Solution
{
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
            int m = Integer.parseInt(st.nextToken());
            arr = new int[n+1];
            for(int i =1 ; i<=n; i++){
            	arr[i] = i;
            }
			for(int i = 0; i < m; i++){
            	st= new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }
            int[] root = new int[n+1];
            for(int i = 1; i <= n; i++){
            	arr[i] = find(i);
                root[arr[i]]++;
            }
            int count = 0;
            for(int i = 1; i <= n; i++){
            	if(root[i] != 0 ) count++;
            }
			sb.append(count).append("\n");
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