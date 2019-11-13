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

  public Heap(int maxSize) {
    this.maxSize = maxSize;
    heapArray = new Node[maxSize];
    currentSize = 0;
  }

  public boolean isEmpty() {
    return currentSize == 0;
  }

  public boolean insert(int key) {
    if (currentSize == maxSize) {
      return false;
    }

    Node newNode = new Node(key);
    heapArray[currentSize] = newNode;
    trickleUp(currentSize++);

    return true;
  }

  public Node remove() {
    if (currentSize == 0) {
      return null;
    }

    Node root = heapArray[0];
    heapArray[0] = heapArray[--currentSize];
    trickleDown(0);

    return root;
  }

  private void trickleUp(int index) {
    int parentIndex = (index - 1) / 2;
    Node bottom = heapArray[index];

    while (index > 0 && heapArray[parentIndex].getKey() < bottom.getKey()) {
      heapArray[parentIndex] = heapArray[index];
      index = parentIndex;
      parentIndex = (parentIndex - 1) / 2;
    }

    heapArray[index] = bottom;
  }

  private void trickleDown(int index) {
    int largerChild;
    Node top = heapArray[index];

    while (index < currentSize / 2) {
      int leftChild = index * 2 + 1;
      int rightChild = leftChild + 1;
      if (rightChild < currentSize &&
          heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
        largerChild = rightChild;
      } else {
        largerChild = leftChild;
      }
      if (top.getKey() >= heapArray[largerChild].getKey()) {
        break;
      }
      heapArray[index] = heapArray[largerChild];
      index = largerChild;
    }

    heapArray[index] = top;
  }
}
