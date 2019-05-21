package program;

import com.jfoenix.controls.*;
import graph.Edge;
import graph.EdgeArrow;
import graph.Vertex;
import graph.VertexCircle;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import util.Find;

import java.net.URL;
import java.util.*;
import java.util.List;

public class Controller implements Initializable {

    @FXML
    private JFXButton clearButton;
    @FXML
    private JFXButton resetButton;
    @FXML
    private JFXButton playPauseButton;
    @FXML
    private JFXToggleButton dijkstraButton;
    @FXML
    private JFXToggleButton addVertexButton;
    @FXML
    private JFXToggleButton addEdgeButton;
    @FXML
    private JFXToggleButton bfsButton;
    @FXML
    private JFXToggleButton dfsButton;
    @FXML
    private Pane viewer;
    @FXML
    private Group canvasGroup;
    @FXML
    private Line edgeLine;
    @FXML
    private Label sourceText = new Label("Source"), weight;
    @FXML
    private JFXNodesList nodeList;
    @FXML
    private JFXSlider slider;
    @FXML
    private ImageView playPauseImage;
    @FXML
    private JFXRadioButton weightedButton;
    @FXML
    private JFXRadioButton unweightedButton;
    @FXML
    private JFXRadioButton directedButton;
    @FXML
    private JFXRadioButton undirectedButton;


    int nVertex = 0;
    int time = 500;

    VertexCircle selectedVertex = null;

    VertexCircle source = null;

    EdgeArrow arrow;

    List<VertexCircle> circles = new ArrayList<>();
    List<Edge> realEdges = new ArrayList<>();
    List<Shape> edges = new ArrayList<>();
    List<Label> distances = new ArrayList<>();

    boolean addVertex = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false;

    private boolean weighted = false,unweighted = true,directed = false, undirected = true,bfs = true, dfs = true, dijkstra = true;

    Algorithm algo = new Algorithm() ;

    public static TextArea textFlow = new TextArea();

    public SequentialTransition animations ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("In intit");
        weightedButton.setUnSelectedColor(Color.BLACK);
        unweightedButton.setUnSelectedColor(Color.BLACK);

