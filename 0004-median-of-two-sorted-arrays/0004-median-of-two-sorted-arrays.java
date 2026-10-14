import java.util.*;
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] temp = new int[nums1.length + nums2.length];

        int index1 = 0;
        int index2 = 0;

        for(int i = 0; i < temp.length; i++){
            if(index1 >= nums1.length){
                temp[i] = nums2[index2++];
            }else if(index2 >= nums2.length){
                temp[i] = nums1[index1++];
            }
            else if(nums1[index1] >= nums2[index2]){
                temp[i] = nums2[index2++];
            }else{
                temp[i] = nums1[index1++];
            }
        }
        if( (nums1.length + nums2.length) % 2 == 0){
            return ((double)temp[ (nums1.length + nums2.length) / 2 - 1] + temp[ (nums1.length + nums2.length) / 2] ) / 2 ;
        }else{
            return (double)temp[ (nums1.length + nums2.length)/ 2] ;
        }
    }
}