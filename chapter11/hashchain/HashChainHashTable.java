class Link {

  private int iData;
  public Link next;

  public Link(int data) {
    iData = data;
  }

  public int getKey() {
    return iData;
  }

  public void displayLink() {
    System.out.print(iData + " ");
  }
}

class SortedList {

  private Link first;

  public SortedList() {
    first = null;
  }

  public void insert(Link newLink) {
    Link previous = null;
    Link current = first;
    // 當 current == null 時, 表示已到 linked list 的末端
    while (current != null && current.getKey() > newLink.getKey()) {
      previous = current;
      current = current.next;
    }

    if (previous == null) { // previous == null 目前 list 為空, 新 link 即為第 1 個 link
      first = newLink;
    } else {
      previous.next = newLink;
    }
    newLink.next = current;
  }

  public void delete(int key) {
    Link previous = null;
    Link current = first;
    while (current != null) {
      if (current.getKey() == key) {
        if (previous == null) { // previous == null 表示是第 1 個 link, 刪除時將 first 指到下一個 link
          first = first.next;
        } else {
          previous.next = current.next;
        }
      } else {
        // 目前指向的 link 不是欲刪除的資料, 移動到下一個 link
        previous = current;
        current = current.next;
      }
    }
  }

  public Link find(int key) {
    Link current = first;
    while (current != null) {
      if (current.getKey() == key) {
        return current;
      }

      current = current.next;
    }

    return null;
  }

  public void displayList() {
    System.out.print("List (first --> last): ");
    Link current = first;
    while (current != null) {
      current.displayLink();
      current = current.next;
    }
    System.out.println("");
  }
}

public class HashChainHashTable {

  private SortedList[] hashArray;
  private int arraySize;

  public HashChainHashTable(int size) {
    arraySize = size;
    hashArray = new SortedList[arraySize];
    for (int i = 0; i < arraySize; i++) {
      hashArray[i] = new SortedList();
    }
  }

  public void displayTable() {
    for (int i = 0; i < arraySize; i++) {
      System.out.print(i + ". ");
      hashArray[i].displayList();
    }
  }

  public void insert(Link newLink) {
    int hashValue = hash(newLink.getKey());
    hashArray[hashValue].insert(newLink);
  }

  public void delete(int key) {
    int hashValue = hash(key);
    hashArray[hashValue].delete(key);
  }

  public Link find(int key) {
    int hashValue = hash(key);
    return hashArray[hashValue].find(key);
  }

  private int hash(int key) {
    return key % arraySize;
  }
}
