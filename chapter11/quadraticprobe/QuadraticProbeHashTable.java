class DataItem {

  private int iData;

  public DataItem(int data) {
    iData = data;
  }

  public int getKey() {
    return iData;
  }
}

public class QuadraticProbeHashTable {

  private int arraySize;
  private DataItem[] hashArray;
  private DataItem nonItem;

  public QuadraticProbeHashTable(int size) {
    arraySize = size;
    hashArray = new DataItem[arraySize];
    nonItem = new DataItem(-1);
  }

  public void insert(DataItem newItem) {
    int hashValue = hash(newItem.getKey());

    int step = 0;
    int probeLength = hashValue;
    while (hashArray[probeLength] != null
        && hashArray[probeLength].getKey() != -1) {
      step++;
      probeLength = hashValue + (step * step);
      probeLength %= arraySize;
    }

    hashArray[hashValue] = newItem;
  }

  private int hash(int key) {
    return key % arraySize;
  }
}
