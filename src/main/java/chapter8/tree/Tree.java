package chapter8.tree;

import java.util.Stack;

class Node {

  public int iData;
  public Node rightChild;
  public Node leftChild;

  public void displayNode() {
    System.out.print("{" + iData + "}");
  }
}

public class Tree {

  private Node root;

  public Tree() {
    root = null;
  }

  public Node find(int key) {
    Node current = root;

    while (current.iData != key) {
      if (current.iData < key) {
        // 如果比 key 小, 表示目標是在右半邊, 因此往右移動
        current = current.rightChild;
      } else {
        // 如果大於或等於 key, 表示目標在左半邊, 往左移動
        current = current.leftChild;
      }

      if (current == null) {
        return null;
      }
    }

    return current;
  }

  public void insert(int data) {
    Node newNode = new Node();
    newNode.iData = data;

    // 當 root 為 null 時，表示 tree 中沒有任何 node
    // 因此新 node 為第一個 node 也就是 root
    if (root == null) {
      root = newNode;
    } else {
      Node current, parent;
      current = root;

      while (current != null) {
        parent = current; // 記錄目前 visit 的 node

        if (current.iData < data) {
          // 如果目前node比data小, 表示要插入在右邊, 因此往右移動
          current = current.rightChild;
          if (current == null) {
            // 如果 leftChild == null
            // 則把新的 node 掛在上一層 parent 的 leftChild
            parent.rightChild = newNode;
          }
        } else {
          // 如果目前node比data大, 表示要插入在左邊, 因此往左移動
          current = current.leftChild;
          if (current == null) {
            // 如果 rightChild == null
            // 則把新的 node 掛在上一層 parent 的 rightChild
            parent.leftChild = newNode;
          }
        }
      }
    }
  }

  public boolean delete(int key) {
    Node current = root;
    Node parent = root;
    boolean isLeftChild = true;

    while (current.iData != key) {
      parent = current;
      if (key < current.iData) {
        isLeftChild = true;
        current = current.leftChild;
      } else {
        isLeftChild = false;
        current = current.rightChild;
      }

      if (current == null) {
        return false;
      }
    }

    if (current.leftChild == null && current.rightChild == null) { // 如果被刪除的 node 沒有 child
      if (current == root) {
        root = null;
      } else if (isLeftChild) {
        parent.leftChild = null;
      } else {
        parent.rightChild = null;
      }
    } else if (current.rightChild == null) { // 如果被刪除的 node 只有 left child
      if (current == root) {
        // 如果是 root, 則用 left child 當成新的 root
        root = current.leftChild;
      } else if (isLeftChild) {
        // 不是 root 且 node 是 parent 的 left child, 將 left child 接到 parent 的 left child
        parent.leftChild = current.leftChild;
      } else {
        // 不是 root 且 node 是 parent 的 right child, 將 right child 接到 parent 的 right child
        parent.rightChild = current.leftChild;
      }
    } else if (current.leftChild == null) { // 如果被刪除的 node 只有 right child
      if (current == root) {
        // 如果是 root, 則用 right child 當成新的 root
        root = current.rightChild;
      } else if (isLeftChild) {
        // 不是 root 且 node 是 parent 的 left child, 將 right child 接到 parent 的 left child
        parent.leftChild = current.rightChild;
      } else {
        // 不是 root 且 node 是 parent 的 right child, 將 right child 接到 parent 的 right child
        parent.rightChild = current.rightChild;
      }
    } else { // 如果被刪除的 node 有 2 個 child
      Node successor = getSuccessor(current);

      if (current == root) {
        root = successor;
      } else if (isLeftChild) {
        parent.leftChild = successor;
      } else {
        parent.rightChild = successor;
      }

      successor.leftChild = current.leftChild;
    }

    return true;
  }

  private Node getSuccessor(Node deletedNode) {
    Node successorParent = deletedNode;
    Node successor = deletedNode;

    // 先往被刪除 node 的 right child 移動
    Node current = deletedNode.rightChild;

    // 沿著 left child 移動, 直到碰到 null
    while (current != null) {
      successorParent = successor;
      successor = current;
      current = current.leftChild;
    }

    // 如果 successor 不是被刪除 node 的 right child,
    if (successor != deletedNode.rightChild) {
      // 把 successor 的 right child 接到它 parent 的 left child 上, 因為 successor 要取代被刪除的 node
      successorParent.leftChild = successor.rightChild;
      // 將被刪除 node 的 right child 接到 successor 的 right child 上, 因為 successor 要取代被刪除的 node
      successor.rightChild = deletedNode.rightChild;
    }

    return successor;
  }

  public void traverse() {
    visitNode(root);
  }

  private void visitNode(Node current) {
    if (current == null) {
      return;
    }

    visitNode(current.leftChild);
    current.displayNode();
    visitNode(current.rightChild);
  }

  public void displayTree() {
    Stack<Node> globalStack = new Stack<Node>();
    globalStack.push(root);
    int nBlanks = 32;
    boolean isRowEmpty = false;
    System.out.println(".......................................");
    while (!isRowEmpty) {
      Stack<Node> localStack = new Stack<Node>();
      isRowEmpty = true;
      for (int i = 0; i < nBlanks; i++) {
        System.out.print(" ");
      }

      while (!globalStack.isEmpty()) {
        Node temp = globalStack.pop();
        if (temp != null) {
          System.out.print(temp.iData);
          localStack.push(temp.leftChild);
          localStack.push(temp.rightChild);

          if (temp.leftChild != null || temp.rightChild != null) {
            isRowEmpty = false;
          }
        } else {
          System.out.print("--");
          localStack.push(null);
          localStack.push(null);
        }

        for (int i = 0; i < nBlanks * 2 - 2; i++) {
          System.out.print(" ");
        }
      }

      System.out.println();
      nBlanks /= 2;

      while (!localStack.isEmpty()) {
        globalStack.push(localStack.pop());
      }
    }

    System.out.println(".......................................");
  }
}
