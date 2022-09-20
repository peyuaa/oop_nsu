/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package task_1_1_1;

/**
 * HeapSort implementationg
 */
public class HeapSort {

    /**
     * prints array.
     *
     * @param arr
     */
    static void printArray(int arr[])
    {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * sorts using heapsort.
     *
     * @param arr
     */
    public void heapSort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    /**
     * build the max heap.
     *
     * @param arr
     * @param n
     * @param i
     */
    private void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    /**
     * entry point.
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
