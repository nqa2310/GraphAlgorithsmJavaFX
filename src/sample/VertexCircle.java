package sample;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.awt.*;
import java.util.List;

public class VertexCircle extends Circle {
    Vertex vertex;
    Point point;
    javafx.scene.control.Label distance = new javafx.scene.control.Label("Dist. : INFINITY");
    javafx.scene.control.Label visitTime = new javafx.scene.control.Label("Visit : 0");
    javafx.scene.control.Label lowTime = new javafx.scene.control.Label("Low : 0");
    javafx.scene.control.Label id;
    boolean isSelected = false;

    public VertexCircle(double x, double y, double rad, String name, Group canvasGroup, List<VertexCircle> circles) {
        super(x, y, rad);
        vertex = new Vertex(name, this);
        point = new Point((int) x, (int) y);
        id = new Label(name);
        id.setFont(Font.font(20));
        canvasGroup.getChildren().add(id);
        id.setLayoutX(x-27);
        id.setLayoutY(y-27);
        this.setOpacity(0.5);
        this.setBlendMode(BlendMode.MULTIPLY);
        this.setId("vertex");
        this.setCursor(Cursor.DEFAULT);
        circles.add(this);
        System.out.println("ADDED: " + circles.size());
    }
}
