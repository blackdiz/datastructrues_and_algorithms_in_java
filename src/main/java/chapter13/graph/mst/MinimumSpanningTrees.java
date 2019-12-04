package chapter13.graph.mst;

class Stack {

  private int top;
  private int[] array;

  public Stack(int size) {
    array = new int[size];
    top = -1;
  }

  public int peek() {
    return array[top];
  }

  public void push(int value) {
    if (isFull()) {
      throw new IllegalStateException("Stack is full.");
    }
    array[++top] = value;
  }

  public int pop() {
    if (isEmpty()) {
      return -1;
    }
    return array[top--];
  }

  public boolean isEmpty() {
    return top == -1;
  }

  public boolean isFull() {
    return top + 1 == array.length;
  }
}

class Vertex {

  private char label;
  public boolean wasVisited;

  public Vertex(char label) {
    this.label = label;
    wasVisited = false;
  }

  public void display() {
    System.out.print(label);
  }
}

class Graph {

  private int MAX_SIZE = 20;
  private Stack stack;
  private Vertex[] vertices;
  private int[][] adjacencyVertexMatrix;
  private int nVertices;

  public Graph() {
    stack = new Stack(MAX_SIZE);
    vertices = new Vertex[MAX_SIZE];
    adjacencyVertexMatrix = new int[MAX_SIZE][MAX_SIZE];
    nVertices = 0;
  }

  public void addVertex(char label) {
    vertices[nVertices++] = new Vertex(label);
  }

  public void addEdge(int start, int end) {
    adjacencyVertexMatrix[start][end] = 1;
    adjacencyVertexMatrix[end][start] = 1;
  }

  public void minimumSpanningTrees() {
    Vertex startVertex = vertices[0];
    startVertex.wasVisited = true;
    stack.push(0);

    while (!stack.isEmpty()) {
      int currentVertexIndex = stack.peek();
      int adjacentVertexIndex = getUnvisitedAdjacentVertexIndex(currentVertexIndex);
      if (adjacentVertexIndex != -1) {
        Vertex adjacentVertex = vertices[adjacentVertexIndex];
        adjacentVertex.wasVisited = true;
        vertices[currentVertexIndex].display();
        adjacentVertex.display();
        System.out.print(" ");
        stack.push(adjacentVertexIndex);
      } else {
        stack.pop();
      }
    }

    for (int i = 0; i < nVertices; i++) {
      vertices[i].wasVisited = false;
    }
  }

  private int getUnvisitedAdjacentVertexIndex(int vertexIndex) {
    for (int i = 0; i < nVertices; i++) {
      if (adjacencyVertexMatrix[vertexIndex][i] == 1 && !vertices[i].wasVisited) {
        return i;
      }
    }

    return -1;
  }
}

public class MinimumSpanningTrees {

  public static void main(String[] args) {
    Graph graph = new Graph();

    graph.addVertex('A');
    graph.addVertex('B');
    graph.addVertex('C');
    graph.addVertex('D');
    graph.addVertex('E');

    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(0, 4);
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(1, 4);
    graph.addEdge(2, 3);
    graph.addEdge(2, 4);
    graph.addEdge(3, 4);

    // 結果應該印出: Minimum spanning tree: AB BC CD DE
    System.out.print("Minimum spanning tree: ");
    graph.minimumSpanningTrees();
  }

}
