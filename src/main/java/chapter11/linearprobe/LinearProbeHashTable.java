package chapter11.linearprobe;

class DataItem {

  private int iData;

  public DataItem(int data) {
    iData = data;
  }

  public int getKey() {
    return iData;
  }
}

public class LinearProbeHashTable {

  private DataItem[] hashArray;
  private int arraySize;
  private DataItem nonItem; // 用來標記已刪除的資料

  public LinearProbeHashTable(int size) {
    arraySize = size;
    hashArray = new DataItem[arraySize];
    nonItem = new DataItem(-1); // 已刪除資料的 key 為 -1
  }

  public void displayTable() {
    System.out.print("Table: ");
    for (int i = 0; i < arraySize; i++) {
      if (hashArray[i] != null) {
        System.out.print(hashArray[i].getKey() + " ");
      } else {
        System.out.print("** ");
      }
    }
    System.out.println("");
  }

  public int hash(int key) {
    return key % arraySize;
  }

  public void insert(DataItem item) {
    int key = item.getKey();
    int hashValue = hash(key);

    while (hashArray[hashValue] != null &&
        hashArray[hashValue].getKey() != -1) { // -1 表示為已被刪除的資料可略過
      ++hashValue;
      hashValue %= arraySize; // 如果已到末端則回到前端繼續搜尋可插入資料的格子
    }

    hashArray[hashValue] = item;
  }

  public DataItem delete(int key) {
    int hashValue = hash(key);

    while (hashArray[hashValue] != null) { // 遇到沒資料的空格表示欲刪除的資料不存在
      if (hashArray[hashValue].getKey() == key) { // 檢查此格資料的 key 是否為目標資料的 key
        DataItem temp = hashArray[hashValue];
        hashArray[hashValue] = nonItem; // 用 -1 的資料標記此格資料已被刪除

        return temp;
      }
      ++hashValue;
      hashValue %= arraySize; // 如果已到末端則回到前端繼續搜尋欲刪除的資料
    }

    return null;
  }
}
