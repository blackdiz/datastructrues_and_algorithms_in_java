import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Params {

  public int n;
  public int returnAddress;

  public Params(int n, int returnAddress) {
    this.n = n;
    this.returnAddress = returnAddress;
  }
}

class StackX {

  private int maxSize;
  private Params[] stackArray;
  private int top;

  public StackX(int size) {
    maxSize = size;
    stackArray = new Params[maxSize];
    top = -1;
  }

  public void push(Params params) {
    stackArray[++top] = params;
  }

  public Params pop() {
    return stackArray[top--];
  }

  public Params peek() {
    return stackArray[top];
  }
}

public class StackTriangleApp {

  static int number;
  static int answer;
  static StackX stack;
  static int codePart;
  static Params params;

  public static void main(String[] args) throws IOException {
    System.out.print("Enter a number: ");
    try (InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr)) {
      String input = br.readLine();
      number = Integer.parseInt(input);
    }
    recTriangle();
    System.out.println("Triangle=" + answer);
  }

  public static void recTriangle() {
    stack = new StackX(10000);
    codePart = 1;
    while (step() == false) {

    }
  }

  public static boolean step() {
    switch (codePart) {
      case 1: // 開始啟動流程
        params = new Params(number, 6);
        stack.push(params);
        codePart = 2;
        break;
      case 2: // 檢查是否stack最上方的參數n=1, 如果n=1表示不用再產生新參數, 可以開始回call stack內儲存的步驟
        params = stack.peek();
        // 當達到n=1時, 就不用計算直接return, return等於是呼叫第5步
        if (params.n == 1) {
          answer = 1;
          codePart = 5;
        } else {
          // 尚未到n=1時，需要再遞迴呼叫n-1, 執行第3步
          codePart = 3;
        }
        break;
      case 3: // 將目前stack最上方的n-1後產生下次執行時的新參數放入stack中, 並設定該次執行後便執行第4步
        Params newParams = new Params(params.n - 1, 4);
        stack.push(newParams);
        // 回到第2步檢查是否已到n=1
        codePart = 2;
        break;
      case 4: // 開始return遞迴, 將該參數n加上加總數後, 回到第5步檢查stack中下一個參數
        params = stack.peek();
        answer = answer + params.n;
        codePart = 5;
        break;
      case 5: // 結束遞迴呼叫, 取得每個參數指定的回call步驟, 再將該參數從stack移出
        params = stack.peek();
        codePart = params.returnAddress;
        stack.pop();
        break;
      case 6: // 當最後一個參數執行完時, 第1步的參數設定會執行第6步, 此時結束整個流程
        return true;
    }

    return false;
  }
}
