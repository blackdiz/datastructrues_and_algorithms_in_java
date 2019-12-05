class DoublyLink {

  public DoublyLink previous;
  public DoublyLink next;
  public long lData;

  public DoublyLink(long lData) {
    this.lData = lData;
  }

  public void displayLink() {
    System.out.print(lData + " ");
  }
}

public class DoublyLinkedList {

  private DoublyLink first;
  private DoublyLink last;

  public DoublyLinkedList() {
    first = null;
    last = null;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void insertFirst(long lData) {
    DoublyLink newLink = new DoublyLink(lData);
    if (isEmpty()) {
      last = newLink;
    } else {
      first.previous = newLink;
    }
    newLink.next = first;
    first = newLink;
  }

  public void insertLast(long lData) {
    DoublyLink newLink = new DoublyLink(lData);
    if (isEmpty()) {
      first = newLink;
    } else {
      last.next = newLink;
    }
    newLink.previous = last;
    last = newLink;
  }

  public DoublyLink deleteFirst() {
    DoublyLink temp = first;
    if (first.next == null) {
      // 如果list只有一個 link, 刪除後就變成空的list, 因此last也指向null
      last = null;
    } else {
      first.next.previous = null;
    }
    first = first.next;

    return temp;
  }

  public DoublyLink deleteLast() {
    DoublyLink temp = last;
    if (first.next == null) {
      // 如果list只有一個 link, 刪除後就變成空的list, 因此first也指向null
      first = null;
    } else {
      last.previous.next = null;
    }
    last = last.previous;

    return temp;
  }

  public boolean insertAfter(long key, long lData) {
    DoublyLink current = first;
    while (current.lData != key) {
      current = current.next;
      if (current == null) { // 找不到含有指定的key的link
        return false;
      }
    }

    DoublyLink newLink = new DoublyLink(lData);
    if (current == last) {
      // 如果是插入到last之後, 則新增的link就變成last
      last = newLink;
    } else {
      // 只有當不是插入到last之後時, 才需要處理下一個link的previous和新link的next
      current.next.previous = newLink;
      newLink.next = current.next;
    }

    newLink.previous = current;
    current.next = newLink;

    return true;
  }

  public DoublyLink deleteKey(long key) {
    DoublyLink current = first;
    while (current.lData != key) {
      current = current.next;
      if (current == null) {
        return null;
      }
    }

    if (current == first) {
      // 如果刪除的是第一個link, first要指向刪除link的下個link
      first = current.next;
    } else {
      // 如果不是第一個, 表示被刪除的link一定有前一個link, 因此把前一個link的next
      // 指向被刪除link的next
      current.previous.next = current.next;
    }
    if (current == last) {
      // 如果刪除是的最後一個link, last要指向刪除link的前一個link
      last = current.previous;
    } else {
      // 如果不是最後一個, 表示被刪除的link一定有下一個link, 因此把下一個link的previous
      // 指向被刪除link的previous
      current.next.previous = current.previous;
    }

    return current;
  }

  public void displayForward() {
    System.out.println("List (first --> last): ");

    DoublyLink current = first;
    while (current != null) {
      current.displayLink();
      current = current.next;
    }

    System.out.println("");
  }

  public void displayBackward() {
    System.out.println("List (last --> first): ");

    DoublyLink current = last;
    while (current != null) {
      current.displayLink();
      current = current.previous;
    }

    System.out.println("");
  }

  public static void main(String[] args) {
    DoublyLinkedList list = new DoublyLinkedList();

    list.insertFirst(22);
    list.insertFirst(44);
    list.insertFirst(66);

    list.insertLast(11);
    list.insertLast(33);
    list.insertLast(55);

    list.displayForward();
    list.displayBackward();

    list.deleteFirst();
    list.deleteLast();
    list.deleteKey(11);

    list.displayForward();

    list.insertAfter(22, 77);
    list.insertAfter(33, 88);

    list.displayForward();
  }
}
