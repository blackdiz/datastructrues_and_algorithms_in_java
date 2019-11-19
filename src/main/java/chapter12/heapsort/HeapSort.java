package chapter12.heapsort;

class Node {

  private int key;

  public Node(int key) {
    this.key = key;
  }

  public int getKey() {
    return key;
  }

  public void setKey() {
    this.key = key;
  }
}

class Heap {

  private Node[] heapArray;
  private int maxSize;
  private int currentSize;

  public Heap(int size) {
    heapArray = new Node[size];
    maxSize = size;
    currentSize = 0;
  }

  public boolean insert(int key) {
    if (currentSize == maxSize) {
      return false;
    }

    Node newNode = new Node(key);
    heapArray[currentSize] = newNode;
    tackleUp(currentSize++);

    return true;
  }

  public Node remove() {
    Node top = heapArray[0];

    heapArray[0] = heapArray[--currentSize];
    trickleDown(0);

    return top;
  }

  public void insertAt(int index, Node newNode) {
    heapArray[index] = newNode;
    currentSize++;
  }

  private void tackleUp(int bottomIndex) {
    int parentIndex = (bottomIndex - 1) / 2;
    Node bottom = heapArray[bottomIndex];

    while (bottomIndex > 0 &&
        heapArray[bottomIndex].getKey() > heapArray[parentIndex].getKey()) {
      heapArray[parentIndex] = heapArray[bottomIndex];
      bottomIndex = parentIndex;
    }

    heapArray[bottomIndex] = bottom;
  }

  public void trickleDown(int topIndex) {
    Node top = heapArray[topIndex];
    int largerChildIndex;

    while (topIndex < currentSize / 2) { // 如果目標 node 的 index < currentSize / 2 表示有 child
      int leftChildIndex = topIndex * 2 + 1;
      int rightChildIndex = leftChildIndex + 1;

      if (rightChildIndex < currentSize &&
          // 計算出來的 right child index > currentSize 表示沒有 right child
          heapArray[rightChildIndex].getKey() > heapArray[leftChildIndex].getKey()) {
        largerChildIndex = rightChildIndex;
      } else {
        largerChildIndex = leftChildIndex;
      }

      if (heapArray[largerChildIndex].getKey() >= heapArray[topIndex].getKey()) {
        break;
      } else {
        // 如果 key 較大的 child 的 key 比目標 node 的 key 大就交換位置
        heapArray[topIndex] = heapArray[largerChildIndex];
        topIndex = largerChildIndex;
      }
    }

    heapArray[topIndex] = top;
  }
}

public class HeapSort {

  public static void main(String[] args) {
    int size = 32;
    Heap heap = new Heap(size);

    for (int i = 0; i < size; i++) {
      int random = (int) (Math.random() * 100);
      Node newNode = new Node(random);
      heap.insertAt(i, newNode);
    }

    for (int i = size / 2 - 1; i >= 0; i--) {
      heap.trickleDown(i);
    }

    for (int i = size - 1; i >= 0; i--) {
      Node biggestNode = heap.remove();
      heap.insertAt(i, biggestNode);
    }
  }
}
