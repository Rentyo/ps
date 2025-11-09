
import java.io.*;
import java.util.*;
public class Main {
    // 알파벳에 해당하는 수 할당
    static int[] sel;
    // 단어에 알파벳 개수 확인
    static ArrayList<Integer>[] alpha;
    // 존재하는 알파벳 확인(비트마스킹)
    static int isAlpha;
    // 최대값
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        alpha = new ArrayList[N];
        for(int i = 0; i < N; i++){
            alpha[i] = new ArrayList<>();
        }
        isAlpha= 0;
        int count = 0;
        sel = new int['Z'-'A'+1];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < s.length(); j++){
                alpha[i].add(s.charAt(j) - 'A');
                if((isAlpha & (1 << s.charAt(j)-'A')) == 0){
                    count++;
                    isAlpha |= (1 << s.charAt(j)-'A');
                }
            }
        }
        max = 0;
        recursive(count, 9);
        System.out.println(max);
    }
    public static void recursive(int count, int num){
        if(count == 0){
            calculate();
            return;
        }

        for(int i = 0; i < 'Z'-'A'+1; i++){
            if((isAlpha & (1 << i)) == 0 || sel[i] != 0) continue;
            sel[i] = num;
            recursive(count-1, num-1);
            sel[i] = 0;
        }

    }
    public static void calculate(){
        int sum = 0;
        for(int i = 0; i < alpha.length; i++){
            int total = 0;
            int[] arr = new int[10];
            for(int j = 0; j < alpha[i].size(); j++){
                total = total * 10 + sel[alpha[i].get(j)];
            }
            sum += total;
        }
        max = Math.max(sum, max);


    }
}
