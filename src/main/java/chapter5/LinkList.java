package chapter5;

class Link {

  public int iData;
  public int dData;
  public Link next;

  public Link(int iData, int dData) {
    this.iData = iData;
    this.dData = dData;
  }

  public void displayLink() {
    System.out.print("{" + iData + ", " + dData + "}");
  }
}

public class LinkList {

}
