class Link {

  public int iData;
  public double dData;
  public Link next;

  public Link(int iData, double dData) {
    this.iData = iData;
    this.dData = dData;
  }

  public void displayLink() {
    System.out.print("{" + iData + ", " + dData + "}");
  }
}

public class LinkList {

  private Link first;

  public LinkList() {
    first = null;
  }

  public boolean isEmpty() {
    return first == null;
  }

  public void insertFirst(int iData, double dData) {
    Link newLink = new Link(iData, dData);
    newLink.next = first; // 將下個link的參照指到目前的第一個link
    first = newLink; // 新的link變成第一個link
  }

  public Link deleteFirst() {
    if (isEmpty()) {
      return null;
    }

    Link temp = first;
    // 將第一個link的參照指向目前第一個link.next, 所以目前第一個link就不再能被讀到
    first = first.next;

    return temp;
  }

  public void displayList() {
    System.out.println("List (first --> last): ");
    Link currentLink = first;
    while (currentLink != null) {
      currentLink.displayLink();
      currentLink = currentLink.next;
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    LinkList list = new LinkList();

    list.insertFirst(22, 2.99);
    list.insertFirst(44, 4.99);
    list.insertFirst(66, 6.99);
    list.insertFirst(88, 8.99);

    list.displayList();
    while (!list.isEmpty()) {
      Link deletedLink = list.deleteFirst();
      System.out.print("Deleted ");
      deletedLink.displayLink();
      System.out.println("");
    }
    list.displayList();
  }
}
