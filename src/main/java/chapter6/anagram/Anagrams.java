package chapter6.anagram;

public class Anagrams {
  static int size;
  static int count;
  static char[] charArr;

  public static void doAnagram(int newSize) {
    if (newSize == 1) { // 如果要重新排序的字母數只剩一個就不需要重排, 因此直接return
      return;
    }

    // 需要重排的次數和字母數相等, 比方cat有3個字就需要重排3次
    // 每次迴圈內的code執行時都會等到下一層的遞迴結束後重排, 比方這次重排的是cat, 那下一層重排的會是at
    // 等到at重排過後會回到這層, 接著交換成atc再進行下一層ta的重排
    for (int i = 0; i < newSize; i++) {
      // 遞迴時將首字以外的字再重排, 比方cat就會再把at重排, 那次就會重排2次
      doAnagram(newSize - 1);
      // 如果此次重排的字數只有2個, 表示此為其中一種可能的組合, 因為在固定住其他字母時, 2個字為最小可能的變化
      if (newSize == 2) {
        displayWord();
      }

      // 交換此次重排的字母, 比方at就會交換為ta
      rotate(newSize);
    }
  }

  public static void rotate(int newSize) {
    int position = size - newSize;
    char temp = charArr[position];

    int i;
    for (i = position + 1; i < size; i++) {
      charArr[i - 1] = charArr[i];
    }
    charArr[i - 1] = temp;
  }

  public static void displayWord() {
    System.out.print(++count + " ");
    for (int i = 0; i < size; i++) {
      System.out.print(charArr[i]);
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    String input = "cats";
    charArr = input.toCharArray();
    size = input.length();
    doAnagram(input.length());
  }
}
