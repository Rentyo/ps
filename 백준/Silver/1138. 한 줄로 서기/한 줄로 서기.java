import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[n];

        // 3. 키가 1인 사람부터 n인 사람까지 순차적으로 배치
        for (int i = 0; i < n; i++) {
            int count = arr[i]; // 왼쪽에 있어야 할 큰 사람의 수
            int emptySpaceCount = 0;

            for (int j = 0; j < n; j++) {
                // 현재 칸이 비어있는 경우(0)만 카운트함
                // 빈 칸은 나중에 들어올 '나보다 키 큰 사람'들을 위한 자리임
                if (result[j] == 0) {
                    if (emptySpaceCount == count) {
                        result[j] = i + 1; // 사람의 번호는 1부터 시작
                        break;
                    }
                    emptySpaceCount++;
                }
            }
        }

        // 4. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int val : result) {
            sb.append(val).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
