import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int time = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < time; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if(order.equals("push")){
                stack.push(Integer.parseInt(st.nextToken()));
            }else if(order.equals("top")){
                sb.append(stack.empty() ? -1 : stack.peek()).append("\n");
            }else if(order.equals("pop")){
                sb.append(stack.empty() ? -1 : stack.pop()).append("\n");
            }else if(order.equals("empty")){
                sb.append(stack.empty() ? 1 : 0).append("\n");
            }else{
                sb.append(stack.size()).append("\n");
            }
        }
        System.out.println(sb);
    }
}