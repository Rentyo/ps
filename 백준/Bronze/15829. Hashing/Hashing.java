import java.io.*;
public class Main {
    static final int M = 1234567891;
    static final int R = 31;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int N= Integer.parseInt(br.readLine());
        String s = br.readLine();

        int result = 0;
        for(int i = 0; i < N; i++){
            int val = s.charAt(i) - 'a' + 1;         
            result = (result + (val * getPow(i)) % M) % M;  
        }
        System.out.println(result);
    }    
    public static int getPow(int exp){
        if(exp == 1) return R;
        else if(exp == 0) return 1;
        int div = getPow(exp / 2) % M;
        if(exp % 2 == 0){
            return (div * div) % M;
        }else{
            return (((div * div) % M) * R) % M;
        }
    }
}
