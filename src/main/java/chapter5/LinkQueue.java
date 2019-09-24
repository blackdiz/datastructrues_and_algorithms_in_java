package chapter5;

public class LinkQueue {

  private DoubleEndedList queueList;

  public LinkQueue() {
    queueList = new DoubleEndedList();
  }

  public void insert(long lData) {
    // queue的insert是按新增順序排序, 和double-ended list的insertLast相同
    queueList.insertLast(lData);
  }

  public long remove() {
    // 因為insert時是愈晚insert的在list愈後面, 所以remove時就從前面開始remove, 符合FIFO
    return queueList.deleteFirst();
  }

  public void displayQueue() {
    System.out.println("Queue (front --> rear): ");
    queueList.displayList();
  }

  public static void main(String[] args) {
    LinkQueue queue = new LinkQueue();

    queue.insert(20);
    queue.insert(40);

    queue.displayQueue();

    queue.insert(60);
    queue.insert(80);

    queue.displayQueue();

    queue.remove();
    queue.remove();

    queue.displayQueue();
  }

  class DoubleEndedList {

    private LinkL first;
    private LinkL last;

    public DoubleEndedList() {
      first = null;
      last = null;
    }

    public boolean isEmpty() {
      return first == null;
    }

    public void insertLast(long lData) {
      LinkL newLink = new LinkL(lData);

      if (isEmpty()) {
        // 如果list是空的, 則first也指向新的link
        first = newLink;
      } else {
        // 反之, 則須讓目前last.next指向新的link, 讓兩者鏈結
        last.next = newLink;
      }

      last = newLink;
    }

    public long deleteFirst() {
      LinkL temp = first;
      if (first.next == null) {
        // 如果list目前只有一個link, 移除後就變成空的list, 所以要讓last也指向null
        last = null;
      }
      first = first.next;

      return temp.lData;
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
