class DArray {

  private long[] theArray;
  private int nElems;

  public DArray(int max) {
    theArray = new long[max];
    nElems = 0;
  }

  public void insert(long value) {
    theArray[nElems] = value;
    nElems++;
  }

  public void display() {
    for (int i = 0; i < nElems; i++) {
      System.out.print(theArray[i] + " ");
    }
    System.out.println("");
  }

  public void mergeSort() {
    long[] workSpace = new long[nElems];
    recMergeSort(workSpace, 0, --nElems);
  }

  private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
    if (lowerBound == upperBound) {
      return; // 表示陣列已分割到只剩下一個元素, 所以不用排序, 直接結束此次遞迴
    } else {
      int mid = (lowerBound + upperBound) / 2; // 找到此次遞迴的分割中心點
      recMergeSort(workSpace, lowerBound, mid); // 處理分割後的前半段
      recMergeSort(workSpace, mid + 1, upperBound); // 處理分割後的後半段
      merge(workSpace, lowerBound, mid + 1, upperBound); // 將已排序的前半段和後半段進行合併排序
    }
  }

  private void merge(long[] workSpace, int lowerPtr, int highPtr, int upperBound) {
    int i = 0; // 暫存用array workSpace的index
    int lowerBound = lowerPtr; // 此次合併排序對應原始陣列部分的最左邊index
    int mid = highPtr - 1; // 中心點
    int n = upperBound - lowerBound + 1; // 此次合併的總元素數

    // 當lowerPtr > mid時, 表示左半邊的陣列元素已經全部比完
    // 當highPtr > mid時, 表示右半邊的陣列元素已經全部比完
    // 所以只有當這兩個情況都沒有發生時才從兩邊取元素出來比較
    while (lowerPtr <= mid && highPtr <= upperBound) {
      if (theArray[lowerPtr] < theArray[highPtr]) {
        workSpace[i++] = theArray[lowerPtr++];
      } else {
        workSpace[i++] = theArray[highPtr++];
      }
    }

    // 當執行到此段時, 表示有某一邊的陣列元素已經全部比完
    // 此時如果lowerPtr <= mid, 表示左半邊的元素還有剩餘的元素
    // 因為是已排序, 所以直接複製到workSpace裡即可
    while (lowerPtr <= mid) {
      workSpace[i++] = theArray[lowerPtr++];
    }

    // 當執行到此段時, 表示有某一邊的陣列元素已經全部比完
    // 此時如果highPtr < upperBound, 表示右半邊的元素還有剩餘的元素
    // 因為是已排序, 所以直接複製到workSpace裡即可
    while (highPtr <= upperBound) {
      workSpace[i++] = theArray[highPtr++];
    }

    // 最後將workSpace內合併並排序兩邊陣列後的結果複製回原始的array中,
    // 假設此次合併的左邊陣列index是2-6, 表示合併排序的是原始陣列的2-6部分,
    // 所以從workSpace複製回去時由原始陣列的2開始放入
    for (int j = 0; j < n; j++) {
      theArray[lowerBound + j] = workSpace[j];
    }
  }
}

public class MergeSort {

  public static void main(String[] args) {
    DArray arr = new DArray(100);
    arr.insert(64);
    arr.insert(21);
    arr.insert(33);
    arr.insert(70);
    arr.insert(12);
    arr.insert(85);
    arr.insert(44);
    arr.insert(3);
    arr.insert(99);
    arr.insert(0);
    arr.insert(108);
    arr.insert(36);

    arr.display();

    arr.mergeSort();

    arr.display();
  }
}
