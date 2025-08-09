import java.util.*;
import java.io.*;
class Main {
    static int result;
    static int m;
    static boolean[] v;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = new boolean[n];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        result = 0;
        powerset(0);
        System.out.println(result);
    }
    public static void powerset(int index){
        if(index == arr.length){
            int sum = 0;
            int count = 0;
            for(int i = 0; i < arr.length;i++) {
                sum+= v[i] ? arr[i] : 0;
                count+= v[i] ? 1 : 0;
            }
            if(count!= 0 && sum == m) result++;
            return;
        }
        v[index] = true;
        powerset(index+1);
        v[index] = false;
        powerset(index+1);
    }
}