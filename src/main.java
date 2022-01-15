

public class main {

    public static void main(String[] args) {
        //testGraph();
        //testDFS();
        //testBFS();
        testMin();
    }

    private static void testMin() {
        GraphImpl graph = new GraphImpl(10);
        graph.addVertex("Moskva");
        graph.addVertex("Tula");
        graph.addVertex("Ryazan");
        graph.addVertex("Kaluga");
        graph.addVertex("Lipetsk");
        graph.addVertex("Tambov");
        graph.addVertex("Orel");
        graph.addVertex("Saratov");
        graph.addVertex("Kursk");
        graph.addVertex("Voronezh");

        //first
        graph.addEdge("Moskva", "Tula", "Ryazan", "Kaluga");
        //way#1
        graph.addEdge("Tula", "Lipetsk", 2);
        graph.addEdge("Lipetsk", "Voronezh", 3);
        //way#2
        graph.addEdge("Ryazan", "Tambov", 5);
        graph.addEdge("Tambov", "Saratov", 3);
        graph.addEdge("Saratov", "Voronezh", 1);
        //way#3
        graph.addEdge("Kaluga", "Orel", 2);
        graph.addEdge("Orel", "Kursk", 7);
        graph.addEdge("Kursk", "Voronezh", 1);

        graph.bfs("Moskva", "Voronezh");

        graph.displayMatrix();
    }

    private static void testGraph() {
        Graph graph = new GraphImpl(7);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", "C");
        graph.addEdge("B", "C", "D");
        graph.addEdge("C", "A", "B", "D");
        graph.addEdge("D", "B", "C");

        System.out.println("Size of graph is " + graph.getSize());
        graph.display();
    }

    private static void testDFS() {
        Graph graph = new GraphImpl(7);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge("A", "B", "C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("D", "F");
        graph.addEdge("F", "G");

        graph.dfs("A");
    }

    private static void testBFS() {
        Graph graph = new GraphImpl(8);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");

        graph.addEdge("A", "B", "C", "D");
        graph.addEdge("B", "E");
        graph.addEdge("E", "H");
        graph.addEdge("C", "F");
        graph.addEdge("D", "G");

    }
}
