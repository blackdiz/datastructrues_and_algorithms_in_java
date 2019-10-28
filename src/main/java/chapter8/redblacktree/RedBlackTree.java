package chapter8.redblacktree;

class Node {

  public int key;
  public double value;
  private boolean isRed = true; // 預設新建的 node 都是紅色

  public void changeColor() {
    isRed = !isRed;
  }

  public void display() {
    System.out.print("{" + key + ", " + value + ", color: " + (isRed ? "red" : "black"));
  }
}

public class RedBlackTree {

  private Node root;
}
