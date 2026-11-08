class Stack {
    int[] stack = new int[GraphMatrix.MAX];
    int top;

    Stack() {
        top = -1;
    }

    boolean isEmpty() {
        return this.top == -1;
    }

    boolean isFull() {
        return this.top == stack.length - 1;
    }

    void push(int data) {
        if (!isEmpty()) {
            stack[++top] = data; 
        } else {
            System.out.println("full mas");
        }
    }

    int pop() {
        if (isEmpty()) {
            return -1;
        }

        int popped = stack[top];
        stack[top--] = -1;
        return popped;
    }
}

class Queue {
    int[] queue = new int[GraphMatrix.MAX];
    int front, rear;

    Queue() {
        this.front = 0;
        this.rear = -1;
    }

    boolean isEmpty() {
        return this.rear == -1;
    }

    boolean isFull() {
        return this.rear == queue.length - 1;
    }

    void enqueue(int data) {
        if (isFull()) {
            System.out.println("queue is full mas");
            return;
        } else {
            queue[++rear] = data;
        }
    }

    int dequeue() {
        if (isEmpty()) {
            System.out.println("full mas");
            return -1;
        }

        int removed = queue[front];

        for (int i = 0; i < rear; i++) {
            queue[i] = queue[i + 1];
        }

        queue[rear--] = -1;
        
        return removed;
    }
}

public class GraphMatrix {
    static int MAX = 5;

    private class Vertex {
        char label;
        boolean isVisited;

        Vertex(char label) {
            this.label = label;
            this.isVisited = false;
        }
    }

    Vertex[] verticies = new Vertex[MAX];
    int[][] adjMatrix = new int[MAX][MAX];
    int vertexCount;
    Stack stack;
    Queue queue;

    boolean hasVertex(char label) {
        return getVertexIndex(label) != -1;
    }

    int getVertexIndex(char label) {
        for (int i = 0; i < vertexCount; i++) {
            if (verticies[i].label == label) {
                return i;
            }
        }

        return -1;
    }

    void addVertex(char label) {
        if (vertexCount == MAX) {
            System.out.println("Graph is full");
            return;
        }

        verticies[vertexCount++] = new Vertex(label);
    }

    void addEdge(char startLabel, char endLabel, int weight) {
        if (!hasVertex(startLabel)) addVertex(startLabel);
        if (!hasVertex(endLabel)) addVertex(endLabel);

        int startIndex = getVertexIndex(startLabel);
        int endIndex = getVertexIndex(endLabel);

        adjMatrix[startIndex][endIndex] = weight;
        adjMatrix[endIndex][startIndex] = weight;
    }

    int getUnvisitedNeighbor(int curr) {
        for (int i = 0; i < vertexCount; i++) {
            if (adjMatrix[curr][i] > 0 && !verticies[i].isVisited) {
                return i;
            }
        }

        return -1;
    }

    void resetVisited() {
        for (int i = 0; i < vertexCount; i++) {
            verticies[i].isVisited = false;
        }
    }

    void bfs() {
        System.out.print("BFS: ");
        verticies[0].isVisited = true;
        System.out.print(verticies[0].label + " ");
        queue.enqueue(0);

        while (!queue.isEmpty()) {
            int curr = queue.dequeue();
            int next = getUnvisitedNeighbor(curr);

            while (next != -1) {
                verticies[next].isVisited = true;
                System.out.print("-> " + verticies[next].label + " ");
                queue.enqueue(next);
                
                next = getUnvisitedNeighbor(curr);
            }
        }
        System.out.println();
        resetVisited();
    }

    void dfs() {
        System.out.print("DFS: ");
        verticies[0].isVisited = true;
        System.out.print(verticies[0].label + " ");
        stack.push(0);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            int next = getUnvisitedNeighbor(curr);

            while (next != -1) {
                verticies[next].isVisited = true;
                System.out.println(verticies[next].label + " ");
                stack.push(next);

                next = getUnvisitedNeighbor(curr);
            }
        }
        System.out.println();
        resetVisited();
    }

    void shortestPath(char startLabel, char targetLabel) {
        int start = getVertexIndex(startLabel);
        int target = getVertexIndex(targetLabel);

        if (start == -1 || target == -1) {
            System.out.println("vertex gada");
            return;
        }

        int[] dist = new int[vertexCount];
        int[] prev = new int[vertexCount];
        boolean[] locked = new boolean[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
            locked[i] = false;
        }

        dist[start] = 0;

        for (int step = 0; step < vertexCount; step++) {
            int curr = -1;
            int minDist = Integer.MAX_VALUE;

            for (int v = 0; v < vertexCount; v++) {
                if (!locked[v] && dist[v] < minDist) {
                    minDist = dist[v];
                    curr = v;
                }
            }

            if (curr == -1 || curr == target) {
                break;
            }

            locked[curr] = true;

            // cek semua neighbor

            for (int neighbor = 0; neighbor < vertexCount; neighbor++) {
                int edgeWeight = adjMatrix[curr][neighbor];

                if (edgeWeight > 0 && !locked[neighbor]) {
                    int newDist = dist[curr] + edgeWeight;

                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        prev[neighbor] = curr;
                    }
                }
            }
        }

        if (dist[target] == Integer.MAX_VALUE) {
            System.out.println("gada path mas");
        } else {
            System.out.println("\nshortest pathnya dari " + startLabel + " ke " + targetLabel + " = " + dist[target]);

            int[] path = new int[vertexCount];
            int count = 0;
            int trace = target;

            while (trace != -1) {
                path[count++] = trace;
                trace = prev[trace];
            }

            System.out.print("Path: ");
            for (int i = count - 1; i >= 0; i--) {
                System.out.print(verticies[path[i]].label);
                if (i > 0) System.out.print(" -> ");
            }
            System.out.println();
        }
    }

    void printMatrix() {
        System.out.print("\n\t");
        for (int i = 0; i < vertexCount; i++) {
            System.out.print(verticies[i].label + "\t");
        }
        System.out.println();

        for (int i = 0; i < vertexCount; i++) {
            System.out.print(verticies[i].label + "\t");
            for (int j = 0; j < vertexCount; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GraphMatrix g = new GraphMatrix();

        g.addVertex('A'); 
        g.addVertex('B'); 
        g.addVertex('D'); 
        g.addVertex('E'); 
        g.addVertex('C'); 

        g.addEdge('A', 'B', 2);
        g.addEdge('B', 'C', 4);
        g.addEdge('B', 'D', 1);
        g.addEdge('C', 'E', 2);
        g.addEdge('C', 'A', 3);

        g.bfs();
        g.dfs();

        g.shortestPath('A', 'E');
        g.shortestPath('D', 'E');

        g.printMatrix();
    }
}
