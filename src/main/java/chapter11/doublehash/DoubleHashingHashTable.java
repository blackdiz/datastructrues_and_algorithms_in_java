package chapter11.doublehash;

class DataItem {

  private int iData;

  public DataItem(int data) {
    iData = data;
  }

  public int getKey() {
    return iData;
  }

}

public class DoubleHashingHashTable {

  private int arraySize;
  private DataItem[] hashArray;
  private DataItem nonItem;

  public DoubleHashingHashTable(int size) {
    arraySize = size;
    hashArray = new DataItem[arraySize];
    nonItem = new DataItem(-1);
  }

  public void displayTable() {
    System.out.println("Table: ");
    for (int i = 0; i < arraySize; i++) {
      if (hashArray[i] != null) {
        System.out.print(hashArray[i].getKey() + " ");
      } else {
        System.out.print("** ");
      }
      System.out.println("");
    }
  }

  public void insert(DataItem newItem) {
    int hashValue = hash(newItem.getKey());
    int stepSize = hashStepSize(newItem.getKey());

    while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1) {
      hashValue += stepSize;
      hashValue %= arraySize;
    }

    hashArray[hashValue] = newItem;
  }

  public DataItem delete(int key) {
    int hashValue = hash(key);
    int stepSize = hashStepSize(key);

    while (hashArray[hashValue] != null) {
      if (hashArray[hashValue].getKey() == key) {
        DataItem temp = hashArray[hashValue];
        hashArray[hashValue] = nonItem;

        return temp;
      }
      hashValue += stepSize;
      hashValue %= arraySize;
    }

    return null;
  }

  public DataItem find(int key) {
    int hashValue = hash(key);
    int stepSize = hashStepSize(key);

    while (hashArray[hashValue] != null) {
      if (hashArray[hashValue].getKey() == key) {
        return hashArray[hashValue];
      }
      hashValue += stepSize;
      hashValue %= arraySize;
    }

    return null;
  }

  private int hash(int key) {
    return key % arraySize;
  }

  private int hashStepSize(int key) {
    return 5 - (key % 5);
  }
}
