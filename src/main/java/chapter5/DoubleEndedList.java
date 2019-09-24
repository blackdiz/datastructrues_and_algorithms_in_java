package chapter5;

// Link only accept long
class LinkL {

  public long lData;
  public LinkL next;

  public LinkL(long lData) {
    this.lData = lData;
  }

  public void displayLink() {
    System.out.print(lData + " ");
  }
}

public class DoubleEndedList {

  public LinkL first;
  public LinkL last;

  public DoubleEndedList() {
    first = null;
    last = null;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void insertFirst(long lData) {
    LinkL newLink = new LinkL(lData);
    if (isEmpty()) {
      // 如果是空的list, 那最末端和最前端的link會是同一個
      last = newLink;
    }
    newLink.next = first;
    first = newLink;
  }

  public void insertLast(long lData) {
    LinkL newLink = new LinkL(lData);
    if (isEmpty()) {
      // 如果是空的list, 那最末端和最前端的link會是同一個
      first = newLink;
    } else {
      // 將目前最末端的link.next指向新的link
      last.next = newLink;
    }
    last = newLink;
  }

  public long deleteFist() {
    long temp = first.lData;
    if (first.next == null) {
      // 如果list內只有一個link時, 在刪除後last也必須指向null
      last = null;
    }
    first = first.next;

    return temp;
  }

  public long deleteLast() {
    long temp;
    if (first == last) {
      temp = last.lData;
      first = null;
      last = null;
    } else {
      LinkL current = first;
      LinkL previous = first;

      while (current != null) {
        if (current.next != null) {
          previous = current;
          current = current.next;
        } else {
          break;
        }
      }

      temp = last.lData;
      previous.next = null;
      last = previous;
    }

    return temp;
  }

  public void displayList() {
    System.out.println("List (first --> last): ");

    LinkL current = first;
    while (current != null) {
      current.displayLink();
      current = current.next;
    }

    System.out.println("");
  }

  public static void main(String[] args) {
    DoubleEndedList list = new DoubleEndedList();

    list.insertFirst(22);
    list.insertFirst(44);
    list.insertFirst(66);

    list.insertLast(11);
    list.insertLast(33);
    list.insertLast(55);

    list.displayList();

    list.deleteFist();
    list.deleteFist();

    list.displayList();

    list.deleteLast();

    list.displayList();
  }
}
