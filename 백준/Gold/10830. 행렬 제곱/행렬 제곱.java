import java.io.*;
import java.util.*;

public class Main {
	static long[][] arr;
	static int[] count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		arr = new long[n][n];
		count = new int[37];
		int index = 0;
		while(m != 1){
			count[index]++;
			if(m % 2 != 0) count[index]++;
			m/=2;
			index++;
		}
		for(int i =0; i< n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++){
				arr[i][j] = Long.parseLong(st.nextToken());
			}
		}

		long[][] result = new long[n][n];
		for(int i = 0; i < n; i++){
			System.arraycopy(arr[i], 0, result[i], 0, n);
		}

		for(int i = index-1; i >=0; i--){
			result = arrayPowNow(result);
			if(count[i] == 2){
				result = arrayPowFirst(result);
			}
			
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				sb.append(result[i][j]%1000).append(" ");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static long[][] arrayPowFirst(long[][] now){
		long[][] temp = new long[now.length][now[0].length];
		
		for(int i = 0; i < now.length; i++){
			for(int j =0; j< now.length; j++){
				long sum = 0;
				for(int k =0; k < now.length; k++){
					sum += (now[i][k]*arr[k][j])%1000;
				}
				temp[i][j] = sum % 1000;
			}
		}
		
		return temp;
	}
	public static long[][] arrayPowNow(long[][] now){
		long[][] temp = new long[now.length][now[0].length];
		
		for(int i = 0; i < now.length; i++){
			for(int j =0; j< now.length; j++){
				long sum = 0;
				for(int k =0; k < now.length; k++){
					sum += (now[i][k]*now[k][j])%1000;
				}
				temp[i][j] = sum % 1000;
			}
		}
		
		return temp;
	}
	
}
