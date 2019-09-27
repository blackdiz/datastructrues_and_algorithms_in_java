package chapter5.iterator;

class Link {

  public long lData;
  public Link next;

  public Link(long lData) {
    this.lData = lData;
  }

  public void displayLink() {
    System.out.print(lData + " ");
  }
}

class LinkList {

  private Link first;

  public LinkList() {
    this.first = null;
  }

  public Link getFirst() {
    return first;
  }

  public void setFirst(Link first) {
    this.first = first;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public ListIterator getIterator() {
    return new ListIterator(this);
  }

  public void displayList() {
    Link current = first;

    while (current != null) {
      current.displayLink();
      current = current.next;
    }

    System.out.println("");
  }
}

public class ListIterator {

  private Link current; // 目前指向的link
  private Link previous; // 目前指向link的前一個link
  private LinkList list; // 此iterator對應的list

  public ListIterator(LinkList list) {
    this.list = list;
    reset();
  }

  // 將iterator目前指向的link重置到list的first
  public void reset() {
    current = list.getFirst();
    previous = null;
  }

  public boolean atEnd() {
    return current.next == null;
  }

  public void nextLink() {
    previous = current;
    current = current.next;
  }

  public Link getCurrent() {
    return current;
  }

  // 將新link插入到current之後
  public void insertAfter(long lData) {
    Link newLink = new Link(lData);
    if (list.isEmpty()) {
      // 如果目前為空list, 則插入後current和list.first就指向新link
      current = newLink;
      list.setFirst(newLink);
    } else {
      newLink.next = current.next;
      current.next = newLink;
      nextLink(); // 插入後將current指向新的link
    }
  }

  // 將新link插入到current之前
  public void insertBefore(long lData) {
    Link newLink = new Link(lData);
    if (previous == null) { // 表示為空list或目前current在list最前端
      newLink.next = list.getFirst(); // 新link插入在舊的first之前, 所以其next為舊的first
      list.setFirst(newLink);
    } else {
      previous.next = newLink;
      newLink.next = current;
      current = newLink; // 插入後將current指向新的link
    }
  }

  public long deleteCurrent() {
    if (list.isEmpty()) {
      throw new IllegalStateException("List is empty.");
    }

    long value = current.lData;

    if (previous == null) { // 當被刪除的link是目前list最前端的時候
      list.setFirst(current.next); // 重設list.first為下一個link
      reset();
    } else {
      previous.next = current.next;
    }

    return value;
  }
}