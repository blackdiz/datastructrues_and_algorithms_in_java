package chapter5;

public class LinkListWithFind {

  private Link first;

  public LinkListWithFind() {
    first = null;
  }

  public void insertFirst(int iData, double dData) {
    Link newLink = new Link(iData, dData);
    newLink.next = first;
    first = newLink;
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

  public Link find(int key) {
    Link current = first;
    while (current != null) {
      if (key == current.iData) {
        break;
      }
      current = current.next;
    }

    return current;
  }

  public Link delete(int key) {
    Link current = first;
    Link previous = first;
    while (current != null) {
      if (current.iData == key) {
        if (current == first) { // 如果第1個link就找到, 就把first指向找到的link.next
          first = current.next;
        } else {
          previous.next = current.next; // 將前一個link.next指向找到的link.next
        }
        break;
      }
      previous = current;
      current = current.next;
    }

    return current;
  }

  public static void main(String[] args) {
    LinkListWithFind list = new LinkListWithFind();

    list.insertFirst(22, 2.99);
    list.insertFirst(44, 4.99);
    list.insertFirst(66, 6.99);
    list.insertFirst(88, 8.99);

    list.displayList();

    Link f = list.find(44);
    if (f != null) {
      System.out.println("Found link with key " + f.iData);
    } else {
      System.out.println("Can't find link");
    }

    Link d = list.delete(44);
    if (d != null) {
      System.out.println("Deleted link with key " + d.iData);
    } else {
      System.out.println("Can't delete link");
    }

    list.displayList();
  }
}
