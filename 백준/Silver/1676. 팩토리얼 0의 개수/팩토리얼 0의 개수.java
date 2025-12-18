import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int count = 0;
        for (int div = 5; div <= N; div *= 5) {
            count += N / div;
        }
        System.out.println(count);
    }
}