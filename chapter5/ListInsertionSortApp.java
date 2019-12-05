class LinkX {

  public long lData;
  public LinkX next;

  public LinkX(long lData) {
    this.lData = lData;
  }
}

class SortedListX {

  private LinkX first;

  public SortedListX() {
    first = null;
  }

  public SortedListX(LinkX[] linkArr) {
    first = null;
    for (int i = 0; i < linkArr.length; i++) {
      insert(linkArr[i]);
    }
  }

  public void insert(LinkX newLink) {
    LinkX current = first;
    LinkX previous = null;
    while (current != null && newLink.lData > current.lData) {
      previous = current;
      current = current.next;
    }

    if (previous == null) {
      first = newLink;
    } else {
      previous.next = newLink;
    }
    newLink.next = current;
  }

  public LinkX remove() {
    LinkX temp = first;
    first = first.next;

    return temp;
  }
}

public class ListInsertionSortApp {

  public static void main(String[] args) {
    int size = 10;
    LinkX[] linkArray = new LinkX[size];
    for (int i = 0; i < linkArray.length; i++) {
      int n = (int) (Math.random() * 99);
      LinkX newLink = new LinkX(n);
      linkArray[i] = newLink;
    }

    System.out.print("Unsorted array: ");
    for (int i = 0; i < linkArray.length; i++) {
      System.out.print(linkArray[i].lData + " ");
    }
    System.out.println("");

    SortedListX list = new SortedListX(linkArray); // 將 array 藉由 sorted list 排序
    for (int i = 0; i < linkArray.length; i++) {
      // 從 sorted list 取出放回 array
      linkArray[i] = list.remove();
    }

    System.out.print("Sorted array: ");
    for (int i = 0; i < linkArray.length; i++) {
      System.out.print(linkArray[i].lData + " ");
    }
    System.out.println("");
  }
}
