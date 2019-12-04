package chapter13.graph.dijkstra;

// 記錄 parent vertex 和距離
class DistanceParent {

  public int distance;
  public int parentVertex;

  public DistanceParent(int parentVertex, int distance) {
    this.parentVertex = parentVertex;
    this.distance = distance;
  }
}

class Vertex {

  private char label;
  public boolean isInTree;

  public Vertex(char label) {
    this.label = label;
    isInTree = false;
  }

  public void display() {
    System.out.print(label);
  }
}

class Graph {

  private final int MAX_VERTICES = 20;
  private final int INFINITY = 100000000;
  private Vertex[] vertices;
  private int adjacencyMatrix[][];
  private int nVertices;
  private int nVerticesInTree;
  private DistanceParent[] shortestPath;
  private int currentVertex;
  private int distanceFromStartToCurrentVertex;

  public Graph() {
    vertices = new Vertex[MAX_VERTICES];
    adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];

    for (int row = 0; row < adjacencyMatrix.length; row++) {
      for (int column = 0; column < adjacencyMatrix[row].length; column++) {
        adjacencyMatrix[row][column] = INFINITY;
      }
    }

    shortestPath = new DistanceParent[MAX_VERTICES];
  }

  public void addVertex(char label) {
    vertices[nVertices++] = new Vertex(label);
  }

  public void addEdge(int start, int end, int weight) {
    adjacencyMatrix[start][end] = weight;
  }

  public void shortestPath() {
    int startVertexInTree = 0; // 起始 vertex 由第 1 個新增的 vertex 開始
    vertices[startVertexInTree].isInTree = true;
    nVerticesInTree = 1; // 將它加進 tree 中

    // 將起始 vertex 到其他 vertex 的距離複製到 shortest-path array 中, 做為起始值
    for (int i = 0; i < nVertices; i++) {
      int tempDistance = adjacencyMatrix[startVertexInTree][i];
      shortestPath[i] = new DistanceParent(startVertexInTree, tempDistance);
    }

    // 反覆計算直到所有 vertex 都加到 tree 中為止
    while (nVerticesInTree < nVertices) {
      int minDistanceVertex = getMinDistanceVertex();
      int minDistance = shortestPath[minDistanceVertex].distance;

      if (minDistance == INFINITY) {
        // 假如僅剩的最短距離為無限遠, 表示剩下的 vertex 和起始 vertex 沒有連接, 所以不用加入 tree 中
        System.out.println("There are unreachable vertices");
        break;
      } else {
        currentVertex = minDistanceVertex;
        distanceFromStartToCurrentVertex = shortestPath[minDistanceVertex].distance;
      }
      vertices[minDistanceVertex].isInTree = true;
      nVerticesInTree++;
      adjustShortestPathArray(); // 更新 shortest-path array 裡最短距離的資料
    }

    displaySortestPaths();

    // 重置是否已加入 tree 中的標記
    nVerticesInTree = 0;
    for (int i = 0; i < nVertices; i++) {
      vertices[i].isInTree = false;
    }
  }

  private int getMinDistanceVertex() {
    int minDistance = INFINITY;
    int minDistanceVertex = 0;

    for (int i = 1; i < nVertices; i++) {
      if (!vertices[i].isInTree && shortestPath[i].distance < minDistance) {
        minDistance = shortestPath[i].distance;
        minDistanceVertex = i;
      }
    }

    return minDistanceVertex;
  }

  private void adjustShortestPathArray() {
    int column = 1; // 略過和起始 vertex 的距離
    while (column < nVertices) {
      // 如果和目前指向的 vertex = currentVertex 相連的 vertex 已經加入 tree 中則不須檢查是否有更短距離
      if (vertices[column].isInTree) {
        column++;
        continue;
      }

      // distanceFromCurrentToFringe 為目前指向的 vertex 可到達但尚未加入 tree 中的 vertex 的距離
      int distanceFromCurrentToFringe = adjacencyMatrix[currentVertex][column];
      int distanceFromStartToFringe =
          distanceFromStartToCurrentVertex + distanceFromCurrentToFringe;

      if (distanceFromStartToFringe < shortestPath[column].distance) {
        shortestPath[column].distance = distanceFromStartToFringe;
        shortestPath[column].parentVertex = currentVertex;
      }
      column++;
    }
  }

  private void displaySortestPaths() {
    for (int i = 0; i < nVertices; i++) {
      vertices[i].display();
      System.out.print("=");

      if (shortestPath[i].distance == INFINITY) {
        System.out.print("inf");
      } else {
        System.out.print(shortestPath[i].distance);
      }
      int parentVertex = shortestPath[i].parentVertex;
      System.out.print("(");
      vertices[parentVertex].display();
      System.out.print(") ");
    }
    System.out.println();
  }
}

public class ShortestPath {


  public static void main(String[] args) {
    Graph graph = new Graph();

    graph.addVertex('A');
    graph.addVertex('B');
    graph.addVertex('C');
    graph.addVertex('D');
    graph.addVertex('E');

    graph.addEdge(0, 1, 50); // AB 50
    graph.addEdge(0, 3, 80); // AD 80
    graph.addEdge(1, 2, 60); // BC 60
    graph.addEdge(1, 3, 90); // BD 90
    graph.addEdge(2, 4, 40); // CE 40
    graph.addEdge(3, 2, 20); // DC 20
    graph.addEdge(3, 4, 70); // DE 70
    graph.addEdge(4, 1, 50); // EB 50

    // 結果應該印出: A=inf(A) B=50(A) C=100(D) D=80(A) E=140(C)
    graph.shortestPath();
  }
}
