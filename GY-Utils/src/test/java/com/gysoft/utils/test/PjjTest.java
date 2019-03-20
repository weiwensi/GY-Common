package com.gysoft.utils.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author DJZ-PJJ
 * @Description: TODO
 * @date 2018/12/1310:14
 */
public class PjjTest {


    public static int partition(int []array,int lo,int hi){
        //固定的切分方式
        int key=array[lo];
        while(lo<hi){
            while(array[hi]>=key&&hi>lo){//从后半部分向前扫描
                hi--;
            }
            array[lo]=array[hi];
            while(array[lo]<=key&&hi>lo){//从前半部分向后扫描
                lo++;
            }
            array[hi]=array[lo];
        }
        array[hi]=key;
        return hi;
    }

    public static void sort(int[] array,int lo ,int hi){
        if(lo>=hi){
            return ;
        }
        int index=partition(array,lo,hi);
        sort(array,lo,index-1);
        sort(array,index+1,hi);
    }

    public static void main(String[] args) {
        Set<Integer> numSet = new HashSet<Integer>();
        while (true){
            Date now = new Date();
            Long lastNum = now.getTime();
            Integer last = Integer.valueOf(lastNum.toString().substring(lastNum.toString().length()-1));
            numSet.add(last);
            if(numSet.size()==10){
                break;
            }
        }
        System.out.println(numSet);

    }

}
