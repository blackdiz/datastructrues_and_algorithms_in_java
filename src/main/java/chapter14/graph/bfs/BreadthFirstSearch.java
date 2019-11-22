package chapter14.graph.bfs;

class Queue {

  private int front;
  private int rear;
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
        rear = 0;
        front = 0;
        array[rear] = i;
      } else {
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
      if (front == rear) {
        front = -1;
        rear = -1;
      } else {
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

    while (!queue.isEmpty()) {
      int currentVertexIndex = queue.peek();
      int adjacentVertexIndex = getAdjacentVertexIndex(currentVertexIndex);
      if (adjacentVertexIndex != -1) {
        Vertex adjacentVertex = vertices[adjacentVertexIndex];
        adjacentVertex.wasVisited = true;
        System.out.print(adjacentVertex.label);
        queue.insert(adjacentVertexIndex);
      } else {
        queue.remove();
      }
    }

    for (int i = 0; i < nVertices; i++) {
      vertices[i].wasVisited = false;
    }
  }

  private int getAdjacentVertexIndex(int index) {
    for (int i = 0; i < nVertices; i++) {
      if (adjacencyMatrix[index][i] == 1 && !vertices[i].wasVisited) {
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
