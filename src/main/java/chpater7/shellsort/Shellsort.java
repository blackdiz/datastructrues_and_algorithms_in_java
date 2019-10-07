package chpater7.shellsort;

class ArraySh {

  private long[] array;
  private int nElems;

  public ArraySh(int max) {
    array = new long[max];
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

  public void shellSort() {
    int h = 1; // 間隔最小值為1
    while (h < nElems / 3) { // 直到下次h*3會超過元素長度時, 再增加h就沒有意義
      h = 3 * h + 1; // 依Knuth的公式遞增, 找出h的最大值
    }

    while (h > 0) {
      int inner, outter;
      long temp;

      // 以h當起始邊界點, 每次結束後往右移動一格再一次insertion sort
      for (outter = h; outter < nElems; outter++) {
        temp = array[outter];
        inner = outter;
        // 因為是用h做間隔, 所以比較的元素是inner - h
        while (inner > (h - 1) && array[inner - h] >= temp) {
          array[inner] = array[inner - h]; // 若下一個元素比較大就往右移動h個間隔
          inner = inner - h;
        }
        array[inner] = temp;
      }
      h = (h - 1) / 3; // 依Knuth的公式遞減, 最終會變成1
    }
  }
}

public class Shellsort {

  public static void main(String[] args) {
    int maxSize = 10;
    ArraySh arraySh = new ArraySh(maxSize);

    for (int i = 0; i < maxSize; i++) {
      arraySh.insert((int) (Math.random() * 99));
    }

    arraySh.shellSort();
    arraySh.display();
  }
}
