import java.util.*;

public class GraphImpl implements Graph {
    private final List<Vertex> vertexList;
    private final int[][] adjMatrix;
    private int minPAth = 0;
    private int [] paths;
    List<Integer> values = new LinkedList<>();

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
    }

    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, int value) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = value;
        //adjMatrix[startIndex][endIndex] = edgeRandomizer();
        return false;
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, String... others) {
        boolean result = addEdge(startLabel, secondLabel, 1);

        for (String other: others) {
            result &= addEdge(startLabel, other, 1);
        }

        return result;
    }

    private int edgeRandomizer() {
        int a = 3;
        int b = 9;
        int random = a + (int) (Math.random() * b);
        return random;
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
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    public void displayMatrix() {
        int count = 0;
        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix.length; j++) {
                System.out.print(adjMatrix[count][j] + " ");
            }
            System.out.println();
            count++;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            stringBuilder.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] != 0) {
                    stringBuilder.append("->").append(vertexList.get(j));
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void findMinPath(String startLabel, String finalLabel) {
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
    public void bfs(String startLabel, String finalLabel) {
        int startIndex = indexOf(startLabel);
        int finalIndex = indexOf(finalLabel);

        if(startIndex == -1 && finalIndex == -1) {
            throw new IllegalArgumentException("Wrong vertex" + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>();

        resetVertexVisited();

        Vertex vertex = vertexList.get(startIndex);
        visitVertex(queue, vertex);

        for (int i = 0; i < getSize(); i++) {
            if(adjMatrix[indexOf(vertex.getLabel())][i] != 0) {
                values.add(getEdge(indexOf(vertex.getLabel()), i));
            }
        }
        System.out.println(values);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if(vertex != null) {
                visitVertex(queue, vertex);
                result(vertex);
            } else {
                System.out.println(queue);
                queue.remove();
            }
        }

        /*
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[i][finalIndex] != 0) {
                values.add(adjMatrix[i][finalIndex]);
            }
        }

         */

        System.out.println(values);
        /*
        if (vertex == vertexList.get(finalIndex)) {
            System.out.println("You arrived");
        }
         */
        System.out.println();
    }

    private void getAllEdgesOf(int index) {
        for (int i = 0; i < getSize(); i++) {
            if(adjMatrix[index][i] != 0) {
                values.add(adjMatrix[index][i]);
                System.out.println(values);
            }
        }
    }
    private int getEdge(int startIndex, int secondIndex) {

        return adjMatrix[startIndex][secondIndex];
    }

    private void result(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if(adjMatrix[currentIndex][i] != 0 && !vertexList.get(i).isVisited()) {
                values.add(getEdge(currentIndex, i));
                System.out.println(values);
            }
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if(adjMatrix[currentIndex][i] != 0 && !vertexList.get(i).isVisited()) {

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
