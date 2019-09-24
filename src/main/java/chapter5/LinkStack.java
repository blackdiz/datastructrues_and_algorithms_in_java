package chapter5;

public class LinkStack {

  private LinkList stackList;

  public LinkStack() {
    stackList = new LinkList();
  }

  public void push(long lData) {
    stackList.insertFirst(lData);
  }

  public long pop() {
    return stackList.deleteFirst();
  }

  public boolean isEmpty() {
    return stackList.isEmpty();
  }

  public void displayStack() {
    System.out.println("Stack (top --> down): ");
    stackList.displayList();
  }

  public static void main(String[] args) {
    LinkStack stack = new LinkStack(); // 我們都是針對LinkStack操作, 而不在乎stack是用linked list實作的

    stack.push(20);
    stack.push(40);

    stack.displayStack();

    stack.push(60);
    stack.push(80);

    stack.displayStack();

    stack.pop();
    stack.pop();

    stack.displayStack();
  }

  class LinkList {

    private LinkL first;

    public LinkList() {
      first = null;
    }

    public boolean isEmpty() {
      return first == null;
    }

    public void insertFirst(long lData) {
      LinkL newLink = new LinkL(lData);
      newLink.next = first;
      first = newLink;
    }

    public long deleteFirst() {
      long temp = first.lData;
      first = first.next;

      return temp;
    }

    public void displayList() {
      LinkL current = first;
      while (current != null) {
        current.displayLink();
        current = current.next;
      }

      System.out.println("");
    }
  }
}
