import java.util.*;
class Solution {
    public int reverse(int x) {
       boolean check = x >= 0 ? true : false;
       x = Math.abs(x);
       int sum = 0;

       while(x > 0){
        if((long) sum * 10 + x % 10 > Integer.MAX_VALUE) return 0;
        sum = sum*10 + x % 10;
        x /= 10;
       }
       
       return check ? sum : sum * -1;

    }
}