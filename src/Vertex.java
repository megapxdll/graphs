import java.util.Objects;

public class Vertex {
    private final String label;

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    private  boolean isVisited;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) obj;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public String toString() {
        return label;
    }
}
