package chapter8.redblacktree;

class Node {

  public int key;
  public double value;
  private boolean isRed = true; // 預設新建的 node 都是紅色
  public Node parent;
  public Node leftChild;
  public Node rightChild;

  public Node(int key, double value) {
    this.key = key;
    this.value = value;
  }

  public boolean isRed() {
    return isRed;
  }

  public void changeColor() {
    isRed = !isRed;
  }

  public void display() {
    System.out.print("{" + key + ", " + value + ", color: " + (isRed ? "red" : "black"));
  }
}

public class RedBlackTree {

  private Node root;

  public void insert(int key, double value) {
    Node newNode = new Node(key, value);

    if (root == null) {
      newNode.changeColor(); // root 永遠為黑色
      root = newNode;
      newNode.parent = root;
    } else {
      Node current, parent;
      current = root;
      parent = root;

      while (current != null) {
        if (!current.isRed() &&
            current.leftChild != null && current.leftChild.isRed() &&
            current.rightChild != null && current.rightChild.isRed()) {
          flipColor(current);
        }

        if (current.leftChild != null && current.leftChild.isRed()) {
          Node grandParent = current;
          if (parent.leftChild != null && parent.leftChild.isRed()) {
            grandParent.changeColor();
            parent.changeColor();
            rotate(grandParent);
          }
        }

        if (current.key < key) {
          current = current.leftChild;
          if (current == null) {
            newNode = parent;
            parent.leftChild = newNode;
          }
        } else {
          current = current.rightChild;
          if (current == null) {
            newNode = parent;
            parent.leftChild = newNode;
          }
        }
      }


    }
  }

  private void flipColor(Node rootNode) {
    if (rootNode != root) {
      rootNode.changeColor();
    }
    rootNode.leftChild.changeColor();
    rootNode.rightChild.changeColor();
  }
}
