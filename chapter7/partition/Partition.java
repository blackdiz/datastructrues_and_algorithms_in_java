class ArrayPartition {

  private long[] array;
  private int nElems;

  public ArrayPartition(int maxSize) {
    array = new long[maxSize];
    nElems = 0;
  }

  public void insert(long value) {
    array[nElems++] = value;
  }

  public int size() {
    return nElems;
  }

  public void display() {
    System.out.print("A=");
    for (int i = 0; i < nElems; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println("");
  }

  // left為分組起始index
  // right為分組終點index
  // pivot為分組基準數
  public int partitionInt(int left, int right, long pivot) {
    int leftPtr = left - 1; // leftPtr指向從左開始找到大於pivot時的index
    int rightPtr = right + 1; // rightPtr指向從右開始找到小於pivot時的index
    while (true) {
      while (leftPtr < right && array[++leftPtr] < pivot) {
        // 當leftPtr < pivot時結束迴圈, 此時leftPtr指向的資料是 >= pivot
        // 或者當整個陣列的資料都小於pivot時, 會因leftPtr一直累加到 > right而結束
      }
      while (rightPtr > left && array[--rightPtr] > pivot) {
        // 當rightPtr > pivot時結束迴圈, 此時rightPtr指向的資料是 <= pivot
        // 或者當整個陣列的資料都大於pivot時, 會因rightPtr一直遞減到 < left而結束
      }
      if (leftPtr >= rightPtr) {
        // 當leftPtr指向的index和rightPtr指向的index交錯時, 表示所有資料都已被掃過一遍, 所以可以結束
        break;
      } else {
        // 交換leftPtr和rightPtr兩邊的資料
        swap(leftPtr, rightPtr);
      }
    }

    return leftPtr; // 回傳最後分組結束時的邊界index
  }

  public void swap(int index1, int index2) {
    long temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
}

public class Partition {

  public static void main(String[] args) {
    int maxSize = 16;
    ArrayPartition arrayPartition = new ArrayPartition(maxSize);
    for (int i = 0; i < maxSize; i++) {
      arrayPartition.insert((int) (Math.random() * 199));
    }

    arrayPartition.display();
    long pivot = 99;

    System.out.print("Pivot is " + pivot);

    int size = arrayPartition.size();
    int partitionIndex = arrayPartition.partitionInt(0, size - 1, pivot);

    System.out.println(", Partition is at index " + partitionIndex);

    arrayPartition.display();
  }
}
