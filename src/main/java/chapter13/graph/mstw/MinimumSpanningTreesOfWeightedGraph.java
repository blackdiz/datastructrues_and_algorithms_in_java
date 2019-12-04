package chapter13.graph.mstw;

class Edge {

  public int sourceVertex;
  public int destinationVertex;
  public int distance;

  public Edge(int sourceVertex, int destinationVertex, int distance) {
    this.sourceVertex = sourceVertex;
    this.destinationVertex = destinationVertex;
    this.distance = distance;
  }
}

class PriorityQueue {

  private final int SIZE = 20;
  private Edge[] queueArray;
  private int size;

  public PriorityQueue() {
    queueArray = new Edge[SIZE];
    size = 0;
  }

  public void insert(Edge item) {
    int i;
    for (i = 0; i < size; i++) {
      if (item.distance >= queueArray[i].distance) {
        break;
      }
    }

    for (int j = size - 1; j >= i; j--) {
      queueArray[j + 1] = queueArray[j];
    }

    queueArray[i] = item;
    size++;
  }

  public Edge removeMin() {
    return queueArray[--size];
  }

  public void removeN(int n) {
    for (int i = n; i < size - 1; i++) {
      queueArray[i] = queueArray[i + 1];
    }

    size--;
  }

  public Edge peekMin() {
    return queueArray[size - 1];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public Edge peekN(int n) {
    return queueArray[n];
  }

  public int find(int targetVertex) {
    for (int i = 0; i < size; i++) {
      if (queueArray[i].destinationVertex == targetVertex) {
        return i;
      }
    }

    return -1;
  }
}

class Vertex {

  public char label;
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
  private Vertex[] vertices;
  private int[][] adjacencyMatrix;
  private int nVertices;
  private int currentVertex;
  private PriorityQueue queue;
  private int nVerticesInTree;

  public Graph() {
    vertices = new Vertex[MAX_VERTICES];
    adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
    nVertices = 0;
    queue = new PriorityQueue();
  }

  public void addVertex(char label) {
    vertices[nVertices++] = new Vertex(label);
  }

  // adjacency matrix 中的預設值為 0，表示起始、終點 vertex 間沒有 edge，如果大於 0 則是 edge 的 weight
  public void addEdge(int start, int end, int weight) {
    adjacencyMatrix[start][end] = weight;
    adjacencyMatrix[end][start] = weight;
  }

  public void minimumSpanningTreesOfWeightedGraph() {
    currentVertex = 0;
    // 重覆進行直到 vertex 都被加進 minimum spanning tree 中
    while (nVerticesInTree < nVertices - 1) {
      vertices[currentVertex].isInTree = true;
      nVerticesInTree++;

      // 檢視 adjacency matrix 將相鄰的 vertex 加到 priority queue 中
      for (int i = 0; i < nVertices; i++) {
        // 如果起始、終點 vertex 相同則略過
        if (i == currentVertex) {
          continue;
        }
        // 如果終點 vertex 已經加進 tree 中則略過
        if (vertices[i].isInTree) {
          continue;
        }

        // 取出相連的 edge 的 distance 也就是 weight
        int distance = adjacencyMatrix[currentVertex][i];
        if (distance == 0) { // 0 表示二者並沒有 edge 相連
          continue;
        }
        putInPriorityQueue(i, distance);
      }

      // 如果 queue 為空表示沒有任何 vertex 和目前所在的 vertex 相連
      if (queue.size() == 0) {
        System.out.println("Graph not connected");
        return;
      }

      Edge edge = queue.removeMin();
      int sourceVertex = edge.sourceVertex;
      currentVertex = edge.destinationVertex;

      vertices[sourceVertex].display();
      vertices[currentVertex].display();
      System.out.print(" ");
    }

    // 重置所有 vertex 的是否已加入 tree 標記
    for (int i = 0; i < nVertices; i++) {
      vertices[i].isInTree = false;
    }
  }

  private void putInPriorityQueue(int destinationVertex, int newDistance) {
    int queueIndex = queue.find(destinationVertex); // 找出 queue 中是否有重複終點 vertex 的 edge 存在
    if (queueIndex != -1) {
      // 如果有就比較該 edge 和新 edge 的 distance 也就是 weight，保留較小方的 edge
      Edge tempEdge = queue.peekN(queueIndex);
      int oldDistance = tempEdge.distance;
      if (oldDistance > newDistance) {
        Edge edge = new Edge(currentVertex, destinationVertex, newDistance);
        queue.removeN(queueIndex);
        queue.insert(edge);
      }
    } else {
      // 如果沒有就直接新增到 priority queue 中
      Edge edge = new Edge(currentVertex, destinationVertex, newDistance);
      queue.insert(edge);
    }
  }
}

public class MinimumSpanningTreesOfWeightedGraph {

  public static void main(String[] args) {
    Graph graph = new Graph();
    graph.addVertex('A');
    graph.addVertex('B');
    graph.addVertex('C');
    graph.addVertex('D');
    graph.addVertex('E');
    graph.addVertex('F');

    graph.addEdge(0, 1, 6); // AB 6
    graph.addEdge(0, 3, 4); // AD 4
    graph.addEdge(1, 2, 10); // BC 10
    graph.addEdge(1, 3, 7); // BD 7
    graph.addEdge(1, 4, 7); // BE 7
    graph.addEdge(2, 3, 8); // CD 8
    graph.addEdge(2, 4, 5); // CE 5
    graph.addEdge(2, 5, 6); // CF 6
    graph.addEdge(3, 4, 12); // DE 12
    graph.addEdge(4, 5, 7); // EF 7

    // 結果應該印出: Minimum spanning tree:AD AB BE EC CF
    System.out.print("Minimum spanning tree:");
    graph.minimumSpanningTreesOfWeightedGraph();
    System.out.println();
  }
}
