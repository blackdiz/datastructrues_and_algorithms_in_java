package chapter10.tree234;

class DataItem {

  public long lData;

  public DataItem(long data) {
    lData = data;
  }

  @Override
  public String toString() {
    return "/" + lData;
  }
}

class Node {

  private static final int ORDER = 4;
  private int numItems;
  private Node parent;
  private Node[] childArray = new Node[ORDER];
  private DataItem[] itemArray = new DataItem[ORDER - 1];

  public void connectChild(int childNum, Node child) {
    childArray[childNum] = child;
    if (child != null) {
      child.parent = this;
    }
  }

  public Node disconnectChild(int childNum) {
    Node tempNode = childArray[childNum];
    childArray[childNum] = null;

    return tempNode;
  }

  public Node getChild(int childNum) {
    return childArray[childNum];
  }

  public Node getParent() {
    return parent;
  }

  public boolean isLeaf() {
    return childArray[0] == null;
  }

  public int getNumItems() {
    return numItems;
  }

  public DataItem getItem(int index) {
    return itemArray[index];
  }

  public boolean isFull() {
    return numItems == (ORDER - 1);
  }

  public int findItemIndex(long key) {
    for (int i = 0; i < ORDER - 1; i++) {
      if (itemArray[i] == null) {
        break;
      } else if (itemArray[i].lData == key) {
        return i;
      }
    }

    return -1;
  }

  public int insertItem(DataItem newItem) {
    numItems++;
    long newKey = newItem.lData;

    // 從 item array 末端開始比對
    for (int i = ORDER - 2; i >= 0; i--) {
      if (itemArray[i] != null) {
        long itsKey = itemArray[i].lData;
        if (newKey < itsKey) {
          // 如果大於新 item 則向右移一格
          itemArray[i + 1] = itemArray[i];
        } else {
          // 如果小於或等於新 item 則新 item 放在向右一格的位置
          itemArray[i + 1] = newItem;

          return i + 1;
        }
      }
    }

    // 執行到此, 表示上面的迴圈結束後，新 item 是最小的
    itemArray[0] = newItem;

    return 0;
  }

  public DataItem removeItem() {
    DataItem temp = itemArray[numItems - 1];
    itemArray[numItems - 1] = null;
    numItems--;

    return temp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numItems; i++) {
      sb.append(itemArray[i]);
    }
    sb.append("/");

    return sb.toString();
  }
}

public class Tree234 {

  private Node root = new Node();

  public int find(long data) {
    Node current = root;
    int childNumber;

    while (true) {
      childNumber = current.findItemIndex(data);
      if (childNumber != -1) {
        return childNumber;
      } else if (current.isLeaf()) {
        return -1;
      } else {
        current = getNextChild(current, data);
      }
    }
  }

  public void insert(long data) {
    // current 為目前 visit 的 node, 由 root 開始
    Node current = root;
    DataItem newItem = new DataItem(data);

    // 由 root 到 leaf 找尋可以放入 item 的 node
    while (true) {
      if (current.isFull()) { // 如果 node 已放滿 item 則 split
        split(current);
        // 因為 split 會產生新的 node, 所以 parent 的 child link 會變化
        // 因此要再回到 parent 再找尋正確的 child link
        current = current.getParent();
        current = getNextChild(current, data);
      } else if (current.isLeaf()) {
        break;
      } else {
        current = getNextChild(current, data);
      }
    }

    current.insertItem(newItem);
  }

  public void split(Node node) {
    DataItem itemB, itemC;

    itemC = node.removeItem();
    itemB = node.removeItem();

    Node parent;
    Node newRight = new Node();

    if (node == root) {
      // 如果被 split 的 node 是 root 則要另外再新建 1 個 node
      // 做為被 split 的 node 的 parent 和新 root
      root = new Node();
      parent = root;
      // 被 split 的 node 變成新 root 的 child
      root.connectChild(0, node);
    } else {
      parent = node.getParent();
    }

    int itemIndex = parent.insertItem(itemB);
    int n = parent.getNumItems();

    // 將新 item 右邊的 item 右邊的 child link 右移一格
    // 以空出位置連結 split 後新建的 node
    for (int i = n - 1; i > itemIndex; i--) {
      Node temp = parent.disconnectChild(i);
      parent.connectChild(i + 1, temp);
    }

    // 將 split 後新建的 node 接到新 item 的右邊
    parent.connectChild(itemIndex + 1, newRight);

    newRight.insertItem(itemC);

    // 將被 split 的 node 最右 2 個 child link 移到新建的 node 上
    Node child2, child3;
    child2 = node.disconnectChild(2);
    child3 = node.disconnectChild(3);

    newRight.connectChild(0, child2);
    newRight.connectChild(1, child3);
  }

  public Node getNextChild(Node node, long data) {
    int i;
    int numItems = node.getNumItems();

    // 找尋位於小於 itemC 之前的 child link
    for (i = 0; i < numItems; i++) {
      if (data < node.getItem(i).lData) {
        return node.getChild(i);
      }
    }

    return node.getChild(i);
  }

  public void displayTree() {
    recDisplayTree(root, 0, 0);
  }

  private void recDisplayTree(Node node, int level, int childNumber) {
    System.out.print("level=" + level + " child=" + childNumber + " ");
    System.out.println(node);
    int numItems = node.getNumItems();
    for (int i = 0; i < numItems + 1; i++) {
      Node nextNode = node.getChild(i);
      if (nextNode != null) {
        recDisplayTree(nextNode, level + 1, i);
      } else {
        return;
      }
    }
  }

  public static void main(String[] args) {
    Tree234 tree = new Tree234();
    tree.insert(50);
    tree.insert(40);
    tree.insert(60);
    tree.insert(30);
    tree.insert(70);

    tree.displayTree();
  }
}
