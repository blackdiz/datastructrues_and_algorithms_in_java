package chapter4;

/**
 * 這個 priority queue 的實作是由小到大排序
 *
 * @author blackdiz
 */
public class PriorityQueue {

  private int maxSize;
  private int[] queue;
  private int itemNums = 0;

  public PriorityQueue(int max) {
    maxSize = max;
    queue = new int[max];
  }

  public void insert(int item) {
    if (isFull()) {
      System.out.println("Queue is full.");
      return;
    }
    if (itemNums == 0) { // 如果目前queue內沒有元素則可以直接插入
      queue[itemNums++] = item;
    } else {
      int i;
      for (i = itemNums - 1; i >= 0; i--) { // 由後方開始比對
        int current = queue[i];
        // 如果新元素比目前這index的元素大, 則將目前index的元素往後移, 直到新元素比index的元素小或相同為止
        if (item > current) {
          queue[i + 1] = current;
        } else {
          break;
        }
      }
      queue[i + 1] = item; // 如果新元素比目前所指到index的元素小或相同就放在index的右邊
      itemNums++;
    }
  }


  public int remove() {
    return queue[--itemNums];
  }

  public boolean isFull() {
    return itemNums == maxSize;
  }

  public boolean isEmpty() {
    return itemNums == 0;
  }

  public void display() {
    System.out.print("Current content of the queue: ");
    for (int i = 0; i < queue.length; i++) {
      System.out.print(queue[i] + ",");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    PriorityQueue q = new PriorityQueue(5);
    q.insert(10);
    q.display();
    q.insert(8);
    q.display();
    q.insert(9);
    q.display();
    q.insert(6);
    q.display();
    q.insert(7);
    q.display();

    System.out.println("Get: " + q.remove());
    q.display();
    System.out.println("Get: " + q.remove());
    q.display();
    System.out.println("Get: " + q.remove());
    q.display();
    System.out.println("Get: " + q.remove());
    q.display();
    System.out.println("Get: " + q.remove());
    q.display();
  }
}
