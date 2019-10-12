package chapter7.quicksort;

class ArrayMedian {

  private long[] array;
  private int nElems;

  public ArrayMedian(int maxSize) {
    array = new long[maxSize];
    nElems = 0;
  }

  public void insert(long value) {
    array[nElems++] = value;
  }

  public void display() {
    System.out.print("A=");

    for (int i = 0; i < nElems; i++) {
      System.out.print(array[i] + " ");
    }

    System.out.println("");
  }

  public void quickSort() {
    recQuickSort(0, nElems - 1);
  }

  private void recQuickSort(int left, int right) {
    int size = right - left + 1;
    if (size <= 3) {
      manualSort(left, right);
    } else {
      // 從子陣列中挑出最左、最右、中間三個位置中的中位數做為pivot
      long pivot = medianOf3(left, right);

      int pivotIndex = partitionIt(left, right, pivot);

      recQuickSort(left, pivotIndex - 1);
      recQuickSort(pivotIndex + 1, right);
    }
  }

  private long medianOf3(int left, int right) {
    int center = (left + right) / 2;
    // 對這3個元素做bubble sort
    if (array[left] > array[center]) {
      swap(left, center);
    }
    if (array[center] > array[right]) {
      swap(center, right);
    }
    if (array[left] > array[center]) {
      swap(left, center);
    }
    swap(center, right - 1); // 將排序後的中位數移到最右位左邊一格

    return array[right - 1];
  }

  private int partitionIt(int left, int right, long pivot) {
    // 因為在medianOf3中, left已分到正確的組別裡, 因此不用參加partition, 所以起始點為left, 每次遍歷時為++left
    int leftPtr = left;
    // 因為在medianOf3中, right已分到加partition正確的組別, 而right - 1為pivot, 兩者都不需要參加partition,
    // 所以超始點為right - 1, 每次遍歷時為--right
    int rightPtr = right - 1;

    while (true) {
      // 和之前的程式相比, 移除了left < right的檢查, 因為pivot為中位數, 所以至少right一定為>= pivot
      // 所以一定會有滿足array[++leftPtr] >= pivot而終止while迴圈
      while (array[++leftPtr] < pivot) {

      }
      // 和之前的程式相比, 移除了right > left的檢查, 因為pivot為中位數, 所以至少left一定為<= pivot
      // 所以一定會有滿足array[--rightPtr] <= pivot而終止while迴圈
      while (array[--rightPtr] > pivot) {

      }

      if (leftPtr >= rightPtr) {
        break;
      } else {
        swap(leftPtr, rightPtr);
      }
    }

    swap(leftPtr, right - 1);

    return leftPtr;
  }

  // 針對子陣列的元素<= 3時, 因為沒辦法執行medianOf3, 而無法用quicksort時的排序
  private void manualSort(int left, int right) {
    int size = right - left + 1;
    if (size <= 1) {
      return;
    }

    // 只有兩個元素時就直接比大小決定位置
    if (size == 2) {
      if (array[left] > array[right]) {
        swap(left, right);
      }
    } else {
      // 3個元素時用bubble sort排序位置
      if (array[left] > array[right - 1]) {
        swap(left, right - 1);
      }
      if (array[right - 1] > array[right]) {
        swap(right - 1, right);
      }
      if (array[left] > array[right - 1]) {
        swap(left, right - 1);
      }
    }
  }

  private void swap(int index1, int index2) {
    long temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
}

public class QuickSort2 {

  public static void main(String[] args) {
    int maxSize = 16;
    ArrayMedian arrayMedian = new ArrayMedian(maxSize);

    for (int i = 0; i < maxSize; i++) {
      arrayMedian.insert((int) (Math.random() * 99));
    }

    arrayMedian.display();

    arrayMedian.quickSort();

    arrayMedian.display();
  }
}
