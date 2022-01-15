

public interface Graph {

    void addVertex(String label);

    boolean addEdge(String startLabel, String secondLabel, String... others);

    boolean addEdge(String startLabel, String secondLabel, int value);

    int getSize();

    void display();

    //Depth-first search
    void dfs(String startLabel);

    //Breadth-first search
    void bfs(String startLabel, String finalLabel);
}
