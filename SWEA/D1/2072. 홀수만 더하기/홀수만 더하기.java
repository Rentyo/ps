import java.util.*;
import java.io.*;

class Solution
{
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
            int sum = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 10; i++){
            	int num = Integer.parseInt(st.nextToken());
                if(num%2 == 1) sum += num;
            }
			sb.append(sum).append("\n");
		}
        System.out.print(sb);
	}
}