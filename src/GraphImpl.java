import java.util.*;

public class GraphImpl implements Graph {
    private final List<Vertex> vertexList;
    private final int[][] adjMatrix;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = 1;
        return false;
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, String... others) {
        boolean result = addEdge(startLabel, secondLabel);

        for (String other: others) {
            result &= addEdge(startLabel, other);
        }

        return result;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            stringBuilder.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] == 1) {
                    stringBuilder.append("->").append(vertexList.get(j));
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if(startIndex == -1) {
            throw new IllegalArgumentException("Wrong vertex" + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        
        resetVertexVisited();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);

        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if(vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }

        System.out.println();
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if(startIndex == -1) {
            throw new IllegalArgumentException("Wrong vertex" + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>() {
        };

        resetVertexVisited();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);

        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if(vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }

        System.out.println();
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if(adjMatrix[currentIndex][i] == 1 && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        System.out.println(vertex.getLabel() + " ");
        stack.add(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        System.out.println(vertex.getLabel() + " ");
        queue.add(vertex);
        vertex.setVisited(true);
    }

    private void resetVertexVisited() {
        for (Vertex vertex: vertexList) {
            vertex.setVisited(false);
        }
    }
}
