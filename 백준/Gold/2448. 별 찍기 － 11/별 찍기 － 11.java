import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static char[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 도화지 준비: 공백으로 초기화
        map = new char[n][2 * n - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], ' ');
        }

        // 별 그리기 시작 (첫 번째 꼭짓점 위치: 행 0, 열 n-1)
        drawStars(0, n - 1, n);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2 * n - 1; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void drawStars(int r, int c, int size) {
        if (size == 3) {
            // 가장 작은 삼각형 그리기
            map[r][c] = '*';
            map[r + 1][c - 1] = map[r + 1][c + 1] = '*';
            map[r + 2][c - 2] = map[r + 2][c - 1] = map[r + 2][c] = map[r + 2][c + 1] = map[r + 2][c + 2] = '*';
            return;
        }

        // 크기를 절반으로 줄여 3개의 구역으로 재귀 호출
        int nextSize = size / 2;
        drawStars(r, c, nextSize); // 위쪽
        drawStars(r + nextSize, c - nextSize, nextSize); // 왼쪽 아래
        drawStars(r + nextSize, c + nextSize, nextSize); // 오른쪽 아래
    }
}