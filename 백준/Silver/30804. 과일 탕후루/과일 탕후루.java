import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int fruit = Integer.parseInt(st.nextToken());
            arr[i] = fruit;
        }

        int[] fruits = new int[10];

        int start = 0;
        int sp = 0;
        int max = 0;

        for (int end = 0; end < N; end++) {
            if (fruits[arr[end]] == 0) {
                sp++;
            }
            fruits[arr[end]]++;
            
            while (sp > 2) {
                fruits[arr[start]]--;
                if (fruits[arr[start]] == 0) {
                    sp--;
                }
                start++;
            }
            
            max = Math.max(max, end - start + 1);
        }

        System.out.println(max);
    }
}
