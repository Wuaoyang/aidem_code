package sort;

import java.util.Arrays;

/**
 * @author aidem
 * @date 2021-07-13
 * @description code
 */
public class MainSort {

    //    public static final int[] array = new int[]{1, 6, 3, 8, 33, 27, 66, 9, 7, 88};
    public static final int[] array = new int[]{10, 6, 3, 8, 33, 27, 66, 9, 7, 88};


    public static void main(String[] args) {
//        quickSort(array, 0, array.length-1);
//        printArray(array);
//        for (int i = 0;;){
//            System.out.println("11");
//        }
        int i = 0, j;
        j = i++;
        System.out.println(i);

        int k = 0;
        k = k++;
        System.out.println(k);

    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] array, int start, int end) {
        int length = array.length;
        if (length == 1) {
            return;
        }
        int temp = array[start], left = start, right = end;
        while (left != right) {
            while (left < right && array[right] > temp) {
                right--;
            }
            if (left < right) {
                array[left] = array[right];
            }
            while (left < right && array[left] < temp) {
                left++;
            }
            if (left < right) {
                array[right] = array[left];
            }
        }
        array[left] = temp;
        quickSort(array, start, left - 1);
        quickSort(array, left + 1, end);
    }

    private static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

}
