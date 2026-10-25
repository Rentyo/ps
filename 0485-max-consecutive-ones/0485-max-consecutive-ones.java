class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int i = 0;
        int maxLength = 0;
        int count = 0;
        while(i < nums.length){
            if(nums[i] == 1){
                count++;
            }else{
                maxLength = Math.max(count, maxLength);
                count = 0;
            }
            i++;
        }
        maxLength = Math.max(count, maxLength);
        return maxLength;
    }
}