public class SortedList {

  private LinkL first;

  public SortedList() {
    first = null;
  }

  public void insert(long key) {
    LinkL current = first;
    LinkL previous = null;

    while (current != null && key > current.lData) {
      previous = current;
      current = current.next;
    }

    LinkL newLink = new LinkL(key);
    if (previous == null) {
      // 如果previous == null, 表示上面的while沒有執行到,
      // 如果是因為current為null, 表示list為空
      // 若是因為key < first, 表示應該insert在最前端
      // 不論哪種情況, insert的位置在list開頭, 所以將first指向新的link
      first = newLink;
    } else {
      // 若previous != null, 則表示insert的位置在previous -> new link -> current,
      // 因此將previous.next指向新的link
      previous.next = newLink;
    }

    // 如果list為空, 則current = null, newLink insert在最前端, newLink.next = null
    // 如果list不為空, newLink insert在最前端, current = first, newLink.next = 原先的first,
    // 如果insert在最末端, 則current = null, newLink.next = null
    // insert在中間位置, newLink取代current, 故newLink.next = current
    newLink.next = current;
  }

  public boolean isEmpty() {
    return first == null;
  }

  // 刪除時一律刪除最前端的link, 即是最小值的link
  public LinkL remove() {
    LinkL temp = first;
    first = first.next;

    return first;
  }

  public void displayList() {
    System.out.println("List (first --> Last): ");
    LinkL current = first;
    while (current != null) {
      current.displayLink();
      current = current.next;
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    SortedList list = new SortedList();

    list.insert(20);
    list.insert(40);

    list.displayList();

    list.insert(10);
    list.insert(30);

    list.displayList();

    list.insert(50);

    list.displayList();

    list.remove();

    list.displayList();
  }
}
