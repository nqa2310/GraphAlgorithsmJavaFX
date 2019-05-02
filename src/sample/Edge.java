package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

public class Edge {
    public Vertex source, target;
    public double weight;
    public Shape line;
    public Label weightLabel;

    public Shape getLine() {
        return line;
    }

    public Edge(Vertex source, Vertex target) {
        this.source = source;
        this.target = target;
        weight = 0;
    }

//    public Edge(Node argSource, Node argTarget, double argWeight, Shape argline, Label weiLabel) {
//        source = argSource;
//        target = argTarget;
//        weight = argWeight;
//        line = argline;
//        this.weightLabel = weiLabel;
//    }
}
