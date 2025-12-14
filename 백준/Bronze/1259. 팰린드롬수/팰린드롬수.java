import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        StringBuilder sb = new StringBuilder();
        while( !(s = br.readLine()).equals("0") ){
            StringBuilder s1 = new StringBuilder(s);
            if(s.equals(s1.reverse().toString())){
                sb.append("yes").append("\n");
            }else{
                sb.append("no").append("\n");
                
            }
        }
        System.out.println(sb);
    }
}
