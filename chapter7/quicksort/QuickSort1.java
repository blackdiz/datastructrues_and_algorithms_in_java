class Array {

  private long[] array;
  private int nElems;

  public Array(int maxSize) {
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
      // 這裡的判斷用 right - left <= 0 是有用途的，如果 pivot 本身已經是最大的數字且已排在最右邊，則 left = pivot index + 1 會超出 right
      // 這時就不必再做排序
      return;
    } else {
      long pivot = array[right];

      int pivotIndex = partitionIt(left, right, pivot);
      // 因為 pivot 已經在最終排序的正確位置, 因此不用加入下次 quicksort
      recQuickSort(left, pivotIndex - 1);
      recQuickSort(pivotIndex + 1, right);
    }
  }

  private int partitionIt(int left, int right, long pivot) {
    // leftPtr 指向從左邊開始找到大於 pivot 時的 index, 用 left - 1 是因為等一下會用 ++leftPar 取 array
    // 中值來和 pivot 比較
    int leftPtr = left - 1;
    // rightPtr 指向從右邊開始找到小於 pivot 時的 index, 因為最右邊為 pivot 本身, 所以由 right 開始,
    // 每次取值比較時先-1再取值
    int rightPtr = right;

    while (true) {
      // 找到 > pivot的值時結束迴圈
      // 和 Partition.java 裡不同，這裡不用加上 left < right 的判斷, 因為最右邊本身就是 pivot
      // 所以 array[++leftPtr] 到最右邊時必定 == pivot 而不會滿足 < pivot,
      // 用 ++leftPtr 是因為如果用 leftPar++, 則如果 array 中所有元素 < pivot 時, 
      // 最終 leftPtr 的位置會在 array 長度 + 1, 而我們最後會交換 leftPtr 和 pivot 的位置,
      // 這樣會造成 index of bounds
      while (array[++leftPtr] < pivot) {

      }
      // 找到 < pivot的值時結束迴圈
      while (rightPtr > 0 && array[--rightPtr] > pivot) {

      }

      // 當 leftPtr 和 rightPtr 交錯時表示陣列已經遍歷比較完, 因此結束
      if (leftPtr >= rightPtr) {
        break;
      } else {
        swap(leftPtr, rightPtr);
      }
    }

    // 最後 leftPtr 指向的位置會是右邊所有元素都 > pivot 的子陣列的最左邊, 因此將它和最右邊 pivot 的位置
    // 互換, 這樣 pivot 就會移到最終排序時的正確位置
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
    Array array = new Array(maxSize);

    for (int i = 0; i < maxSize; i++) {
      array.insert((int) (Math.random() * 199));
    }

    array.display();
    array.quickSort();
    array.display();
  }
}
