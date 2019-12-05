// 對小於cutoff的子陣列改用insertion sort處理
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

  private void recQuickSort(int left, int right) {
    int size = right - left + 1;
    if (size <= 9) { // 依Knuth的建議，<= 9的子陣列改用insertion sort
      insertionSort(left, right);
    } else {
      long pivot = medianOf3(left, right);

      int partitionIndex = partitionIt(left, right, pivot);

      recQuickSort(left, partitionIndex - 1);
      recQuickSort(partitionIndex + 1, right);
    }
  }

  private long medianOf3(int left, int right) {
    int center = (left + right) / 2;

    if (array[left] > array[center]) {
      swap(left, right);
    }
    if (array[center] > array[right]) {
      swap(center, right);
    }
    if (array[left] > array[center]) {
      swap(left, center);
    }

    swap(center, right - 1);

    return array[right - 1];
  }

  private int partitionIt(int left, int right, long pivot) {
    int leftPtr = left;
    int rightPtr = right - 1;

    while (true) {
      while (array[++leftPtr] < pivot) {
      }

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

  private void insertionSort(int left, int right) {
    int in, out;
    for (out = left + 1; out <= right; out++) {
      long temp = array[out];
      in = out;
      while (in > left && array[in - 1] > temp) {
        array[in] = array[in - 1];
        in--;
      }
      array[in] = temp;
    }
  }

  private void swap(int index1, int index2) {
    long temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
}

public class QuickSort3 {

  public static void main(String[] args) {
    int maxSize = 16;
    ArrayIns arrayIns = new ArrayIns(maxSize);

    for (int i = 0; i < maxSize; i++) {
      arrayIns.insert((int) (Math.random() * 99));
    }

    arrayIns.display();

    arrayIns.quickSort();

    arrayIns.display();
  }
}
