import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();

        int r1 = Integer.parseInt(A) + Integer.parseInt(B) - Integer.parseInt(C);
        int r2 = Integer.parseInt(A + B) - Integer.parseInt(C);
        StringBuilder sb = new StringBuilder().append(r1).append("\n").append(r2);
        System.out.println(sb);
    }
}