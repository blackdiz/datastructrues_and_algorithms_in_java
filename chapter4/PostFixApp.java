import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class IntStackX {

  private int[] stackArray;
  private int top = -1;

  public IntStackX(int size) {
    stackArray = new int[size];
  }

  public void push(int c) {
    stackArray[++top] = c;
  }

  public int pop() {
    return stackArray[top--];
  }

  public int peek() {
    return stackArray[top];
  }

  /**
   * return item at index n.
   *
   * @param index index
   * @return char at the index
   */
  public int peekN(int index) {
    return stackArray[index];
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public boolean isFull() {
    return (top + 1) == stackArray.length;
  }

  public int size() {
    return top + 1;
  }

  public void display(String s) {
    System.out.print(s);
    System.out.print("Stack(bottom -> top): ");
    for (int j = 0; j < size(); j++) {
      System.out.print(peekN(j));
      System.out.print(" ");
    }
    System.out.println("");
  }
}

class ParsePost {

  private IntStackX stack;
  private String input;

  public ParsePost(String postfix) {
    input = postfix;
  }

  public int doParse() {
    stack = new IntStackX(20);
    char ch;
    int i;
    int num1, num2, ans;
    for (i = 0; i < input.length(); i++) {
      ch = input.charAt(i);
      stack.display("" + ch + " ");
      // 如果是數字則放入stack中, 等讀到運算子時再pop出來做運算
      if (ch >= '0' && ch <= '9') {
        stack.push((int) (ch - '0'));
      } else {
        // 將運算子前面的2個運算元pop出來做運算
        num2 = stack.pop();
        num1 = stack.pop();
        switch (ch) {
          case '+':
            ans = num1 + num2;
            break;
          case '-':
            ans = num1 - num2;
            break;
          case '*':
            ans = num1 * num2;
            break;
          case '/':
            ans = num1 / num2;
            break;
          default:
            ans = 0;
            break;
        }
        System.out.println("Current answer is: " + ans);
        stack.push(ans);
      }
    }

    ans = stack.pop(); // 全部解析完後, stack中最後的數字即為最後答案

    return ans;
  }
}

public class PostFixApp {

  public static void main(String[] args) throws IOException {
    String input;
    int output;
    try (InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr)) {
      while (true) {
        System.out.print("Enter postfix: ");
        System.out.flush();
        input = br.readLine();
        if ("".equals(input)) {
          break;
        } else {
          ParsePost parser = new ParsePost(input);
          output = parser.doParse();
          System.out.println("Evaluates to " + output);
        }
      }
    }
  }
}
