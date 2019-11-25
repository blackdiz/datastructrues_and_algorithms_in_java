package chapter13.graph.bfs;

class Queue {

  private int front; // 標記目前可移除元素的位置
  private int rear; // 標記目前已新增元素的位置
  private int[] array;
  private int maxSize;

  public Queue(int size) {
    maxSize = size;
    array = new int[size];
    front = -1;
    rear = -1;
  }

  public void insert(int i) {
    if (!isFull()) {
      if (isEmpty()) {
        // 如果 queue 是空的，則從 index 0 開始
        rear = 0;
        front = 0;
        array[rear] = i;
      } else {
        // 新增時用 % maxSize 找出如果目前已到末端則從前端循環時要插入的 index
        rear = (rear + 1) % maxSize;
        array[rear] = i;
      }
    }
  }

  public int remove() {
    if (isEmpty()) {
      return -1;
    } else {
      int temp = array[front];
      // 當 front == rear 時, 表示已移除最後一個元素, 所以重置 front、rear 回到起始值 -1 表示 queue 為空
      // 因為當 queue 為空在第一次新增元素後，front 和 rear 都為 0，所以不能直接用 front == rear 判斷是否為空
      if (front == rear) {
        front = -1;
        rear = -1;
      } else {
        // 移除元素後用 % maxSize 找出如果目前已到末端則從前端循環時下次要移除元素的 index
        front = (front + 1) % maxSize;
      }

      return temp;
    }
  }

  public int peek() {
    return array[front];
  }

  public boolean isEmpty() {
    return rear == -1;
  }

  // 因為 rear 指向的是可新增元素的位置，而 front 指向的是目前可移除元素的位置,
  // 假如下一個可新增的位置和 front 的位置相同，表示該位置已有元素存在，所以 queue 已滿無法新增
  private boolean isFull() {
    return (rear + 1) % maxSize == front;
  }
}

class Vertex {

  public char label;
  public boolean wasVisited = false;

  public Vertex(char label) {
    this.label = label;
  }
}

class Graph {

  private int MAX_SIZE = 20;
  private Vertex[] vertices;
  private int[][] adjacencyMatrix;
  private Queue queue;
  private int nVertices;

  public Graph() {
    vertices = new Vertex[MAX_SIZE];
    adjacencyMatrix = new int[MAX_SIZE][MAX_SIZE];
    queue = new Queue(MAX_SIZE);
    nVertices = 0;
  }

  public void addVertex(char label) {
    vertices[nVertices++] = new Vertex(label);
  }

  public void addEdge(int start, int end) {
    adjacencyMatrix[start][end] = 1;
    adjacencyMatrix[end][start] = 1;
  }

  public void breadthFirstSearch() {
    Vertex start = vertices[0];
    start.wasVisited = true;
    System.out.print(start.label);
    queue.insert(0);
    int adjacentVertexIndex;

    while (!queue.isEmpty()) {
      int currentVertexIndex = queue.remove();
      while ((adjacentVertexIndex = getAdjacentVertexIndex(currentVertexIndex)) != -1) {
        Vertex adjacentVertex = vertices[adjacentVertexIndex];
        // 當有相鄰的 vertex 時，對它進行 3 個步驟，拜訪它、標記成已拜訪、新增到 queue 中
        adjacentVertex.wasVisited = true;
        System.out.print(adjacentVertex.label);
        queue.insert(adjacentVertexIndex);
      }
    }

    // 全部走過後清除 vertex 的已拜訪標記
    for (
        int i = 0;
        i < nVertices; i++) {
      vertices[i].wasVisited = false;
    }

  }

  private int getAdjacentVertexIndex(int vertexIndex) {
    for (int i = 0; i < nVertices; i++) {
      // 檢查和 vertexIndex 指向的 vertex 是否和其他 vertex 相鄰，若相鄰再檢查是否已拜訪過
      if (adjacencyMatrix[vertexIndex][i] == 1 && !vertices[i].wasVisited) {
        return i;
      }
    }

    return -1;
  }
}

public class BreadthFirstSearch {

  public static void main(String[] args) {
    Graph graph = new Graph();

    graph.addVertex('A');
    graph.addVertex('B');
    graph.addVertex('C');
    graph.addVertex('D');
    graph.addVertex('E');
    graph.addVertex('F');
    graph.addVertex('G');
    graph.addVertex('H');
    graph.addVertex('I');

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(0, 4);
    graph.addEdge(1, 5);
    graph.addEdge(3, 6);
    graph.addEdge(5, 7);
    graph.addEdge(6, 8);

    graph.breadthFirstSearch();
  }
}
