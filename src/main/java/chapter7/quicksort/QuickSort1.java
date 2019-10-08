package chapter7.quicksort;

import javax.lang.model.util.Elements.Origin;

class ArrayIns {

  private long[] array;
  private int nElems;

  public ArrayIns(int maxSize) {
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

  public void recQuickSort(int left, int right) {
    if (right - left <= 0) {
      return;
    } else {
      long pivot = array[right];

      int pivotIndex = partitionIt(left, right, pivot);
      // 因為pivot已經在最終排序的正確位置, 因此不用加入下次quicksort
      recQuickSort(left, pivotIndex - 1);
      recQuickSort(pivotIndex + 1, right);
    }
  }

  private int partitionIt(int left, int right, long pivot) {
    // leftPtr指向從左邊開始找到大於pivot時的index
    int leftPtr = left - 1;
    // rightPtr指向從右邊開始找到小於pivot時的index, 因為最右邊為pivot本身, 所以由right開始,
    // 每次取值比較時先-1再取值
    int rightPtr = right;

    while (true) {
      // 找到 > pivot的值時結束迴圈
      // 和Partition.java裡不同，這裡不用加上left < right的判斷, 因為最右邊本身就是pivot
      // 所以array[++leftPtr]到最右邊時必定 == pivot 而不會滿足 < pivot
      while (array[++leftPtr] < pivot) {

      }
      // 找到 < pivot的值時結束迴圈
      while (rightPtr > 0 && array[--rightPtr] > pivot) {

      }

      // 當leftPtr和rightPtr交錯時表示陣列已經遍歷比較完, 因此結束
      if (leftPtr >= rightPtr) {
        break;
      } else {
        swap(leftPtr, rightPtr);
      }
    }

    // 最後leftPtr指向的位置會是右邊所有元素都 > pivot的子陣列的最左邊, 因此將它和最右邊pivot的位置
    // 互換, 這樣pivot就會移到最終排序時的正確位置
    swap(leftPtr, right);

    return leftPtr;
  }

  private void swap(int index1, int index2) {
    long temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
}

public class QuickSort1 {

  public static void main(String[] args) {
    int maxSize = 16;
    ArrayIns arrayIns = new ArrayIns(16);

    for (int i = 0; i < maxSize; i++) {
      arrayIns.insert((int) (Math.random() * 199));
    }

    arrayIns.display();
    arrayIns.quickSort();
    arrayIns.display();
  }
}