        ResetHandle(null);
        undirectedButton.setSelected(true);
        unweightedButton.setSelected(true);
        addEdgeButton.setDisable(true);
        addVertexButton.setDisable(true);
        clearButton.setDisable(true);
        bfsButton.setDisable(true);
        dfsButton.setDisable(true);
        dijkstraButton.setDisable(true);
        Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
        playPauseImage.setImage(image);

//        //Set TextFlow pane properties
//        textFlow.setPrefSize(hiddenRoot.getPrefWidth(), hiddenRoot.getPrefHeight() - 2);
////        textFlow.prefHeightProperty().bind(hiddenRoot.heightProperty());
//        textFlow.setStyle("-fx-background-color: #dfe6e9;");
//        textFlow.setLayoutY(39);
//        textContainer.setLayoutY(textFlow.getLayoutY());
//        textFlow.setPadding(new Insets(5, 0, 0, 5));
//        textFlow.setEditable(false);
//        textContainer.setContent(textFlow);

    }

    /**
     * Checks if an edge already exists between two nodes before adding a new
     * edge.
     *
     * @param u = selected node
     * @param v = second selected node
     * @return True if edge already exists. Else false.
     */
    boolean edgeExists(VertexCircle u, VertexCircle v) {
        for (Edge e : realEdges) {
            if (e.source == u.vertex && e.target == v.vertex) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles events for mouse clicks on the canvas. Adds a new node on the
     * drawing canvas where mouse is clicked.
     *
     * @param ev
     */
    @FXML
    public void handle(MouseEvent ev) {
        if (addVertex) {
            if (nVertex == 1) {
                addVertexButton.setDisable(false);
            }
            if (nVertex == 2) {
                addEdgeButton.setDisable(false);
                AddVertexHandle(null);
            }

            if (!ev.getSource().equals(canvasGroup)) {
                if (ev.getEventType() == MouseEvent.MOUSE_RELEASED && ev.getButton() == MouseButton.PRIMARY) {
                    nVertex++;
                    VertexCircle circle = new VertexCircle(ev.getX(), ev.getY(), 20, String.valueOf(nVertex),canvasGroup,circles);
                    circle.setFill(Color.GREY);
                    canvasGroup.getChildren().add(circle);

                    circle.setOnMousePressed(mouseHandler);
                    circle.setOnMouseReleased(mouseHandler);
                    circle.setOnMouseDragged(mouseHandler);
                    circle.setOnMouseExited(mouseHandler);
                    circle.setOnMouseEntered(mouseHandler);

//                    ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
//                    tr.setByX(15f);
//                    tr.setByY(15f);
//                    tr.setInterpolator(Interpolator.EASE_OUT);
//                    tr.play();

                }
            }
        }
    }

    /**
     * Adds an edge between two selected nodes. Handles events for mouse clicks
     * on a node.
     */
    EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {
            VertexCircle circle = (VertexCircle) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (!circle.isSelected) {
                    if (selectedVertex != null) {
                        if (addEdge && !edgeExists(selectedVertex, circle)) {
                            weight = new Label();
                            System.out.println("Adding Edge");
                            Find F1 = new Find();
                            F1.findLine(selectedVertex.point.x,selectedVertex.point.y,circle.point.x,circle.point.y);
                            F1.findCoordinates(selectedVertex.point.x,selectedVertex.point.y,circle.point.x,circle.point.y,20);
                            Find F2 = new Find();
                            F2.findLine(circle.point.x,circle.point.y,selectedVertex.point.x,selectedVertex.point.y);
                            F2.findCoordinates(circle.point.x,circle.point.y,selectedVertex.point.x,selectedVertex.point.y,20);
                            //Adds the edge between two selected nodes
                            if (undirected) {
                                edgeLine = new Line(F1.X, F1.Y, F2.X, F2.Y);
                                edgeLine.setStrokeWidth(2);
                                canvasGroup.getChildren().add(edgeLine);
                                edgeLine.setId("line");
                                edgeLine.setCursor(Cursor.DEFAULT);
                            } else if (directed) {
                                arrow = new EdgeArrow(F1.X, F1.Y, F2.X, F2.Y);
                                canvasGroup.getChildren().add(arrow);
                                arrow.setId("arrow");
                                arrow.setCursor(Cursor.DEFAULT);
                                arrow.setStrokeWidth(2);
                            }

                            //Adds weight between two selected nodes
                            if (weighted) {
                                weight.setLayoutX(((selectedVertex.point.x) + (circle.point.x)) / 2);
                                weight.setLayoutY(((selectedVertex.point.y) + (circle.point.y)) / 2);

                                TextInputDialog dialog = new TextInputDialog("0");
                                dialog.setTitle(null);
                                dialog.setHeaderText("Enter Weight of the Edge :");
                                dialog.setContentText(null);

                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {
                                    weight.setText(result.get());
                                } else {
                                    weight.setText("0");
                                }
                                canvasGroup.getChildren().add(weight);
                            } else if (unweighted) {
                                weight.setText("1");
                            }

                            Edge temp = null;
                            if (undirected) {
                                selectedVertex.vertex.adjacents.add(new Edge(selectedVertex.vertex, circle.vertex, Double.valueOf(weight.getText()), edgeLine, weight));
                                circle.vertex.adjacents.add(new Edge(circle.vertex, selectedVertex.vertex, Double.valueOf(weight.getText()), edgeLine, weight));
                                edges.add(edgeLine);
                                realEdges.add(selectedVertex.vertex.adjacents.get(selectedVertex.vertex.adjacents.size() - 1));
                                realEdges.add(circle.vertex.adjacents.get(circle.vertex.adjacents.size() - 1));

                            } else if (directed) {
                                temp = new Edge(selectedVertex.vertex, circle.vertex, Double.valueOf(weight.getText()), arrow, weight);
                                selectedVertex.vertex.adjacents.add(temp);
                                edges.add(arrow);
                                realEdges.add(temp);
                            }
                        }
                        if (addVertex || addEdge) {
                            selectedVertex.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), selectedVertex, Color.GREEN, Color.GRAY);
                            ft1.play();
                        }
                        selectedVertex = null;
                        return;
                    }
                    if(!calculate) {
                        FillTransition ft = new FillTransition(Duration.millis(300), circle, Color.GRAY, Color.GREEN);
                        ft.play();
                        circle.isSelected = true;
                        selectedVertex = circle;
                    }

                    // WHAT TO DO WHEN SELECTED ON ACTIVE ALGORITHM
                    if (calculate && !calculated) {
                        if (bfs) {
                            FillTransition ft1 = new FillTransition(Duration.millis(time), circle.vertex.circle);
                            ft1.setToValue(Color.RED);
                            ft1.play();
                            source = circle;
                            algo.BFS(source.vertex);
                        }
                        else if (dfs) {
                            FillTransition ft1 = new FillTransition(Duration.millis(time), circle.vertex.circle);
                            ft1.setToValue(Color.RED);
                            ft1.play();
                            source = circle;
                            algo.DFS(source.vertex);
                        }
                        else if (dijkstra) {
                            FillTransition ft1 = new FillTransition(Duration.millis(time), circle.vertex.circle);
                            ft1.setToValue(Color.RED);
                            ft1.play();
                            source = circle;
                            algo.Dijkstra(source.vertex);
                        }

                        calculated = true;
                    }
                    else if (calculate && calculated) {

                        for (VertexCircle n : circles) {
                            n.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), n);
                            ft1.setToValue(javafx.scene.paint.Color.BLACK);
                            ft1.play();
                        }
                        List<Vertex> path = algo.getShortestPathTo(circle.vertex);
                        for (Vertex n : path) {
                            FillTransition ft1 = new FillTransition(Duration.millis(300), n.circle);
                            ft1.setToValue(Color.YELLOW);
                            ft1.play();
                        }
                    }
                } else {
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.GREEN, Color.GRAY);
                    ft1.play();
                    selectedVertex = null;
                }

            }
        }

    };

    @FXML
    public void SpeedHandle(MouseEvent event) {
        int temp = (int)slider.getValue();
        time = temp;
        System.out.println(time);
    }

    /**
     * Event handler for the Play/Pause button.
     *
     * @param event
     */
    @FXML
    public void PlayPauseHandle(ActionEvent event) {
        try{
            if (playing && animations != null && animations.getStatus() == Animation.Status.RUNNING) {
                Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
                playPauseImage.setImage(image);
                System.out.println("PAUSED");
                animations.pause();
                paused = true;
                playing = false;
                return;
            } else if (paused && animations != null) {
                Image image = new Image(getClass().getResourceAsStream("pause_black_48x48.png"));
                playPauseImage.setImage(image);
                if(animations.getStatus() == Animation.Status.PAUSED)
                    animations.play();
                else if(animations.getStatus() == Animation.Status.STOPPED)
                    animations.playFromStart();
                playing = true;
                paused = false;
                return;
            } else if (bfs || dfs || dijkstra ) {
                if (source != null) {
                    playing = true;
                    paused = false;
                    Image image = new Image(getClass().getResourceAsStream("pause_black_48x48.png"));
                    playPauseImage.setImage(image);
                    algo.finishAnimation(source.vertex);
                }
            }
        } catch(Exception e){
            System.out.println("Error while play/pause: " + e);
            ClearHandle(null);
        }
    }

    @FXML
    public void DirectedHandle(ActionEvent event) {
        undirectedButton.setSelected(false);
        directed = true;
        undirected = false;
    }
    @FXML
    public void UndirectedHandle(ActionEvent event) {
        directedButton.setSelected(false);
        undirected = true;
        directed = false;
    }

    @FXML
    public void WeightedHandle(ActionEvent event) {
        unweightedButton.setSelected(false);
        weighted = true;
        unweighted = false;
    }
    @FXML
    public void UnweightedHandle(ActionEvent event) {
        weightedButton.setSelected(false);
        weighted = false;
        unweighted = true;
    }

    /**
     * Event handler for the Add Edge button.
     *
     * @param event
     */
    @FXML
    public void AddEdgeHandle(ActionEvent event) {
        addVertex = false;
        addEdge = true;
        calculate = false;
        weightedButton.setDisable(true);
        unweightedButton.setDisable(true);
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(true);

        if (unweighted) {
            bfsButton.setDisable(false);
            bfsButton.setSelected(false);
            dfsButton.setDisable(false);
            dfsButton.setSelected(false);
        }
        if (weighted) {
            dijkstraButton.setDisable(false);
            dijkstraButton.setSelected(false);
        }
    }

    /**
     * Event handler for the Add Vertex button.
     *
     * @param event
     */
    @FXML
    public void AddVertexHandle(ActionEvent event) {
        addVertex = true;
        addEdge = false;
        calculate = false;
        addVertexButton.setSelected(true);
        addEdgeButton.setSelected(false);
        selectedVertex = null;

        if (unweighted) {
            bfsButton.setDisable(false);
            bfsButton.setSelected(false);
            dfsButton.setDisable(false);
            dfsButton.setSelected(false);
        }
        if (weighted) {
            dijkstraButton.setDisable(false);
            dijkstraButton.setSelected(false);
        }
    }

    @FXML
    public void BFSHandle(ActionEvent event) {
        addVertex = false;
        addEdge = false;
        weightedButton.setDisable(true);
        unweightedButton.setDisable(true);
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(false);
        addVertexButton.setDisable(true);
        addEdgeButton.setDisable(true);
        playPauseButton.setDisable(false);
        calculate = true;
        clearButton.setDisable(false);
        bfs = true;
        dfs = false;
        dijkstra = false;
        dfsButton.setSelected(false);
        dijkstraButton.setSelected(false);
    }

    @FXML
    public void DFSHandle(ActionEvent event) {
        addVertex = false;
        addEdge = false;
        weightedButton.setDisable(true);
        unweightedButton.setDisable(true);
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(false);
        addVertexButton.setDisable(true);
        addEdgeButton.setDisable(true);
        playPauseButton.setDisable(false);
        calculate = true;
        clearButton.setDisable(false);
        dfs = true;
        bfs = false;
        dijkstra = false;
        bfsButton.setSelected(false);
        dijkstraButton.setSelected(false);
    }

    @FXML
    public void DijkstraHandle(ActionEvent event) {
        addVertex = false;
        addEdge = false;
        weightedButton.setDisable(true);
        unweightedButton.setDisable(true);
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(false);
        addVertexButton.setDisable(true);
        addEdgeButton.setDisable(true);
        playPauseButton.setDisable(false);
        calculate = true;
        clearButton.setDisable(false);
        bfs = false;
        dfs = false;
        dijkstra = true;
        bfsButton.setSelected(false);
        dfsButton.setSelected(false);
    }

    /**
     * Event handler for the Reset button. Clears all the lists and empties the
     * canvas.
     *
     * @param event
     */
    @FXML
    public void ResetHandle(ActionEvent event) {
        ClearHandle(null);
        nVertex = 0;
        time = 500;
        canvasGroup.getChildren().clear();
        canvasGroup.getChildren().addAll(viewer);
        selectedVertex = null;
        circles = new ArrayList<>();
//        distances = new ArrayList<Label>();
//        visitTime = new ArrayList<Label>();
//        lowTime = new ArrayList<Label>();
        addVertex = true;
        addEdge = false;
        calculate = false;
        calculated = false;
        slider.setDisable(false);
        addVertexButton.setSelected(true);
        addEdgeButton.setSelected(false);
        addEdgeButton.setDisable(true);
        addVertexButton.setDisable(false);
        weightedButton.setDisable(false);
        unweightedButton.setDisable(false);
        clearButton.setDisable(true);
        algo = new Algorithm();
        Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
        playPauseImage.setImage(image);
        bfsButton.setDisable(true);
        dfsButton.setDisable(true);
        dijkstraButton.setDisable(true);
        playPauseButton.setDisable(true);
        playing = false;
        paused = false;
    }

    /**
     * Event handler for the Clear button. Re-initiates the distance and node
     * values and labels.
     *
     * @param event
     */
    @FXML
    public void ClearHandle(ActionEvent event) {
        if(animations != null && animations.getStatus() != Animation.Status.STOPPED)
            animations.stop();
        if(animations != null) animations.getChildren().clear();
        selectedVertex = null;
        calculated = false;
        System.out.println("CLEAR:" + circles.size());
        for (VertexCircle n : circles) {
            n.isSelected = false;
            n.vertex.visited = false;
            n.vertex.previous = null;
            n.vertex.minDistance = Double.POSITIVE_INFINITY;

            FillTransition ft1 = new FillTransition(Duration.millis(300), n);
            ft1.setToValue(Color.BLACK);
            ft1.play();
        }
        for (Shape x : edges) {
            if (undirected) {
                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), x);
                ftEdge.setToValue(Color.BLACK);
                ftEdge.play();
            } else if (directed) {
                FillTransition ftEdge = new FillTransition(Duration.millis(time), x);
                ftEdge.setToValue(Color.BLACK);
                ftEdge.play();
            }
        }
        canvasGroup.getChildren().remove(sourceText);
        for (Label x : distances) {
            x.setText("Distance : INFINITY");
            canvasGroup.getChildren().remove(x);
        }
