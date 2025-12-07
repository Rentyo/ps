import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t_c = 1; t_c <= T; t_c++){
            String s = br.readLine();
            int point = 0;
            int index = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == 'O'){
                    point += ++index;
                }else{
                    index = 0;
                }
            }
            sb.append(point).append("\n");
        }
        System.out.println(sb);
    }
}
