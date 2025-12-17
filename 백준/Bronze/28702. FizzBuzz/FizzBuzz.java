import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();
        int s4;
        if((s1.charAt(0) >='0' && s1.charAt(0) <='9')){
            s4 = Integer.parseInt(s1) + 3;
        }else if(s2.charAt(0) >='0' && s2.charAt(0) <='9'){
            s4 = Integer.parseInt(s2) + 2;
        }else{
            s4 = Integer.parseInt(s3) + 1;
        }


        if(s4 % 3 == 0){
            if(s4 % 5 == 0){
                System.out.println("FizzBuzz");
            }else{
                System.out.println("Fizz");
            }
        }else if(s4 % 5 == 0){
            System.out.println("Buzz");
        }else{
            System.out.println(s4);
        }
    }
}