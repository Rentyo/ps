import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] count = new int[10];
        int num = 1;
        for(int i = 0; i < 3; i ++){
            num *= Integer.parseInt(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            count[num%10]++;
            num/=10;
        }
        
        for(int i = 0; i <= 9; i++){
            sb.append(count[i]).append("\n");
        }
        System.out.println(sb);
    }
}