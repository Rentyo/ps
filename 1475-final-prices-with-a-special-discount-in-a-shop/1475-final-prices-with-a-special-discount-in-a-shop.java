class Solution {
    public int[] finalPrices(int[] prices) {
        int[] temp = new int[prices.length];

        for(int i = 0; i < temp.length; i++){
            temp[i] = prices[i];
            for(int j = i+1; j < temp.length; j++){
                if(prices[i] >= prices[j]){
                    temp[i] = prices[i] - prices[j];
                    break;
                }
            }
        }
        return temp;
    }
}