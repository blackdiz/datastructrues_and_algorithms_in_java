package chapter12.heap;

class Node {

  private int key;

  public Node(int key) {
    this.key = key;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }
}

public class Heap {

  private Node[] heapArray;
  private int maxSize;
  private int currentSize;

  public Heap(int size) {
    heapArray = new Node[size];
    currentSize = 0;
  }

  public boolean isEmpty() {
    return currentSize == 0;
  }

  public boolean insert(int data) {
    if (currentSize == maxSize) {
      return false;
    }
    Node newNode = new Node(data);
    heapArray[currentSize++] = newNode;
    tackleUp(currentSize);

    return true;
  }

  public Node remove() {
    if (currentSize == 0) {
      return null;
    }
    Node root = heapArray[0];
    heapArray[0] = heapArray[--currentSize];
    tackleDown(0);

    return root;
  }

  public boolean change(int index, int newValue) {
    if (index < 0 || index >= currentSize) {
      return false;
    }

    int oldValue = heapArray[index].getKey();
    heapArray[index].setKey(newValue);

    if (oldValue < newValue) {
      tackleUp(index);
    } else {
      tackleDown(index);
    }

    return true;
  }

  private void tackleUp(int bottomIndex) {
    Node bottomNode = heapArray[bottomIndex];
    int parentIndex = (bottomIndex - 1) / 2;
    while (bottomIndex > 0 && heapArray[parentIndex].getKey() < bottomNode.getKey()) {
      heapArray[bottomIndex] = heapArray[parentIndex];
      bottomIndex = parentIndex;
      parentIndex = (parentIndex - 1) / 2;
    }
    heapArray[bottomIndex] = bottomNode;
  }

  private void tackleDown(int topIndex) {
    Node top = heapArray[topIndex];
    int largerChildIndex;

    while (topIndex < currentSize / 2) { // 如果目標 node 的 index < currentSize / 2 表示有 left child
      int leftChildIndex = topIndex * 2 + 1;
      int rightChildIndex = leftChildIndex + 1;

      if (rightChildIndex < currentSize &&
          // 計算出來的 right child index > currentSize 表示沒有 right child
          heapArray[rightChildIndex].getKey() > heapArray[leftChildIndex].getKey()) {
        largerChildIndex = rightChildIndex;
      } else {
        largerChildIndex = leftChildIndex;
      }
      // 如果 key 較大的 child 的 key 比目標 node 的 key 大就交換位置
      if (top.getKey() >= heapArray[largerChildIndex].getKey()) {
        heapArray[topIndex] = heapArray[largerChildIndex];
        topIndex = largerChildIndex;
      }
    }

    heapArray[topIndex] = top;
  }
}
