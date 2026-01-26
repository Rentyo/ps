import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a =  Integer.parseInt(br.readLine());
        int b =  Integer.parseInt(br.readLine());
        int count = 0;
        for (long z = 1; ; z++) {
            long coolNumber = z * z * z * z * z * z;

            if (coolNumber > b) break; // b를 넘어가면 더 이상 볼 필요 없음
            if (coolNumber >= a) {    // a보다 크거나 같으면 범위 내에 있는 것
                count++;
            }
        }
        System.out.println(count);

    }
}
