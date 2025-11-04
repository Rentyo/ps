
import java.io.*;
import java.util.*;

public class Main {
    static char[] c1_letter = {'a', 'e', 'i', 'o', 'u'};
    static int L;
    static boolean[] v;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        v = new boolean['z' - 'a' + 1];
        Arrays.fill(v, true);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            v[st.nextToken().charAt(0) - 'a'] = false;
        }
        permutation(0, 0, new char[L], 0, 0);
        System.out.println(sb);
    }

    public static void permutation(int count, int idx, char[] letters, int c1, int c2) {
        if (count == L) {
            if(c1 >= 1 && c2 >= 2) {
                for (int i = 0; i < letters.length; i++) {
                    sb.append(letters[i]);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = idx; i < 'z' - 'a' + 1; i++) {
            if (!v[i]) {
                v[i] = true;
                letters[count] = (char) ('a' + i);
                if(letters[count] == c1_letter[0] || letters[count] == c1_letter[1] || letters[count] == c1_letter[2] || letters[count] == c1_letter[3] || letters[count] == c1_letter[4])
                {
                    permutation(count + 1, i + 1, letters, c1+1, c2);
                }else{
                    permutation(count + 1, i + 1, letters, c1, c2+1);
                }
                v[i] = false;
            }
        }
    }

}
