import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            String command = br.readLine(); // RDD 등 명령어
            int n = Integer.parseInt(br.readLine()); // 배열 요소 개수

            // 1. 문자열 [1,2,3,4] 파싱하기
            String input = br.readLine();
            Deque<Integer> deque = new ArrayDeque<>();

            // 앞뒤 [ ] 제거 후 ,를 기준으로 분리
            StringTokenizer st = new StringTokenizer(input.substring(1, input.length() - 1), ",");
            while (st.hasMoreTokens()) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            sb.append(solve(command, deque)).append("\n");
        }
        System.out.println(sb);
    }

    public static String solve(String command, Deque<Integer> deque) {
        boolean isReversed = false; // 현재 뒤집힌 상태인지 체크

        for (char c : command.toCharArray()) {
            if (c == 'R') {
                isReversed = !isReversed; // 실제 뒤집지 않고 상태만 토글
            } else { // 'D' 명령어
                if (deque.isEmpty()) {
                    return "error";
                }

                // 뒤집힌 상태에 따라 앞 또는 뒤에서 제거
                if (isReversed) {
                    deque.removeLast();
                } else {
                    deque.removeFirst();
                }
            }
        }

        // 2. 결과 출력 형식 맞추기 ([1,2,3] 형태)
        return makeString(deque, isReversed);
    }

    public static String makeString(Deque<Integer> deque, boolean isReversed) {
        StringBuilder sb = new StringBuilder("[");

        while (!deque.isEmpty()) {
            // 상태에 따라 앞에서 꺼낼지 뒤에서 꺼낼지 결정
            sb.append(isReversed ? deque.removeLast() : deque.removeFirst());
            if (!deque.isEmpty()) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}