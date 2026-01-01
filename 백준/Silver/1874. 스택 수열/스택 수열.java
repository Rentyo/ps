
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int index = 1;
        Stack<Integer> st = new Stack<>();
        for(int i = 1; i <= n; i++){
            int now = Integer.parseInt(br.readLine());
            if(!st.isEmpty() && now < st.peek()){
                System.out.println("NO");
                return;
            }
            while(now >= index){
                st.add(index++);
                sb.append("+").append("\n");
            }
            st.pop();
            sb.append("-").append("\n");
        }
        System.out.println(sb);

    }
}
