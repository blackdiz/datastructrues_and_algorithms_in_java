package chapter13.graph.topologicalsort;

class Vertex {

  public char label;
  public boolean wasVisited;

  public Vertex(char label) {
    this.label = label;
    wasVisited = false;
  }
}

class Graph {

  private int MAX_SIZE = 20;
  private Vertex[] vertices;
  private int[][] adjacencyMatrix;
  private int nVertices;
  private char[] sortedArray;

  public Graph() {
    vertices = new Vertex[MAX_SIZE];
    adjacencyMatrix = new int[MAX_SIZE][MAX_SIZE];
    nVertices = 0;
    sortedArray = new char[MAX_SIZE];
  }

  public void addVertex(char label) {
    vertices[nVertices++] = new Vertex(label);
  }

  // 因為是 directed graph 所以 edge 只會有從起點到終點一個方向
  public void addEdge(int start, int end) {
    adjacencyMatrix[start][end] = 1;
  }

  public void topologicalSort() {
    int originalVertices = nVertices;
    while (nVertices > 0) {
      int currentVertex = noSuccessors();
      if (currentVertex == -1) {
        System.out.println("ERROR: Graph has cycles");
        return;
      }

      sortedArray[nVertices - 1] = vertices[currentVertex].label;
      deleteVertex(currentVertex);
    }

    System.out.print("Topologically sorted order: ");

    for (int i = 0; i < originalVertices; i++) {
      System.out.print(sortedArray[i]);
    }

    System.out.println();
  }

  private int noSuccessors() {
    boolean isEdge;
    // 遍歷 adjacency matrix 找到沒有和其他 vertex 有相連的 vertex
    for (int row = 0; row < nVertices; row++) {
      isEdge = false;
      for (int column = 0; column < nVertices; column++) {
        if (adjacencyMatrix[row][column] > 0) {
          isEdge = true;
          break;
        }
      }

      if (!isEdge) {
        return row;
      }
    }

    return -1;
  }

  private void deleteVertex(int deleteVertexIndex) {
    if (deleteVertexIndex != nVertices - 1) {
      for (int i = deleteVertexIndex; i < nVertices - 1; i++) {
        vertices[i] = vertices[i + 1];
      }

      for (int row = deleteVertexIndex; row < nVertices - 1; row++) {
        moveRowUp(row, nVertices);
      }

      for (int column = deleteVertexIndex; column < nVertices - 1; column++) {
        // 因為 row 在前面已經刪除一列所以這裡移動 column 時少移動 1 個 row
        moveColumnLeft(column, nVertices - 1);
      }
    }

    nVertices--;
  }

  private void moveRowUp(int row, int columnLength) {
    for (int column = 0; column < columnLength; column++) {
      adjacencyMatrix[row][column] = adjacencyMatrix[row + 1][column];
    }
  }

  private void moveColumnLeft(int column, int rowLength) {
    for (int row = 0; row < rowLength; row++) {
      adjacencyMatrix[row][column] = adjacencyMatrix[row][column + 1];
    }
  }
}

public class TopologicalSort {

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

    graph.addEdge(0, 3); // AD
    graph.addEdge(0, 4); // AE
    graph.addEdge(1, 4); // BE
    graph.addEdge(2, 5); // CF
    graph.addEdge(3, 6); // DG
    graph.addEdge(4, 6); // EG
    graph.addEdge(5, 7); // FH
    graph.addEdge(6, 7); // GH

    graph.topologicalSort();
  }
}
