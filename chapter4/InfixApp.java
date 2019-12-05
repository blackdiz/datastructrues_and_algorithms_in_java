import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class StackX {

  private char[] stackArray;
  private int top = -1;

  public StackX(int size) {
    stackArray = new char[size];
  }

  public void push(char c) {
    stackArray[++top] = c;
  }

  public char pop() {
    return stackArray[top--];
  }

  public char peek() {
    return stackArray[top];
  }

  /**
   * return item at index n.
   *
   * @param index index
   * @return char at the index
   */
  public char peekN(int index) {
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

/**
 * infix to postfix conversion.
 */
class InfixToPostfix {

  private StackX stack;
  private String input;
  private StringBuilder output = new StringBuilder();

  public InfixToPostfix(String input) {
    this.input = input;
    stack = new StackX(input.length());
  }

  public String doTrans() { // do translation to postfix
    for (int i = 0; i < input.length(); i++) {
      char ch = input.charAt(i);
      stack.display("For " + ch + " "); // diagnostic
      switch (ch) {
        case '+':
        case '-':
          // 遇到+、-時, 開始檢查之前stack中儲存的運算子, 同時+、-的比較優先權設為1
          gotOperator(ch, 1);
          break;
        case '*':
        case '/':
          // 遇到*、/時, 開始檢查之前stack中儲存的運算子, 同時*、/的比較優先權設為2
          gotOperator(ch, 2);
          break;
        case '(':
          stack.push(ch);
          break;
        case ')': // 遇到)時, 表示()內的運算式已經讀取完了, 因此開始pop之前存放在stack中的運算子, 直到遇到stack中的)為止
          gotParenthesis(ch);
          break;
        default:
          output.append(ch);
          break;
      }
      System.out.println("Current ouput is: " + output.toString());
    }

    // 如果已讀到整個Infix運算式的最後, 則把stack中剩餘的運算子都按順序pop出來
    while (!stack.isEmpty()) {
      stack.display("While ");
      output.append(stack.pop());
    }

    stack.display("End ");
    return output.toString();
  }

  public void gotOperator(char opThis, int thisPrecedence) {
    while (!stack.isEmpty()) {
      char opTop = stack.pop();
      if (opTop == '(') {
        // 如果stack前一個儲存的是(, 表示()的運算式尚未結束, 因此直接儲存這次讀取的運算子
        stack.push(opTop);
        break;
      } else {
        int topPrecedence;
        if (opTop == '+' || opTop == '-') {
          topPrecedence = 1;
        } else {
          topPrecedence = 2;
        }

        // 如果目前stack中最上面的運算子小於此次讀取的運算子優先權, 則回存剛剛pop出來的到stack中,
        // 等待下次讀入的運算子再決定是否可以pop
        if (topPrecedence < thisPrecedence) {
          stack.push(opTop);
          break;
        } else {
          // 反之, 則表示前一個儲存的運算子可以套用, 所以pop出來
          output.append(opTop);
        }
      }
    }
    stack.push(opThis);
  }

  public void gotParenthesis(char ch) {
    while (!stack.isEmpty()) {
      char c = stack.pop();
      if (c == '(') { // 如果取出的是 "(", 表示括號內的運算子已經全部填到output了
        break;
      } else {
        output.append(c);
      }
    }
  }
}

public class InfixApp {

  public static void main(String args[]) throws IOException {
    String input, output;
    try (InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr)) {
      while (true) {
        System.out.print("Enter infix: ");
        System.out.flush();
        input = br.readLine();
        if ("".equals(input)) {
          break;
        }
        InfixToPostfix theTrans = new InfixToPostfix(input);
        output = theTrans.doTrans();
        System.out.println("Postfix is " + output + "\n");
      }
    }
  }
}