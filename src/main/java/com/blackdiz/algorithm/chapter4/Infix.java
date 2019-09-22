package com.blackdiz.algorithm.chapter4;

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
    System.out.print("");
  }
}

/**
 * infix to postfix conversion.
 */
class InToPost {

  private StackX stack;
  private String input;
  private StringBuilder output = new StringBuilder();

  public InToPost(String input) {
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
        case '*':
        case '/':
          gotOperator(ch);
          break;
        case '(':
          stack.push(ch);
          break;
        case ')':
          gotParenthesis(ch);
          break;
        default:
          output.append(ch);
          break;
      }

      while (!stack.isEmpty()) {
        stack.display("While ");
        output.append(stack.pop());
      }
    }

    stack.display("End ");
    return output.toString();
  }

  public void gotOperator(char opThis) {
    while (!stack.isEmpty()) {
      char opTop = stack.pop();
      if (opTop == '(') {
        stack.push(opTop);
        break;
      } else {
        if (opTop == '+' || opTop == '-') {
          stack.push(opTop);
          break;
        } else {
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