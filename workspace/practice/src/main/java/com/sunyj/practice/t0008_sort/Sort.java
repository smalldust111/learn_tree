package com.sunyj.practice.t0008_sort;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * 排序算法
 * @author syj
 * @since 2019年7月11日 下午4:37:11
 */
public class Sort {
	public static int[] array = new int[] {2,1,5,3,9,6,4,10,8,7};
	
	/**
	 * 将数组当前索引字段值和下一个索引字段值交换
	 * @param arr
	 */
	public static void change(int[] arr, int sourceIndex,int targetIndex) {
		System.out.printf("%d -> %d \n",arr[sourceIndex],arr[targetIndex]);
		int temp = arr[sourceIndex];
		arr[sourceIndex] = arr[targetIndex];
		arr[targetIndex] = temp;
	}
	/**
	 * 冒泡排序
	 */
	public static void bubbleSort() {
		int[] arr = Arrays.copyOf(array, array.length);
		System.out.println(ArrayUtils.toString(arr));
		if(array.length < 1) {
			System.out.println(ArrayUtils.toString(arr));
			return;
		}
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length-1-i;j++) {
				if(arr[j+1] < arr[j]) {
					change(arr, j, j+1);
				}
//				System.out.printf("第%d次排序后结果：%s\n",j,ArrayUtils.toString(arr));
			}
		}
		System.out.println(ArrayUtils.toString(arr));
		System.out.println(ArrayUtils.toString(array));
	}
	public static void selectionSort() {
		int[] arr = Arrays.copyOf(array, array.length);
		System.out.println(ArrayUtils.toString(arr));
		if(arr.length < 1) {
			System.out.println(ArrayUtils.toString(arr));
			return;
		}
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr.length;j++) {
				
			}
		}
		
	}
	public static void main(String[] args) {
		bubbleSort();
	}
	
}
