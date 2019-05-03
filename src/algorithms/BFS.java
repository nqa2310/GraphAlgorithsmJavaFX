//package algorithms;
//
//import javafx.animation.FillTransition;
//import javafx.animation.StrokeTransition;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Shape;
//import javafx.util.Duration;
//import sample.Controller;
//import sample.Edge;
//import sample.Vertex;
//
//import java.util.LinkedList;
//
//public class BFS {
//    public BFS(Vertex source) {
//        source.minDistance = 0;
//        source.visited = true;
//        LinkedList<Vertex> q = new LinkedList<>();
//        q.push(source);
//        while (!q.isEmpty()) {
//            Vertex u = q.removeLast();
//            FillTransition ft = new FillTransition(Duration.millis(time),u.circle);
//            if (u.circle.getFill() == Color.BLACK) {
//                ft.setToValue(Color.CHOCOLATE);
//            }
//            st.getChildren().add(ft);
//
//            System.out.println(u.name);
//            for (Edge e: u.adjacents) {
//                if (e != null) {
//                    Vertex v = e.target;
//
//                    if(!v.visited) {
//                        v.minDistance = u.minDistance + 1;
//                        v.visited = true;
//                        q.push(v);
//                        v.previous = u;
//
//                        //Vertex visiting animation
//                        //change Edge color
//                        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time),e.line);
//                        ftEdge.setToValue(Color.FORESTGREEN);
//                        st.getChildren().add(ftEdge);
//                    }
//                    FillTransition ft1 = new FillTransition(Duration.millis(time),v.circle);
//                    ft1.setToValue(Color.FORESTGREEN);
//                    ft1.setOnFinished(ev -> {
//                        v.circle.distance.setText("Dist. : " + v.minDistance);
//                    });
//                    ft1.onFinishedProperty();
//                    st.getChildren().add(ft1);
//                }
//            }
//            FillTransition ft2 = new FillTransition(Duration.millis(time), u.circle);
//            ft2.setToValue(Color.BLUEVIOLET);
//            st.getChildren().add(ft2);
//        }
//
//        st.setOnFinished(ev -> {
//            for (VertexFX n: circles) {
//                FillTransition ft1 = new FillTransition(Duration.millis(time),n);
//                ft1.setToValue(Color.BLACK);
//                ft1.play();
//            }
////            if (directed) {
////                for (Shape n : edges) {
////                    n.setFill(Color.BLACK);
////                }
////            } else if (undirected) {
//                for (Shape n : edges) {
//                    n.setStroke(Color.BLACK);
//                }
////            }
//            FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
//            ft1.setToValue(Color.RED);
//            ft1.play();
//        });
//        st.onFinishedProperty();
//        st.play();
//        playing = true;
//        paused = false;
//
//    }
//}
