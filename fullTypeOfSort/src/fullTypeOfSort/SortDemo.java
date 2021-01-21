package fullTypeOfSort;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public final class SortDemo {

    private static final int[] NUMBERS =
            {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};


    public static void main(String[] args) {
        System.out.println(Arrays.toString(NUMBERS) + " Number");
        insertSort(NUMBERS);
        shellSort(NUMBERS);
        selectSort(NUMBERS);
        bubbleSort(NUMBERS);
        heapSort(NUMBERS);
        quickSort(NUMBERS);
        mergingSort(NUMBERS);
        radixSort(NUMBERS);
    }


    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            for (; j >= 0 && array[j] > temp; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        System.out.println(Arrays.toString(array) + " insertSort");
    }


    public static void shellSort(int[] array) {
        int i;
        int j;
        int temp;
        int gap = 1;
        int len = array.length;
        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }
        for (; gap > 0; gap /= 3) {
            for (i = gap; i < len; i++) {
                temp = array[i];
                for (j = i - gap; j >= 0 && array[j] > temp; j -= gap) {
                    array[j + gap] = array[j];
                }
                array[j + gap] = temp;
            }
        }
        System.out.println(Arrays.toString(array) + " shellSort");
    }

    public static void selectSort(int[] array) {
        int position = 0;
        for (int i = 0; i < array.length; i++) {
            int j = i + 1;
            position = i;
            int temp = array[i];
            for (; j < array.length; j++) {
                if (array[j] < temp) {
                    temp = array[j];
                    position = j;
                }
            }
            array[position] = array[i];
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array) + " selectSort");
    }


    public static void bubbleSort(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array) + " bubbleSort");
    }


    public static void heapSort(int[] array) {
        int len = array.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len, array);
        }
        for (int i = len; i > 0; i--) {
            swap(0, i, array);
            maxHeapify(0, i - 1, array);
        }
        System.out.println(Arrays.toString(array) + " heapSort");
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void maxHeapify(int index, int len, int[] arr) {
        int li = (index << 1) + 1; 
        int ri = li + 1;           
        int cMax = li;             
        if (li > len) {
            return;       
        }
        if (ri <= len && arr[ri] > arr[li]) 
        {
            cMax = ri;
        }
        if (arr[cMax] > arr[index]) {
            swap(cMax, index, arr);      
            maxHeapify(cMax, len, arr);  
        }
    }


    public static void quickSort(int[] array) {
        _quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array) + " quickSort");
    }


    private static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];    
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }


            list[low] = list[high];   
            while (low < high && list[low] <= tmp) {
                low++;
            }


            list[high] = list[low];   
        }
        list[low] = tmp;              
        return low;                  
    }


    private static void _quickSort(int[] list, int low, int high) {
        if (low < high) {
            int middle = getMiddle(list, low, high);  
            _quickSort(list, low, middle - 1);  
            _quickSort(list, middle + 1, high);  
        }
    }


    public static void mergingSort(int[] array) {
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array) + " mergingSort");
    }

    private static void sort(int[] data, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            sort(data, left, center);
            sort(data, center + 1, right);
            merge(data, left, center, right);
        }
    }

    private static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        int third = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }

        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }

        while (left <= center) {
            tmpArr[third++] = data[left++];
        }

        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }


    public static void radixSort(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int time = 0;
        while (max > 0) {
            max /= 10;
            time++;
        }


        ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> queue1 = new ArrayList<>();
            queue.add(queue1);
        }


        for (int i = 0; i < time; i++) {
            for (int anArray : array) {
                int x = anArray % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(anArray);
                queue.set(x, queue2);
            }
            int count = 0;
            for (int k = 0; k < 10; k++) {
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    array[count] = queue3.get(0);
                    queue3.remove(0);
                    count++;
                }
            }
        }
        System.out.println(Arrays.toString(array) + " radixSort");
    }
}