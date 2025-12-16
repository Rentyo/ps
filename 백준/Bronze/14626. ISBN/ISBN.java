import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String isbn = br.readLine().trim();

        int starIdx = isbn.indexOf('*');         
        int checkDigit = isbn.charAt(12) - '0';   

        int sum = 0;
        for (int i = 0; i < 12; i++) {
            if (i == starIdx) continue;
            int digit = isbn.charAt(i) - '0';
            int weight = (i % 2 == 0) ? 1 : 3;    
            sum += digit * weight;
        }

        int starWeight = (starIdx % 2 == 0) ? 1 : 3;

        for (int x = 0; x <= 9; x++) {
            if ((sum + x * starWeight + checkDigit) % 10 == 0) {
                System.out.println(x);
                return;
            }
        }
    
    }
}
