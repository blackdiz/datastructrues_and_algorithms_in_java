package chapter5.iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 執行後在命令列下可輸入以下指令可操作iterator:
 * s - 印出list內容
 * r - 將iterator重置到list最前端
 * n - 指向下一個link
 * g - 取得目前指向link的資料
 * b - 插入新的link到目前link前面
 * a - 插入新的link到目前link後面
 * d - 刪除目前指向的link
 */
public class InteractiveIteratorApp {

  public static void main(String[] args) throws IOException {
    LinkList list = new LinkList();
    ListIterator iterator1 = list.getIterator();

    iterator1.insertAfter(20);
    iterator1.insertAfter(40);
    iterator1.insertAfter(60);
    iterator1.insertAfter(80);

    try (InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr)) {
      while (true) {
        System.out.print("Enter first letter of show, reset, next, get, before, after, delete: ");
        System.out.flush();

        String input = br.readLine();
        char choice = input.length() == 0 ? ' ' : input.charAt(0);
        long value;
        switch (choice) {
          case 's': // 印出list內容
            if (!list.isEmpty()) {
              list.displayList();
            } else {
              System.out.println("List is empty.");
            }
            break;
          case 'r': // 重將iterator重置到list最前端
            iterator1.reset();
            break;
          case 'n': // 指向下一個link
            if (!list.isEmpty()) {
              iterator1.nextLink();
            } else {
              System.out.println("Can't go to next link");
            }
            break;
          case 'g': // 取得目前指向link的資料
            if (!list.isEmpty()) {
              value = iterator1.getCurrent().lData;
              System.out.println("Returned " + value);
            } else {
              System.out.println();
            }
            break;
          case 'b': // 插入新的link到目前link前面
            System.out.print("Enter value to insert: ");
            System.out.flush();
            input = br.readLine();
            value = Integer.parseInt(input);
            iterator1.insertBefore(value);
            break;
          case 'a': // 插入新的link到目前link後面
            System.out.print("Enter value to insert: ");
            System.out.flush();
            input = br.readLine();
            value = Integer.parseInt(input);
            iterator1.insertAfter(value);
            break;
          case 'd': // 刪除目前指向的link
            if (!list.isEmpty()) {
              value = iterator1.deleteCurrent();
              System.out.println("Deleted " + value);
            } else {
              System.out.println("Can't delete");
            }
            break;
          case ' ':
            return;
          default:
            System.out.println("Invalid entry");
        }
      }
    }
  }
}
