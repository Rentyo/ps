import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[21];
        for(int i = 0; i < M ;i++){
            st = new StringTokenizer(br.readLine());
            String cal =  st.nextToken();
            switch(cal.charAt(1)){
                case 'd' :
                    arr[Integer.parseInt(st.nextToken())] = 1;
                    break;
                case 'e' :
                    arr[Integer.parseInt(st.nextToken())] = 0;
                    break;
                case 'h' :
                    sb.append(arr[Integer.parseInt(st.nextToken())] == 1 ? 1 : 0).append("\n");
                    break;
                case 'o' :
                    int now = Integer.parseInt(st.nextToken());
                    arr[now] = arr[now] == 1 ? 0 : 1;
                    break;
                case 'l' :
                    for(int j = 1; j <= 20 ; j++){
                        arr[j] = 1;
                    }
                    break;
                case 'm' :
                    for(int j = 1; j <= 20 ; j++){
                        arr[j] = 0;
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