//        for (Label x : visitTime) {
//            x.setText("Visit : 0");
//            canvasGroup.getChildren().remove(x);
//        }
//        for (Label x : lowTime) {
//            x.setText("Low Value : NULL");
//            canvasGroup.getChildren().remove(x);
//        }
//        textFlow.clear();
//
        Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
        playPauseImage.setImage(image);
//
        distances = new ArrayList<>();
//        visitTime = new ArrayList<>();
//        lowTime = new ArrayList<>();
        addVertexButton.setDisable(false);
        addEdgeButton.setDisable(false);
        AddVertexHandle(null);
        bfs = false;
        dfs = false;
        dijkstra = false;
        playing = false;
        paused = false;
    }

    @FXML
    public void ForwardHandle(ActionEvent event) {
        try{
            if (paused && animations != null) {
                if(animations.getStatus() == Animation.Status.PAUSED) {
                    animations.jumpTo(animations.getCurrentTime().add(Duration.millis(time)));
                }
                playing = false;
                paused = true;
                return;
            } else if (!paused && !playing && animations != null) {
                algo.finishAnimation(source.vertex);
                animations.pause();
                playing = false;
                paused = true;
                return;
            }
        } catch(Exception e){
            ClearHandle(null);
        }
    }

    @FXML
    public void BackHandle(ActionEvent event) {
        try{
            if (animations != null) {
                if(animations.getStatus() == Animation.Status.PAUSED) {
                    animations.jumpTo(animations.getCurrentTime().subtract(Duration.millis(time)));
                }
                else if(animations.getStatus() == Animation.Status.STOPPED) {
                    animations.playFromStart();
                    animations.pause();
                    animations.jumpTo(animations.getTotalDuration());
                    animations.jumpTo(animations.getCurrentTime().subtract(Duration.millis(time)));
                }
                playing = false;
                paused = true;
                return;
            }
        } catch(Exception e){
            ClearHandle(null);
        }
    }

    @FXML
    public void FinishHandle(ActionEvent event) {
        try{
            if (paused && animations != null) {
                animations.jumpTo(animations.getTotalDuration());
                playing = false;
                paused = true;
                return;
            }
        } catch(Exception e){
            ClearHandle(null);
        }
    }

    @FXML
    public void BeginHandle(ActionEvent event) {
        try{
            if (paused && animations != null) {
                if(animations.getStatus() == Animation.Status.PAUSED) {
                    animations.jumpTo(animations.getTotalDuration().subtract(animations.getTotalDuration()));
                }
                playing = false;
                paused = true;
                return;
            }
        } catch(Exception e){
            ClearHandle(null);
        }
    }

    public class Algorithm {
        LinkedList<Vertex> list;

        public Algorithm() {
            animations = new SequentialTransition();
            list = new LinkedList<>();
        }

        public void BFS(Vertex source) {
            changeVertexColor(source, Color.BLUE);
            source.minDistance = 0;
            source.visited = true;
            list.push(source);
            while (!list.isEmpty()) {
                Vertex u = list.removeLast();
                changeVertexColor(u,Color.DARKVIOLET);
                System.out.println(u.name);
                for (Edge e: u.adjacents) {
                    if (e != null) {
                        Vertex v = e.target;
                        if (!v.visited) {
//                            v.minDistance = u.minDistance + 1;
                            v.visited = true;
                            list.push(v);
                            v.previous = u;

                            changeEdgeColor(e,Color.BLUE);
                            VertexVisitingFinish(v,Color.BLUE);
                        }
                    }
                }
                changeVertexColor(u,Color.GREEN);
            }

        }

        public void DFS(Vertex source) {
            source.minDistance = 0;
            source.visited = true;
            list.push(source);
            DFSRecursion(source,0);
        }

        public void DFSRecursion(Vertex source, int level) {
            //<editor-fold defaultstate="collapsed" desc="Animation Control">
            changeVertexColor(source,Color.BLUE);
//
//        String str = "";
//        for (int i = 0; i < level; i++) {
//            str = str.concat("\t");
//        }
//        str = str.concat("DFS(" + source.name + ") Enter\n");
//        final String str2 = str;
//        FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
//        fd.setOnFinished(e -> {
//            textFlow.appendText(str2);
//        });
//        fd.onFinishedProperty();
//        animations.getChildren().add(fd);
            //</editor-fold>
            for (Edge e : source.adjacents) {
                if (e != null) {
                    Vertex v = e.target;
                    if (!v.visited) {
//                        v.minDistance = source.minDistance + 1;
                        v.visited = true;
                        v.previous = source;
//                        v.circle.distance.setText("Dist. : " + v.minDistance);
                        changeEdgeColor(e,Color.BLUE);
                        DFSRecursion(v, level + 1);

                        VertexVisitingFinish(v,Color.GREEN);
                        changeEdgeColor(e,Color.GREEN);
                    }
                }
            }
//        str = "";
//        for (int i = 0; i < level; i++) {
//            str = str.concat("\t");
//        }
//        str = str.concat("DFS(" + source.name + ") Exit\n");
//        final String str1 = str;
//        fd = new FadeTransition(Duration.millis(10), textFlow);
//        fd.setOnFinished(e -> {
//            textFlow.appendText(str1);
//        });
//        fd.onFinishedProperty();
//        animations.getChildren().add(fd);

        }

        public void Dijkstra(Vertex source) {
            //<editor-fold defaultstate="collapsed" desc="Animation Control">
            for (VertexCircle n : circles) {
                distances.add(n.distance);
                n.distance.setLayoutX(n.point.x + 20);
                n.distance.setLayoutY(n.point.y);
                canvasGroup.getChildren().add(n.distance);
            }
            sourceText.setLayoutX(source.circle.point.x + 20);
            sourceText.setLayoutY(source.circle.point.y + 10);
            canvasGroup.getChildren().add(sourceText);
            source.circle.distance.setText("Dist. : " + 0);
            //</editor-fold>
            source.minDistance = 0;
            list.push(source);
            while (!list.isEmpty()) {
                Vertex u = list.removeLast();
                System.out.println(u.name);
                //<editor-fold defaultstate="collapsed" desc="Animation Control">
                changeVertexColor(u, Color.DARKVIOLET);
//                String str = "";
//                str = str.concat("Popped : Node(" + u.name + "), Current Distance: " + u.minDistance + "\n");
//                final String str2 = str;
//                FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
//                fd.setOnFinished(e -> {
//                    textFlow.appendText(str2);
//                });
//                fd.onFinishedProperty();
//                animations.getChildren().add(fd);
                //</editor-fold>
                System.out.println(u.name);
                for (Edge e : u.adjacents) {
                    if (e != null) {
                        Vertex v = e.target;
                        System.out.println("HERE " + v.name);
                        if (u.minDistance + e.weight < v.minDistance) {
                            list.remove(v);
                            v.minDistance = u.minDistance + e.weight;
                            v.previous = u;
                            list.push(v);
                            //<editor-fold defaultstate="collapsed" desc="Node visiting animation">
                            changeEdgeColor(e,Color.FORESTGREEN);
                            //</editor-fold>
                            VertexVisitingFinish(v,Color.BLUE);

//                            str = "\t";
//                            str = str.concat("Pushing : Node(" + v.name + "), (" + u.name + "--" + v.name + ") Distance : " + v.minDistance + "\n");
//                            final String str1 = str;
//                            FadeTransition fd2 = new FadeTransition(Duration.millis(10), textFlow);
//                            fd2.setOnFinished(ev -> {
//                                textFlow.appendText(str1);
//                            });
//                            fd2.onFinishedProperty();
//                            animations.getChildren().add(fd2);
                            //</editor-fold>
                        }
                    }
                }
                changeVertexColor(u,Color.GREEN);
            }
        }

        public void finishAnimation(Vertex source) {
            animations.setOnFinished(ev -> {
                for (VertexCircle n : circles) {
                    FillTransition ft1 = new FillTransition(Duration.millis(time), n);
                    ft1.setToValue(Color.BLACK);
                    ft1.play();
                }
                if (directed) {
                    for (Shape n : edges) {
                        n.setFill(Color.BLACK);
                    }
                } else if (undirected) {
                    for (Shape n : edges) {
                        n.setStroke(Color.BLACK);
                    }
                }
                FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
                ft1.setToValue(Color.RED);
                ft1.play();
                Image image1 = new javafx.scene.image.Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
                playPauseImage.setImage(image1);
                paused = true;
                playing = false;
                textFlow.appendText("---Finished--\n");
            });
            animations.onFinishedProperty();
            animations.play();
            playing = true;
            paused = false;
        }

        public void changeEdgeColor( Edge e, Color color) {
            if (undirected) {
                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
                ftEdge.setToValue(color);
                animations.getChildren().add(ftEdge);
            } else if (directed) {
                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
                ftEdge.setToValue(color);
                animations.getChildren().add(ftEdge);
            }

        }

        public void changeVertexColor( Vertex v, Color color) {
            FillTransition ftVertex = new FillTransition(Duration.millis(time),v.circle);
            ftVertex.setToValue(color);
            animations.getChildren().add(ftVertex);
        }

        public void VertexVisitingFinish(Vertex v, Color color) {
            FillTransition ft = new FillTransition(Duration.millis(time), v.circle);
            ft.setToValue(color);
            ft.setOnFinished(ev -> {
                v.circle.distance.setText("Dist. : " + v.minDistance);
            });
            ft.onFinishedProperty();
            animations.getChildren().add(ft);
        }

        public List<Vertex> getShortestPathTo(Vertex target) {
            List<Vertex> shortestPath = new ArrayList<>();
            for (Vertex i = target; i != null; i = i.previous) {
                shortestPath.add(i);
            }
            Collections.reverse(shortestPath);
            return shortestPath;
        }
    }

}
