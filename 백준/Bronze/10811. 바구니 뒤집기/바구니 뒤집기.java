import java.io.*;
import java.util.*;
public class Main {
	static int[] arr;
	public static void main(String[] args) throws IOException {
		//-------여기에 해결 코드를 작성하세요.
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		for(int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			calculate(start, end);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb);
	}
	public static void calculate(int start, int end) {
		StringBuilder sb = new StringBuilder();
		int si = end-start+1;
		int[] temp = new int[end-start+1];
		for(int i = start-1; i <= end-1; i++) {
			temp[i-start+1] = arr[i];
		}
		for(int i = start-1; i <= end-1; i++) {
			arr[i] = temp[end-1 -i];
		}
	}

}
