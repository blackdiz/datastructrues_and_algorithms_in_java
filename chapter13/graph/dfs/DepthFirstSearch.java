class Stack {

  private int[] array;
  private int top;

  public Stack(int size) {
    array = new int[size];
    top = -1;
  }

  public void push(int item) {
    array[++top] = item;
  }

  public int pop() {
    return array[top--];
  }

  public int peek() {
    return array[top];
  }

  public boolean isEmpty() {
    return top == -1;
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

  private final int MAX_VERTICES = 20;
  private Vertex vertexList[]; // 儲存 vertex
  private int adjacencyMatrix[][]; // 用 adjacency matrix 記錄 vertex 的相鄰狀態, 1 為相鄰, 0 為不相鄰
  private int nVertices; // 目前的 vertex 數;
  private Stack stack; // 進行 depth-first search 時的路線記錄

  public Graph() {
    vertexList = new Vertex[MAX_VERTICES];
    adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES]; // 初始所有元素均為 0
    nVertices = 0;
    stack = new Stack(20);
  }

  public void addVertex(char label) {
    vertexList[nVertices++] = new Vertex(label);
  }

  public void addEdge(int start, int end) {
    // 因為 adjacency matrix 中 vertex 的行列是鏡象重複，所以要同時標記起點終點和終點起點
    adjacencyMatrix[start][end] = 1;
    adjacencyMatrix[end][start] = 1;
  }

  public void displayVertex(int v) {
    System.out.print(vertexList[v].label);
  }

  public void depthFirstSearch() {
    vertexList[0].wasVisited = true;
    displayVertex(0);
    stack.push(0);
    while (!stack.isEmpty()) {
      int adjacentVertexIndex = getAdjacentUnvisitedVertex(stack.peek());
      if (adjacentVertexIndex == -1) {
        stack.pop(); // 如果沒有相鄰的 vertex 就從 stack pop 目前的 vertex
      } else {
        // 否則一樣拜訪、標記成已拜訪、放入 stack
        vertexList[adjacentVertexIndex].wasVisited = true;
        displayVertex(adjacentVertexIndex);
        stack.push(adjacentVertexIndex);
      }
    }

    // 全部走過後清除 vertex 的已拜訪標記
    for (int i = 0; i < nVertices; i++) {
      vertexList[i].wasVisited = false;
    }
  }

  private int getAdjacentUnvisitedVertex(int vertexIndex) {
    for (int i = 0; i < nVertices; i++) {
      // 檢查和 vertexIndex 指向的 vertex 是否和其他 vertex 相鄰，若相鄰再檢查是否已拜訪過
      if (adjacencyMatrix[vertexIndex][i] == 1 && !vertexList[i].wasVisited) {
        return i;
      }
    }

    return -1;
  }
}

public class DepthFirstSearch {

  public static void main(String[] args) {
    Graph graph = new Graph();

    graph.addVertex('A');
    graph.addVertex('B');
    graph.addVertex('C');
    graph.addVertex('D');
    graph.addVertex('E');

    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(0, 3);
    graph.addEdge(3, 4);

    // 結果應該印出: Visits: ABCDE
    System.out.print("Visits: ");
    graph.depthFirstSearch();
    System.out.println();
  }
}
