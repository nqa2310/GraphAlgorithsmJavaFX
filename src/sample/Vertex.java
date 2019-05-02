package sample;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{

    public String name;
    public List<Edge> adjacents = new ArrayList<Edge>();
    public List<Edge> revAdjacents = new ArrayList<Edge>();
    public Vertex previous;
    public Controller.VertexFX circle;
    public double minDistance = Double.POSITIVE_INFINITY;
    public boolean visited, isArticulationPoint;
    public int visitTime = 0, lowTime = 0;
    public int DAGColor;

    public Vertex(String name) {
        name = name;
        visited = false;
    }

    public Vertex(String name, Controller.VertexFX c) {
        name = name;
        circle = c;
        visited = false;
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(minDistance, o.minDistance);
    }
}
