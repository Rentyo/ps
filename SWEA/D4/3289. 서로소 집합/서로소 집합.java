import java.util.*;
import java.io.*;
class Solution
{
    static int[] arr;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
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
            	st=  new StringTokenizer(br.readLine());
                int cal = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(cal == 0){
                	union(a, b);
                }else{
                	if(find(a) == find(b)) sb.append(1);
                    else sb.append(0);
                }
            }
            sb.append("\n");
		}
        System.out.print(sb);
	}
    public static void union(int a, int b){
    	int rootA = find(a);
        int rootB = find(b);
        
        if(rootA > rootB){
        	arr[rootA] = rootB;
        }else{
            arr[rootB] = rootA;
        }
    }
    public static int find(int a){
    	if(a == arr[a]) return a;
        return arr[a] = find(arr[a]);
    }
}