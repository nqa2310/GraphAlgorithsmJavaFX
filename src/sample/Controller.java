package sample;

import com.jfoenix.controls.*;
import com.jfoenix.converters.base.NodeConverter;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane stackRoot;
    @FXML
    private JFXButton canvasBackButton;
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
    private ToggleGroup algoToggleGroup;
    @FXML
    private Pane viewer;
    @FXML
    private Group canvasGroup;
    @FXML
    private Line edgeLine;
    @FXML
    private Label sourceText = new Label("Source"), weight;
    @FXML
    private Pane border;
    @FXML
    private JFXNodesList nodeList;
    @FXML
    private JFXSlider slider = new JFXSlider();
    @FXML
    private ImageView playPauseImage;
    @FXML
    private JFXRadioButton weightedButton;
    @FXML
    private JFXRadioButton unweightedButton;


    int nVertex = 0;
    int time = 500;

    VertexFX selectedVertex = null;

    List<VertexFX> circles = new ArrayList<>();
    List<Edge> realEdges = new ArrayList<>();
    List<Shape> edges = new ArrayList<>();
    List<Label> distances = new ArrayList<>();

    boolean addVertex = true, addEdge = false, type = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;

    private boolean weighted = false,unweighted = true,bfs = true, dfs = true, dijkstra = true;

    Algorithm algo = new Algorithm();

    public static TextArea textFlow = new TextArea();

    public SequentialTransition st;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("In intit");
        weightedButton.setUnSelectedColor(Color.WHITE);
        unweightedButton.setUnSelectedColor(Color.WHITE);
//        hiddenPane.setContent(canvasGroup);
//        anchorRoot.setManaged(false);

        ResetHandle(null);
//        viewer.prefHeightProperty().bind(border.heightProperty());
//        viewer.prefWidthProperty().bind(border.widthProperty());
//        AddVertexHandle(null);
        unweightedButton.setSelected(true);
        addEdgeButton.setDisable(true);
        addVertexButton.setDisable(true);
        clearButton.setDisable(true);
        bfsButton.setDisable(true);
        dfsButton.setDisable(true);
        dijkstraButton.setDisable(true);
        Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
        playPauseImage.setImage(image);

//        if (directed) {
//            articulationPointButton.setDisable(true);
//        }

//        //Setup Slider
//        slider = new JFXSlider(10, 1000, 500);
//        slider.setPrefWidth(150);
//        slider.setPrefHeight(80);
//        slider.setSnapToTicks(true);
//        slider.setMinorTickCount(100);
//        slider.setIndicatorPosition(JFXSlider.IndicatorPosition.RIGHT);
//        slider.setBlendMode(BlendMode.MULTIPLY);
//        slider.setCursor(javafx.scene.Cursor.CLOSED_HAND);
//        nodeList.addAnimatedNode(slider);
//        nodeList.setSpacing(50D);
//        nodeList.setRotate(270D);
//        slider.toFront();
//        nodeList.toFront();
//        slider.valueProperty().addListener(this);

//        hiddenRoot.setPrefWidth(220);
//        hiddenRoot.setPrefHeight(581);
//
//        hiddenRoot.setCursor(Cursor.DEFAULT);

//        //Set Label "Detail"
//        Label detailLabel = new Label("Detail");
//        detailLabel.setPrefSize(hiddenRoot.getPrefWidth() - 20, 38);
//        detailLabel.setAlignment(Pos.CENTER);
//        detailLabel.setFont(new Font("Roboto", 20));
//        detailLabel.setPadding(new javafx.geometry.Insets(7, 40, 3, -10));
//        detailLabel.setStyle("-fx-background-color: #dcdde1;");
//        detailLabel.setLayoutX(35);
//
//        //Set TextFlow pane properties
//        textFlow.setPrefSize(hiddenRoot.getPrefWidth(), hiddenRoot.getPrefHeight() - 2);
////        textFlow.prefHeightProperty().bind(hiddenRoot.heightProperty());
//        textFlow.setStyle("-fx-background-color: #dfe6e9;");
//        textFlow.setLayoutY(39);
//        textContainer.setLayoutY(textFlow.getLayoutY());
//        textFlow.setPadding(new Insets(5, 0, 0, 5));
//        textFlow.setEditable(false);
//        textContainer.setContent(textFlow);

//        //Set Pin/Unpin Button
//        JFXButton pinUnpin = new JFXButton();
//        pinUnpin.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//
//        ImageView imgPin = new ImageView(new javafx.scene.image.Image(getClass().getResourceAsStream("/pinned.png")));
//        imgPin.setFitHeight(20);
//        imgPin.setFitWidth(20);
//        ImageView imgUnpin = new ImageView(new Image(getClass().getResourceAsStream("/unpinned.png")));
//        imgUnpin.setFitHeight(20);
//        imgUnpin.setFitWidth(20);
//        pinUnpin.setGraphic(imgPin);
//
//        pinUnpin.setPrefSize(20, 39);
//        pinUnpin.setButtonType(JFXButton.ButtonType.FLAT);
//        pinUnpin.setStyle("-fx-background-color: #dcdde1;");
//        pinUnpin.setOnMouseClicked(e -> {
//            if (pinned) {
//                pinUnpin.setGraphic(imgPin);
//                hiddenPane.setPinnedSide(null);
//                pinned = false;
//            } else {
//                pinUnpin.setGraphic(imgUnpin);
//                hiddenPane.setPinnedSide(Side.RIGHT);
//                pinned = true;
//            }
//        });
//
//        //Add Label and TextFlow to hiddenPane
//        hiddenRoot.getChildren().addAll(pinUnpin, detailLabel, textContainer);
//        hiddenPane.setRight(hiddenRoot);
//        hiddenRoot.setOnMouseEntered(e -> {
//            hiddenPane.setPinnedSide(Side.RIGHT);
//            openHidden.setVisible(false);
//            e.consume();
//        });
//        hiddenRoot.setOnMouseExited(e -> {
//            if (!pinned) {
//                hiddenPane.setPinnedSide(null);
//                openHidden.setVisible(true);
//            }
//            e.consume();
//        });
//        hiddenPane.setTriggerDistance(60);

    }

    /**
     * Checks if an edge already exists between two nodes before adding a new
     * edge.
     *
     * @param u = selected node
     * @param v = second selected node
     * @return True if edge already exists. Else false.
     */
    boolean edgeExists(VertexFX u, VertexFX v) {
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
//                    if (menuBool == true) {
//                        System.out.println("here" + ev.getEventType());
//                        menuBool = false;
//                        return;
//                    }
                    nVertex++;
                    VertexFX circle = new VertexFX(ev.getX(), ev.getY(), 1.2, String.valueOf(nVertex));
                    canvasGroup.getChildren().add(circle);

                    circle.setOnMousePressed(mouseHandler);
                    circle.setOnMouseReleased(mouseHandler);
                    circle.setOnMouseDragged(mouseHandler);
                    circle.setOnMouseExited(mouseHandler);
                    circle.setOnMouseEntered(mouseHandler);

                    ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
                    tr.setByX(13f);
                    tr.setByY(13f);
                    tr.setInterpolator(Interpolator.EASE_OUT);
                    tr.play();

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
            VertexFX circle = (VertexFX) mouseEvent.getSource();
            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseButton.PRIMARY) {
                if (!circle.isSelected) {
                    if (selectedVertex != null) {
                        if (addEdge && !edgeExists(selectedVertex, circle)) {
                            weight = new Label();
                            System.out.println("Adding Edge");
                            //Adds the edge between two selected nodes
                            edgeLine = new Line(selectedVertex.point.x, selectedVertex.point.y, circle.point.x, circle.point.y);
                            edgeLine.setStrokeWidth(2);
                            canvasGroup.getChildren().add(edgeLine);
                            edgeLine.setId("line");

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
//                            javafx.scene.shape.Shape line_arrow = null;
//                            Edge temp = null;
//                            if (undirected) {
//                                temp = new Edge(selectedNode.node, circle.node, Integer.valueOf(weight.getText()), edgeLine, weight);
//                                if (weighted) {
//                                    mstEdges.add(temp);
//                                }
//
                                selectedVertex.vertex.adjacents.add(new Edge(selectedVertex.vertex, circle.vertex, Double.valueOf(weight.getText()), edgeLine, weight));
                                circle.vertex.adjacents.add(new Edge(circle.vertex, selectedVertex.vertex, Double.valueOf(weight.getText()), edgeLine, weight));
                                edges.add(edgeLine);
                                realEdges.add(selectedVertex.vertex.adjacents.get(selectedVertex.vertex.adjacents.size() - 1));
                                realEdges.add(circle.vertex.adjacents.get(circle.vertex.adjacents.size() - 1));
//                                line_arrow = edgeLine;
//
//                            } else if (directed) {
//                                temp = new Edge(selectedNode.node, circle.node, Double.valueOf(weight.getText()), arrow, weight);
//                                selectedNode.node.adjacents.add(temp);
////                                circle.node.revAdjacents.add(new Edge(circle.node, selectedNode.node, Integer.valueOf(weight.getText()), arrow));
//                                edges.add(arrow);
//                                line_arrow = arrow;
//                                realEdges.add(temp);
//                            }
//
//                            RightClickMenu rt = new RightClickMenu(temp);
//                            ContextMenu menu = rt.getMenu();
//                            if (weighted) {
//                                rt.changeId.setText("Change Weight");
//                            } else if (unweighted) {
//                                rt.changeId.setDisable(true);
//                            }
//                            final Shape la = line_arrow;
//                            line_arrow.setOnContextMenuRequested(e -> {
//                                System.out.println("In Edge Menu :" + menuBool);
//
//                                if (menuBool == true) {
//                                    globalMenu.hide();
//                                    menuBool = false;
//                                }
//                                if (addEdge || addNode) {
//                                    globalMenu = menu;
//                                    menu.show(la, e.getScreenX(), e.getScreenY());
//                                    menuBool = true;
//                                }
//                            });
//                            menu.setOnAction(e -> {
//                                menuBool = false;
//                            });
                        }
                        if (addVertex || addEdge) {
                            selectedVertex.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), selectedVertex, Color.GREEN, javafx.scene.paint.Color.BLACK);
                            ft1.play();
                        }
                        selectedVertex = null;
                        return;
                    }
                    if(!calculate) {
                        FillTransition ft = new FillTransition(Duration.millis(300), circle, javafx.scene.paint.Color.BLACK, Color.GREEN);
                        ft.play();
                        circle.isSelected = true;
                        selectedVertex = circle;
                    }

                    // WHAT TO DO WHEN SELECTED ON ACTIVE ALGORITHM
                    if (calculate && !calculated) {
                        if (bfs) {
                            BFS(circle.vertex);
                        }
                        else if (dfs) {
                            DFS(circle.vertex);
                        }
                        else if (dijkstra) {
                            dijkstra(circle.vertex);
                        }
//
                        calculated = true;
                    }
//                    else if (calculate && calculated && !articulationPoint & !mst && !topSortBool) {
//
//                        for (VertexFX n : circles) {
//                            n.isSelected = false;
//                            FillTransition ft1 = new FillTransition(Duration.millis(300), n);
//                            ft1.setToValue(javafx.scene.paint.Color.BLACK);
//                            ft1.play();
//                        }
//                        List<Node> path = algo.getShortestPathTo(circle.vertex);
//                        for (Node n : path) {
//                            FillTransition ft1 = new FillTransition(Duration.millis(300), n.circle);
//                            ft1.setToValue(javafx.scene.paint.Color.BLUE);
//                            ft1.play();
//                        }
//                    }
                } else {
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.GREEN, Color.BLACK);
                    ft1.play();
                    selectedVertex = null;
                }

            }
        }

    };

    private void DFS(Vertex source) {
        Image image = new Image(getClass().getResourceAsStream("pause_black_48x48.png"));
        playPauseImage.setImage(image);
        st = new SequentialTransition();
        source.minDistance = 0;
        source.visited = true;
        LinkedList<Vertex> s = new LinkedList<>();
        s.push(source);
        DFSRecursion(source,0);
        //<editor-fold defaultstate="collapsed" desc="Animation after algorithm is finished">
        st.setOnFinished(ev -> {
            for (VertexFX n : circles) {
                FillTransition ft1 = new FillTransition(Duration.millis(time), n);
                ft1.setToValue(Color.BLACK);
                ft1.play();
            }
//            if (directed) {
//                for (Shape n : edges) {
//                    n.setFill(Color.BLACK);
//                }
//            } else if (undirected) {
                for (Shape n : edges) {
                    n.setStroke(Color.BLACK);
                }
//            }
            FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
            ft1.setToValue(Color.RED);
            ft1.play();
            Image im = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
            playPauseImage.setImage(im);
            paused = true;
            playing = false;
            Image image1 = new javafx.scene.image.Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
            playPauseImage.setImage(image1);
//            textFlow.appendText("---Finished--\n");
        });
        st.onFinishedProperty();
        st.play();
        playing = true;
        paused = false;
        //</editor-fold>

    }

    private void DFSRecursion(Vertex source, int level) {
        //<editor-fold defaultstate="collapsed" desc="Animation Control">
        FillTransition ft = new FillTransition(Duration.millis(time), source.circle);
        if (source.circle.getFill() == Color.BLACK) {
            ft.setToValue(Color.FORESTGREEN);
        }
        st.getChildren().add(ft);
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
//        st.getChildren().add(fd);
        //</editor-fold>
        for (Edge e : source.adjacents) {
            if (e != null) {
                Vertex v = e.target;
                if (!v.visited) {
                    v.minDistance = source.minDistance + 1;
                    v.visited = true;
                    v.previous = source;
//                        v.circle.distance.setText("Dist. : " + v.minDistance);
                    //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                    if (undirected) {
                        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
                        ftEdge.setToValue(Color.FORESTGREEN);
                        st.getChildren().add(ftEdge);
//                    } else if (directed) {
//                        FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                        ftEdge.setToValue(Color.FORESTGREEN);
//                        st.getChildren().add(ftEdge);
//                    }
                    //</editor-fold>
                    DFSRecursion(v, level + 1);
                    //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                    //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                    if (undirected) {

//                    } else if (directed) {
//                        FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                        ftEdge.setToValue(Color.BLUEVIOLET);
//                        st.getChildren().add(ftEdge);
//                    }
                    //</editor-fold>
                    FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
                    ft1.setToValue(Color.BLUEVIOLET);
                    ft1.onFinishedProperty();
                    ft1.setOnFinished(ev -> {
                        v.circle.distance.setText("Dist. : " + v.minDistance);
                    });
                    st.getChildren().add(ft1);
                    StrokeTransition ftEdge0 = new StrokeTransition(Duration.millis(time), e.line);
                    ftEdge0.setToValue(Color.BLUEVIOLET);
                    st.getChildren().add(ftEdge0);
                    //</editor-fold>
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
//        st.getChildren().add(fd);

    }

    private void BFS(Vertex source) {
        Image image = new Image(getClass().getResourceAsStream("pause_black_48x48.png"));
        playPauseImage.setImage(image);
        st = new SequentialTransition();
        FillTransition ftsource = new FillTransition(Duration.millis(time),source.circle);
        if (source.circle.getFill() == Color.BLACK) {
            ftsource.setToValue(Color.GREEN);
        }
        st.getChildren().add(ftsource);
        source.minDistance = 0;
        source.visited = true;
        LinkedList<Vertex> q = new LinkedList<>();
        q.push(source);
        while (!q.isEmpty()) {
            Vertex u = q.removeLast();
            FillTransition ft = new FillTransition(Duration.millis(time),u.circle);
            if (u.circle.getFill() == Color.BLACK) {
                ft.setToValue(Color.CHOCOLATE);
            }
            st.getChildren().add(ft);
            System.out.println(u.name);
            for (Edge e: u.adjacents) {
                if (e != null) {
                    Vertex v = e.target;
                    if (!v.visited) {
                        v.minDistance = u.minDistance + 1;
                        v.visited = true;
                        q.push(v);
                        v.previous = u;

                        //change Edge color
                        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
                        ftEdge.setToValue(Color.FORESTGREEN);
                        st.getChildren().add(ftEdge);
                        //Vertex visiting animation
                        FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
                        ft1.setToValue(Color.FORESTGREEN);
                        ft1.setOnFinished(ev -> {
                            v.circle.distance.setText("Dist. : " + v.minDistance);
                        });
                        ft1.onFinishedProperty();
                        st.getChildren().add(ft1);
                    }
                }
            }
            // Vertex finishing visit animation
            FillTransition ft2 = new FillTransition(Duration.millis(time), u.circle);
            ft2.setToValue(Color.BLUEVIOLET);
            st.getChildren().add(ft2);
        }

        st.setOnFinished(ev -> {
                    for (VertexFX n: circles) {
                        FillTransition ft1 = new FillTransition(Duration.millis(time),n);
                        ft1.setToValue(Color.BLACK);
                        ft1.play();
                    }
//            if (directed) {
//                for (Shape n : edges) {
//                    n.setFill(Color.BLACK);
//                }
//            } else if (undirected) {
                    for (Shape n : edges) {
                        n.setStroke(Color.BLACK);
                    }
//            }
                    FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
                    ft1.setToValue(Color.RED);
                    ft1.play();
                    Image image1 = new javafx.scene.image.Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
                    playPauseImage.setImage(image1);
                    paused = true;
                    playing = false;
                });
        st.onFinishedProperty();
        st.play();
        playing = true;
        paused = false;

    }

    private void dijkstra(Vertex source) {
        Image image = new Image(getClass().getResourceAsStream("pause_black_48x48.png"));
        playPauseImage.setImage(image);
        //<editor-fold defaultstate="collapsed" desc="Animation Control">
        for (VertexFX n : circles) {
            distances.add(n.distance);
            n.distance.setLayoutX(n.point.x + 20);
            n.distance.setLayoutY(n.point.y);
            canvasGroup.getChildren().add(n.distance);
        }
        sourceText.setLayoutX(source.circle.point.x + 20);
        sourceText.setLayoutY(source.circle.point.y + 10);
        canvasGroup.getChildren().add(sourceText);
        st = new SequentialTransition();
        source.circle.distance.setText("Dist. : " + 0);
        //</editor-fold>

        source.minDistance = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(source);
        while (!pq.isEmpty()) {
            Vertex u = pq.poll();
            System.out.println(u.name);
            //<editor-fold defaultstate="collapsed" desc="Animation Control">
            FillTransition ft = new FillTransition(Duration.millis(time), u.circle);
            ft.setToValue(Color.CHOCOLATE);
            st.getChildren().add(ft);
            String str = "";
            str = str.concat("Popped : Node(" + u.name + "), Current Distance: " + u.minDistance + "\n");
            final String str2 = str;
            FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
            fd.setOnFinished(e -> {
                textFlow.appendText(str2);
            });
            fd.onFinishedProperty();
            st.getChildren().add(fd);
            //</editor-fold>
            System.out.println(u.name);
            for (Edge e : u.adjacents) {
                if (e != null) {
                    Vertex v = e.target;
                    System.out.println("HERE " + v.name);
                    if (u.minDistance + e.weight < v.minDistance) {
                        pq.remove(v);
                        v.minDistance = u.minDistance + e.weight;
                        v.previous = u;
                        pq.add(v);
                        //<editor-fold defaultstate="collapsed" desc="Node visiting animation">
                        //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                        if (undirected) {
                            StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
                            ftEdge.setToValue(Color.FORESTGREEN);
                            st.getChildren().add(ftEdge);
//                        } else if (directed) {
//                            FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                            ftEdge.setToValue(Color.FORESTGREEN);
//                            st.getChildren().add(ftEdge);
//                        }
                        //</editor-fold>
                        FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
                        ft1.setToValue(Color.FORESTGREEN);
                        ft1.setOnFinished(ev -> {
                            v.circle.distance.setText("Dist. : " + v.minDistance);
                        });
                        ft1.onFinishedProperty();
                        st.getChildren().add(ft1);

                        str = "\t";
                        str = str.concat("Pushing : Node(" + v.name + "), (" + u.name + "--" + v.name + ") Distance : " + v.minDistance + "\n");
                        final String str1 = str;
                        FadeTransition fd2 = new FadeTransition(Duration.millis(10), textFlow);
                        fd2.setOnFinished(ev -> {
                            textFlow.appendText(str1);
                        });
                        fd2.onFinishedProperty();
                        st.getChildren().add(fd2);
                        //</editor-fold>
                    }
                }
            }
            //<editor-fold defaultstate="collapsed" desc="Animation Control">
            FillTransition ft2 = new FillTransition(Duration.millis(time), u.circle);
            ft2.setToValue(Color.BLUEVIOLET);
            st.getChildren().add(ft2);
            //</editor-fold>
        }

        //<editor-fold defaultstate="collapsed" desc="Animation Control">
        st.setOnFinished(ev -> {
            for (VertexFX n : circles) {
                FillTransition ft1 = new FillTransition(Duration.millis(time), n);
                ft1.setToValue(Color.BLACK);
                ft1.play();
            }
//            if (directed) {
                for (Shape n : edges) {
                    n.setFill(Color.BLACK);
                }
//            } else if (undirected) {
//                for (Shape n : edges) {
//                    n.setStroke(Color.BLACK);
//                }
//            }
            FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
            ft1.setToValue(Color.RED);
            ft1.play();
            Image image1 = new javafx.scene.image.Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
            playPauseImage.setImage(image1);
            paused = true;
            playing = false;
            textFlow.appendText("---Finished--\n");
        });
        st.onFinishedProperty();
        st.play();
        playing = true;
        paused = false;
        //</editor-fold>

    }



    /**
     * Event handler for the Play/Pause button.
     *
     * @param event
     */
    @FXML
    public void PlayPauseHandle(ActionEvent event) {
        try{
            if (playing && st != null && st.getStatus() == Animation.Status.RUNNING) {
                Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
                playPauseImage.setImage(image);
                System.out.println("PAUSED");
                st.pause();
                paused = true;
                playing = false;
                return;
            } else if (paused && st != null) {
                Image image = new Image(getClass().getResourceAsStream("pause_black_48x48.png"));
                playPauseImage.setImage(image);
                if(st.getStatus() == Animation.Status.PAUSED)
                    st.play();
                else if(st.getStatus() == Animation.Status.STOPPED)
                    st.playFromStart();
                playing = true;
                paused = false;
                return;
            }
        } catch(Exception e){
            System.out.println("Error while play/pause: " + e);
            ClearHandle(null);
        }
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
        canvasGroup.getChildren().clear();
        canvasGroup.getChildren().addAll(viewer);
        selectedVertex = null;
        circles = new ArrayList<VertexFX>();
//        distances = new ArrayList<Label>();
//        visitTime = new ArrayList<Label>();
//        lowTime = new ArrayList<Label>();
        addVertex = true;
        addEdge = false;
        calculate = false;
        calculated = false;
        addVertexButton.setSelected(true);
        addEdgeButton.setSelected(false);
        addEdgeButton.setDisable(true);
        addVertexButton.setDisable(false);
        weightedButton.setDisable(false);
        unweightedButton.setDisable(false);
        clearButton.setDisable(true);
//        algo = new Algorithm();
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
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
        if(st != null && st.getStatus() != Animation.Status.STOPPED)
            st.stop();
        if(st != null) st.getChildren().clear();
//        menuBool = false;
        selectedVertex = null;
        calculated = false;
        System.out.println("CLEAR:" + circles.size());
        for (VertexFX n : circles) {
            n.isSelected = false;
            n.vertex.visited = false;
            n.vertex.previous = null;
            n.vertex.minDistance = Double.POSITIVE_INFINITY;
            n.vertex.DAGColor = 0;

            FillTransition ft1 = new FillTransition(Duration.millis(300), n);
            ft1.setToValue(Color.BLACK);
            ft1.play();
        }
        for (Shape x : edges) {
//            if (undirected) {
                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), x);
                ftEdge.setToValue(Color.BLACK);
                ftEdge.play();
//            } else if (directed) {
//                FillTransition ftEdge = new FillTransition(Duration.millis(time), x);
//                ftEdge.setToValue(Color.BLACK);
//                ftEdge.play();
//            }
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
        javafx.scene.image.Image image = new Image(getClass().getResourceAsStream("play_arrow_black_48x48.png"));
        playPauseImage.setImage(image);
//
//        distances = new ArrayList<>();
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

    /**
     * Shape class for the nodes.
     */
    public class VertexFX extends Circle {

        Vertex vertex;
        Point point;
        Label distance = new Label("Dist. : INFINITY");
        Label visitTime = new Label("Visit : 0");
        Label lowTime = new Label("Low : 0");
        Label id;
        boolean isSelected = false;

        public VertexFX(double x, double y, double rad, String name) {
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

//            RightClickMenu rt = new RightClickMenu(this);
//            ContextMenu menu = rt.getMenu();
//            globalMenu = menu;
//            this.setOnContextMenuRequested(e -> {
//                if (addEdge || addNode) {
//                    menu.show(this, e.getScreenX(), e.getScreenY());
//                    menuBool = true;
//                }
//            });
//            menu.setOnAction(e -> {
//                menuBool = false;
//            });
//
            circles.add(this);
            System.out.println("ADDED: " + circles.size());
        }
    }

    public class Algorithm {

//        //<editor-fold defaultstate="collapsed" desc="Dijkstra">
//        public void newDijkstra(Node source) {
//            new Dijkstra(source);
//        }
//
//        class Dijkstra {
//
//            Dijkstra(Node source) {
//
//                //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                for (NodeFX n : circles) {
//                    distances.add(n.distance);
//                    n.distance.setLayoutX(n.point.x + 20);
//                    n.distance.setLayoutY(n.point.y);
//                    canvasGroup.getChildren().add(n.distance);
//                }
//                sourceText.setLayoutX(source.circle.point.x + 20);
//                sourceText.setLayoutY(source.circle.point.y + 10);
//                canvasGroup.getChildren().add(sourceText);
//                SequentialTransition st = new SequentialTransition();
//                source.circle.distance.setText("Dist. : " + 0);
//                //</editor-fold>
//
//                source.minDistance = 0;
//                PriorityQueue<Node> pq = new PriorityQueue<Node>();
//                pq.add(source);
//                while (!pq.isEmpty()) {
//                    Node u = pq.poll();
//                    System.out.println(u.name);
//                    //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                    FillTransition ft = new FillTransition(Duration.millis(time), u.circle);
//                    ft.setToValue(Color.CHOCOLATE);
//                    st.getChildren().add(ft);
//                    String str = "";
//                    str = str.concat("Popped : Node(" + u.name + "), Current Distance: " + u.minDistance + "\n");
//                    final String str2 = str;
//                    FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
//                    fd.setOnFinished(e -> {
//                        textFlow.appendText(str2);
//                    });
//                    fd.onFinishedProperty();
//                    st.getChildren().add(fd);
//                    //</editor-fold>
//                    System.out.println(u.name);
//                    for (Edge e : u.adjacents) {
//                        if (e != null) {
//                            Node v = e.target;
//                            System.out.println("HERE " + v.name);
//                            if (u.minDistance + e.weight < v.minDistance) {
//                                pq.remove(v);
//                                v.minDistance = u.minDistance + e.weight;
//                                v.previous = u;
//                                pq.add(v);
//                                //<editor-fold defaultstate="collapsed" desc="Node visiting animation">
//                                //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                                if (undirected) {
//                                    StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                    ftEdge.setToValue(Color.FORESTGREEN);
//                                    st.getChildren().add(ftEdge);
//                                } else if (directed) {
//                                    FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                    ftEdge.setToValue(Color.FORESTGREEN);
//                                    st.getChildren().add(ftEdge);
//                                }
//                                //</editor-fold>
//                                FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
//                                ft1.setToValue(Color.FORESTGREEN);
//                                ft1.setOnFinished(ev -> {
//                                    v.circle.distance.setText("Dist. : " + v.minDistance);
//                                });
//                                ft1.onFinishedProperty();
//                                st.getChildren().add(ft1);
//
//                                str = "\t";
//                                str = str.concat("Pushing : Node(" + v.name + "), (" + u.name + "--" + v.name + ") Distance : " + v.minDistance + "\n");
//                                final String str1 = str;
//                                FadeTransition fd2 = new FadeTransition(Duration.millis(10), textFlow);
//                                fd2.setOnFinished(ev -> {
//                                    textFlow.appendText(str1);
//                                });
//                                fd2.onFinishedProperty();
//                                st.getChildren().add(fd2);
//                                //</editor-fold>
//                            }
//                        }
//                    }
//                    //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                    FillTransition ft2 = new FillTransition(Duration.millis(time), u.circle);
//                    ft2.setToValue(Color.BLUEVIOLET);
//                    st.getChildren().add(ft2);
//                    //</editor-fold>
//                }
//
//                //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                st.setOnFinished(ev -> {
//                    for (NodeFX n : circles) {
//                        FillTransition ft1 = new FillTransition(Duration.millis(time), n);
//                        ft1.setToValue(Color.BLACK);
//                        ft1.play();
//                    }
//                    if (directed) {
//                        for (Shape n : edges) {
//                            n.setFill(Color.BLACK);
//                        }
//                    } else if (undirected) {
//                        for (Shape n : edges) {
//                            n.setStroke(Color.BLACK);
//                        }
//                    }
//                    FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
//                    ft1.setToValue(Color.RED);
//                    ft1.play();
//                    Image image = new Image(getClass().getResourceAsStream("/play_arrow_black_48x48.png"));
//                    playPauseImage.setImage(image);
//                    paused = true;
//                    playing = false;
//                    textFlow.appendText("---Finished--\n");
//                });
//                st.onFinishedProperty();
//                st.play();
//                playing = true;
//                paused = false;
//                //</editor-fold>
//            }
//        }
//        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="BFS">
        public void newBFS(Vertex source) {
            new BFS(source);
        }

        class BFS {
            BFS(Vertex source) {
                st = new SequentialTransition();
                source.minDistance = 0;
                source.visited = true;
                LinkedList<Vertex> q = new LinkedList<>();
                q.push(source);
                while (!q.isEmpty()) {
                    Vertex u = q.removeLast();
                    FillTransition ft = new FillTransition(Duration.millis(time),u.circle);
                    if (u.circle.getFill() == Color.BLACK) {
                        ft.setToValue(Color.CHOCOLATE);
                    }
                    st.getChildren().add(ft);

                    System.out.println(u.name);
                    for (Edge e: u.adjacents) {
                        if (e != null) {
                            Vertex v = e.target;
                            if (!v.visited) {
                                v.minDistance = u.minDistance + 1;
                                v.visited = true;
                                q.push(v);
                                v.previous = u;

                                //Vertex visiting animation
                                //change Edge color
                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
                                ftEdge.setToValue(Color.FORESTGREEN);
                                st.getChildren().add(ftEdge);
                                FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
                                ft1.setToValue(Color.FORESTGREEN);
                                ft1.setOnFinished(ev -> {
                                    v.circle.distance.setText("Dist. : " + v.minDistance);
                                });
                                ft1.onFinishedProperty();
                                st.getChildren().add(ft1);
                            }
                        }
                    }
                    FillTransition ft2 = new FillTransition(Duration.millis(time), u.circle);
                    ft2.setToValue(Color.BLUEVIOLET);
                    st.getChildren().add(ft2);
                }

                st.setOnFinished(ev -> {
                    for (VertexFX n: circles) {
                        FillTransition ft1 = new FillTransition(Duration.millis(time),n);
                        ft1.setToValue(Color.BLACK);
                        ft1.play();
                    }
//            if (directed) {
//                for (Shape n : edges) {
//                    n.setFill(Color.BLACK);
//                }
//            } else if (undirected) {
                    for (Shape n : edges) {
                        n.setStroke(Color.BLACK);
                    }
//            }
                    FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
                    ft1.setToValue(Color.RED);
                    ft1.play();
                });
                st.onFinishedProperty();
                st.play();
                playing = true;
                paused = false;

            }
        }
        //</editor-fold>

//        //<editor-fold defaultstate="collapsed" desc="TopSort">
//        public void newTopSort() {
//            new TopSort();
//        }
//
//        class TopSort {
//
//            String reverse = "";
//            List<String> topSort = new ArrayList<>();
//            boolean cycleFound = false;
//
//            TopSort() {
//
//                //<editor-fold defaultstate="collapsed" desc="Animation Setup Distances">
//                st = new SequentialTransition();
//                //</editor-fold>
//
//                cycleFound = false;
//                for (NodeFX n : circles) {
//                    if (n.node.DAGColor == 0) {
//                        cycleExists(n.node, 0);
//                    }
//                }
//                if (cycleFound == false) {
//                    for (NodeFX source : circles) {
//                        if (source.node.visited == false) {
//                            topsortRecursion(source.node, 0);
//                        }
//                    }
//
//                    System.out.println("Hello World " + topSort);
//                    Collections.reverse(topSort);
//                    for (String s : topSort) {
//                        reverse += " -> " + s;
//                    }
//                    reverse = reverse.replaceFirst(" -> ", "");
//                    System.out.println(reverse);
//
//                    //<editor-fold defaultstate="collapsed" desc="Animation after algorithm is finished">
//                    st.setOnFinished(ev -> {
//                        for (NodeFX n : circles) {
//                            FillTransition ft1 = new FillTransition(Duration.millis(time), n);
//                            ft1.setToValue(Color.BLACK);
//                            ft1.play();
//                        }
//                        if (directed) {
//                            for (Shape n : edges) {
//                                n.setFill(Color.BLACK);
//                            }
//                        } else if (undirected) {
//                            for (Shape n : edges) {
//                                n.setStroke(Color.BLACK);
//                            }
//                        }
//
//                        Image image = new Image(getClass().getResourceAsStream("/play_arrow_black_48x48.png"));
//                        playPauseImage.setImage(image);
//                        paused = true;
//                        playing = false;
//                        textFlow.appendText("---Finished--\n\n");
//                        textFlow.appendText("Top Sort: " + reverse + "\n");
//
//                    });
//                    st.onFinishedProperty();
//                    st.play();
//
//                    playing = true;
//                    paused = false;
//                    //</editor-fold>
//                } else {
//                    System.out.println("Cycle");
//                    BoxBlur blur = new BoxBlur(3, 3, 3);
//
//                    JFXDialogLayout dialogLayout = new JFXDialogLayout();
//                    dialogLayout.setStyle("-fx-background-color:#dfe6e9");
//                    JFXDialog dialog = new JFXDialog(stackRoot, dialogLayout, JFXDialog.DialogTransition.TOP);
//
//                    JFXButton button = new JFXButton("OK");
//                    button.setPrefSize(50, 30);
//                    button.getStyleClass().add("dialog-button");
//                    button.setButtonType(JFXButton.ButtonType.RAISED);
//                    dialogLayout.setActions(button);
//                    Label message = new Label("     Cycle Detected!\n"
//                            + "Cannot run TopSort on a  Directed Cyclic Graph!");
//                    message.setId("message");
//                    dialogLayout.setBody(message);
//                    button.setOnAction(e -> {
//                        dialog.close();
//                        anchorRoot.setEffect(null);
//                    });
//                    dialog.setOnDialogClosed(e -> {
//                        stackRoot.toBack();
//                        anchorRoot.setEffect(null);
//                        ClearHandle(null);
//                    });
//
//
//                    stackRoot.toFront();
//                    dialog.toFront();
//                    dialog.show();
//                    anchorRoot.setEffect(blur);
//                    dialogLayout.setPadding(new Insets(0, 0, 0, 0));
//                }
//            }
//
//            void cycleExists(Node source, int level) {
//                source.DAGColor = 1;
//                for (Edge e : source.adjacents) {
//                    if (e != null) {
//                        Node v = e.target;
//                        if (v.DAGColor == 1) {
//                            cycleFound = true;
//                        } else if (v.DAGColor == 0) {
//                            v.previous = source;
//                            cycleExists(v, level + 1);
//                        }
//                    }
//                }
//                source.DAGColor = 2;
//            }
//
//            public void topsortRecursion(Node source, int level) {
//                //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                FillTransition ft = new FillTransition(Duration.millis(time), source.circle);
//                if (source.circle.getFill() == Color.BLACK) {
//                    ft.setToValue(Color.FORESTGREEN);
//                }
//                st.getChildren().add(ft);
//
//                String str = "";
//                for (int i = 0; i < level; i++) {
//                    str = str.concat("\t");
//                }
//                str = str.concat("Recursion(" + source.name + ") Enter\n");
//                final String str2 = str;
//                FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
//                fd.setOnFinished(e -> {
//                    textFlow.appendText(str2);
//                });
//                fd.onFinishedProperty();
//                st.getChildren().add(fd);
//                //</editor-fold>
//                source.visited = true;
//                for (Edge e : source.adjacents) {
//                    if (e != null) {
//                        Node v = e.target;
//                        if (!v.visited) {
//                            v.minDistance = source.minDistance + 1;
//                            v.previous = source;
//                            //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                            if (undirected) {
//                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.FORESTGREEN);
//                                st.getChildren().add(ftEdge);
//                            } else if (directed) {
//                                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.FORESTGREEN);
//                                st.getChildren().add(ftEdge);
//                            }
//                            //</editor-fold>
//                            topsortRecursion(v, level + 1);
//                            //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                            //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                            if (undirected) {
//                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.BLUEVIOLET);
//                                st.getChildren().add(ftEdge);
//                            } else if (directed) {
//                                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.BLUEVIOLET);
//                                st.getChildren().add(ftEdge);
//                            }
//                            //</editor-fold>
//                            FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
//                            ft1.setToValue(Color.BLUEVIOLET);
//                            ft1.onFinishedProperty();
//                            st.getChildren().add(ft1);
//                            //</editor-fold>
//                        }
//                    }
//                }
//                str = "";
//                for (int i = 0; i < level; i++) {
//                    str = str.concat("\t");
//                }
//                topSort.add(source.name);
//
//                //<editor-fold defaultstate="collapsed" desc="Recursion exit text">
//                str = str.concat("Recursion(" + source.name + ") Exit\n");
//                final String str1 = str;
//                fd = new FadeTransition(Duration.millis(10), textFlow);
//                fd.setOnFinished(e -> {
//                    textFlow.appendText(str1);
//                });
//                fd.onFinishedProperty();
//                st.getChildren().add(fd);
//                //</editor-fold>
//            }
//        }
//        //</editor-fold>
//
//        //<editor-fold defaultstate="collapsed" desc="Articulation Point">
//        public void newArticulationPoint(Node s) {
//            new ArticulationPoint(s);
//        }
//
//        class ArticulationPoint {
//
//            int timeCnt = 0;
//
//            ArticulationPoint(Node source) {
//
//                //<editor-fold defaultstate="collapsed" desc="Animation Setup Distances">
//                for (NodeFX n : circles) {
//                    visitTime.add(n.visitTime);
//                    n.visitTime.setLayoutX(n.point.x + 20);
//                    n.visitTime.setLayoutY(n.point.y);
//                    canvasGroup.getChildren().add(n.visitTime);
//
//                    lowTime.add(n.lowTime);
//                    n.lowTime.setLayoutX(n.point.x + 20);
//                    n.lowTime.setLayoutY(n.point.y + 13);
//                    canvasGroup.getChildren().add(n.lowTime);
//
//                    n.node.isArticulationPoint = false;
//                }
//
//                st = new SequentialTransition();
//                source.circle.lowTime.setText("Low : " + source.name);
//                source.circle.visitTime.setText("Visit : " + source.visitTime);
//                //</editor-fold>
//
//                timeCnt = 0;
//                RecAP(source);
//
//                for (NodeFX n : circles) {
//                    if (n.node.isArticulationPoint) {
//                        System.out.println(n.node.name);
//                    }
//                }
//
//                //<editor-fold defaultstate="collapsed" desc="Animation after algorithm is finished">
//                st.setOnFinished(ev -> {
//                    for (NodeFX n : circles) {
//                        FillTransition ft1 = new FillTransition(Duration.millis(time), n);
//                        ft1.setToValue(Color.BLACK);
//                        ft1.play();
//                    }
//                    if (directed) {
//                        for (Shape n : edges) {
//                            n.setFill(Color.BLACK);
//                        }
//                    } else if (undirected) {
//                        for (Shape n : edges) {
//                            n.setStroke(Color.BLACK);
//                        }
//                    }
//                    for (NodeFX n : circles) {
//                        if (n.node.isArticulationPoint) {
//                            FillTransition ft1 = new FillTransition(Duration.millis(time), n);
//                            ft1.setToValue(Color.CHARTREUSE);
//                            ft1.play();
//                        }
//                    }
//                    Image image = new Image(getClass().getResourceAsStream("/play_arrow_black_48x48.png"));
//                    playPauseImage.setImage(image);
//                    paused = true;
//                    playing = false;
//                });
//                st.onFinishedProperty();
//                st.play();
//                playing = true;
//                //</editor-fold>
//            }
//
//            void RecAP(Node s) {
//                //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                FillTransition ft = new FillTransition(Duration.millis(time), s.circle);
//                if (s.circle.getFill() == Color.BLACK) {
//                    ft.setToValue(Color.FORESTGREEN);
//                }
//                ft.setOnFinished(ev -> {
//                    s.circle.lowTime.setText("Low : " + s.lowTime);
//                    s.circle.visitTime.setText("Visit : " + s.visitTime);
//                });
//                st.getChildren().add(ft);
//                //</editor-fold>
//                s.visited = true;
//                s.visitTime = timeCnt;
//                s.lowTime = timeCnt;
//
//                timeCnt++;
//                int childCount = 0;
//
//                for (Edge e : s.adjacents) {
//                    if (e != null) {
//                        Node v = e.target;
//                        if (s.previous == v) {
//                            continue;
//                        }
//                        if (!v.visited) {
//                            v.previous = s;
//                            childCount++;
//                            //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                            if (undirected) {
//                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.FORESTGREEN);
//                                st.getChildren().add(ftEdge);
//                            } else if (directed) {
//                                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.FORESTGREEN);
//                                st.getChildren().add(ftEdge);
//                            }
//                            //</editor-fold>
//                            RecAP(v);
//
//                            s.lowTime = Math.min(s.lowTime, v.lowTime);
//                            if (s.visitTime <= v.lowTime && s.previous != null) {
//                                s.isArticulationPoint = true;
//                            }
//
//                            //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                            ///<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                            if (undirected) {
//                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.BLUEVIOLET);
//                                st.getChildren().add(ftEdge);
//                            } else if (directed) {
//                                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.BLUEVIOLET);
//                                st.getChildren().add(ftEdge);
//                            }
//                            //</editor-fold>
//                            FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
//                            ft1.setToValue(Color.BLUEVIOLET);
//                            ft1.setOnFinished(ev -> {
//                                s.circle.lowTime.setText("Low : " + s.lowTime);
//                                s.circle.visitTime.setText("Visit : " + s.visitTime);
//                            });
//                            ft1.onFinishedProperty();
//                            st.getChildren().add(ft1);
//                            //</editor-fold>
//                        } else {
//                            s.lowTime = Math.min(s.lowTime, v.visitTime);
//                        }
//                    }
//                }
//                if (childCount > 1 && s.previous == null) {
//                    s.isArticulationPoint = true;
//                }
//            }
//        }
//        //</editor-fold>
//
//        //<editor-fold defaultstate="collapsed" desc="MST">
//        public void newMST() {
//            new MST();
//        }
//
//        class MST {
//
//            int mstValue = 0;
//
//            Node findParent(Node x) {
//                if (x == x.previous) {
//                    return x;
//                }
//                x.previous = findParent(x.previous);
//                return x.previous;
//            }
//
//            void unionNode(Node x, Node y) {
//                Node px = findParent(x);
//                Node py = findParent(y);
//                if (px == py) {
//                    return;
//                }
//                if (Integer.valueOf(px.name) < Integer.valueOf(py.name)) {
//                    px.previous = py;
//                } else {
//                    py.previous = px;
//                }
//            }
//
//            public MST() {
//
//                st = new SequentialTransition();
//                for (NodeFX x : circles) {
//                    x.node.previous = x.node;
//                }
//
//                //<editor-fold defaultstate="collapsed" desc="Detail Information">
//                String init = "Intially : \n";
//                for (NodeFX x : circles) {
//                    final String s = "Node : " + x.node.name + " , Parent: " + x.node.previous.name + "\n";
//                    FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
//                    fd.setOnFinished(e -> {
//                        textFlow.appendText(s);
//                    });
//                    fd.onFinishedProperty();
//                    st.getChildren().add(fd);
//                }
//                final String s = "Start Algorithm :---\n";
//                FadeTransition fdss = new FadeTransition(Duration.millis(10), textFlow);
//                fdss.setOnFinished(ev -> {
//                    textFlow.appendText(s);
//                });
//                fdss.onFinishedProperty();
//                st.getChildren().add(fdss);
//                //</editor-fold>
//                Collections.sort(mstEdges, new Comparator<Edge>() {
//                    public int compare(Edge o1, Edge o2) {
//                        if (o1.weight == o2.weight) {
//                            return 0;
//                        }
//                        return o1.weight > o2.weight ? 1 : -1;
//                    }
//                });
//
//                for (Edge e : mstEdges) {
//
//                    StrokeTransition ft1 = new StrokeTransition(Duration.millis(time), e.line);
//                    ft1.setToValue(Color.DARKORANGE);
//                    st.getChildren().add(ft1);
//
//                    //<editor-fold defaultstate="collapsed" desc="Detail Information">
//                    final String se = "Selected Edge:- (" + e.source.name.trim() + "--" + e.target.name.trim() + ") Weight: " + String.valueOf(e.weight) + " \n";
//                    FadeTransition fdx = new FadeTransition(Duration.millis(10), textFlow);
//                    fdx.setOnFinished(evx -> {
//                        textFlow.appendText(se);
//                    });
//                    fdx.onFinishedProperty();
//                    st.getChildren().add(fdx);
//
//                    final String s1 = "\t-> Node :" + e.source.name.trim() + "  Parent: " + findParent(e.source.previous).name.trim() + "\n";
//                    FadeTransition fdx2 = new FadeTransition(Duration.millis(10), textFlow);
//                    fdx2.setOnFinished(evx -> {
//                        textFlow.appendText(s1);
//                    });
//                    fdx2.onFinishedProperty();
//                    st.getChildren().add(fdx2);
//
//                    final String s2 = "\t-> Node :" + e.target.name.trim() + "  Parent: " + findParent(e.target.previous).name.trim() + "\n";
//                    FadeTransition fdx3 = new FadeTransition(Duration.millis(10), textFlow);
//                    fdx3.setOnFinished(evx -> {
//                        textFlow.appendText(s2);
//                    });
//                    fdx3.onFinishedProperty();
//                    st.getChildren().add(fdx3);
//                    //</editor-fold>
//
//                    if (findParent(e.source.previous) != findParent(e.target.previous)) {
//                        unionNode(e.source, e.target);
//                        mstValue += e.weight;
//
//                        //<editor-fold defaultstate="collapsed" desc="Detail Information">
//                        final String sa = "\t---->Unioned\n";
//                        final String sa1 = "\t\t->Node :" + e.source.name.trim() + "  Parent: " + findParent(e.source.previous).name.trim() + "\n";
//                        final String sa2 = "\t\t->Node :" + e.target.name.trim() + "  Parent: " + findParent(e.target.previous).name.trim() + "\n";
//                        FadeTransition fdx4 = new FadeTransition(Duration.millis(10), textFlow);
//                        fdx4.setOnFinished(evx -> {
//                            textFlow.appendText(sa);
//                        });
//                        fdx4.onFinishedProperty();
//                        st.getChildren().add(fdx4);
//                        FadeTransition fdx5 = new FadeTransition(Duration.millis(10), textFlow);
//                        fdx5.setOnFinished(evx -> {
//                            textFlow.appendText(sa1);
//                        });
//                        fdx5.onFinishedProperty();
//                        st.getChildren().add(fdx5);
//                        FadeTransition fdx6 = new FadeTransition(Duration.millis(10), textFlow);
//                        fdx6.setOnFinished(evx -> {
//                            textFlow.appendText(sa2);
//                        });
//                        fdx6.onFinishedProperty();
//                        st.getChildren().add(fdx6);
//
//                        StrokeTransition ft2 = new StrokeTransition(Duration.millis(time), e.line);
//                        ft2.setToValue(Color.DARKGREEN);
//                        st.getChildren().add(ft2);
//
//                        FillTransition ft3 = new FillTransition(Duration.millis(time), e.source.circle);
//                        ft3.setToValue(Color.AQUA);
//                        st.getChildren().add(ft3);
//
//                        ft3 = new FillTransition(Duration.millis(time), e.target.circle);
//                        ft3.setToValue(Color.AQUA);
//                        st.getChildren().add(ft3);
//                        //</editor-fold>
//                    } else {
//                        //<editor-fold defaultstate="collapsed" desc="Detail Info">
//                        final String sa = "\t---->Cycle Detected\n";
//                        FadeTransition fdx7 = new FadeTransition(Duration.millis(10), textFlow);
//                        fdx7.setOnFinished(evx -> {
//                            textFlow.appendText(sa);
//                        });
//                        fdx7.onFinishedProperty();
//                        st.getChildren().add(fdx7);
//                        //</editor-fold>
//                        StrokeTransition ft2 = new StrokeTransition(Duration.millis(time), e.line);
//                        ft2.setToValue(Color.DARKRED);
//                        st.getChildren().add(ft2);
//
//                        ft2 = new StrokeTransition(Duration.millis(time), e.line);
//                        ft2.setToValue(Color.web("#E0E0E0"));
//                        st.getChildren().add(ft2);
//
//                    }
//                }
//
//                //<editor-fold defaultstate="collapsed" desc="Animation after algorithm is finished">
//                st.setOnFinished(ev -> {
//                    Image image = new Image(getClass().getResourceAsStream("/play_arrow_black_48x48.png"));
//                    playPauseImage.setImage(image);
//                    paused = true;
//                    playing = false;
//                    textFlow.appendText("Minimum Cost of the Graph " + mstValue);
//                });
//                st.onFinishedProperty();
//                st.play();
//                playing = true;
//                //</editor-fold>
//                System.out.println("" + mstValue);
//            }
//        }
//        //</editor-fold>
//
//        //<editor-fold defaultstate="collapsed" desc="DFS">
//        public void newDFS(Node source) {
//            new DFS(source);
//        }
//
//        class DFS {
//
//            DFS(Node source) {
//
//                //<editor-fold defaultstate="collapsed" desc="Animation Setup Distances">
//                for (NodeFX n : circles) {
//                    distances.add(n.distance);
//                    n.distance.setLayoutX(n.point.x + 20);
//                    n.distance.setLayoutY(n.point.y);
//                    canvasGroup.getChildren().add(n.distance);
//                }
//                sourceText.setLayoutX(source.circle.point.x + 20);
//                sourceText.setLayoutY(source.circle.point.y + 10);
//                canvasGroup.getChildren().add(sourceText);
//                st = new SequentialTransition();
//                source.circle.distance.setText("Dist. : " + 0);
//                //</editor-fold>
//
//                source.minDistance = 0;
//                source.visited = true;
//                DFSRecursion(source, 0);
//
//                //<editor-fold defaultstate="collapsed" desc="Animation after algorithm is finished">
//                st.setOnFinished(ev -> {
//                    for (NodeFX n : circles) {
//                        FillTransition ft1 = new FillTransition(Duration.millis(time), n);
//                        ft1.setToValue(Color.BLACK);
//                        ft1.play();
//                    }
//                    if (directed) {
//                        for (Shape n : edges) {
//                            n.setFill(Color.BLACK);
//                        }
//                    } else if (undirected) {
//                        for (Shape n : edges) {
//                            n.setStroke(Color.BLACK);
//                        }
//                    }
//                    FillTransition ft1 = new FillTransition(Duration.millis(time), source.circle);
//                    ft1.setToValue(Color.RED);
//                    ft1.play();
//                    Image image = new Image(getClass().getResourceAsStream("/play_arrow_black_48x48.png"));
//                    playPauseImage.setImage(image);
//                    paused = true;
//                    playing = false;
//                    textFlow.appendText("---Finished--\n");
//                });
//                st.onFinishedProperty();
//                st.play();
//                playing = true;
//                paused = false;
//                //</editor-fold>
//            }
//
//            public void DFSRecursion(Node source, int level) {
//                //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                FillTransition ft = new FillTransition(Duration.millis(time), source.circle);
//                if (source.circle.getFill() == Color.BLACK) {
//                    ft.setToValue(Color.FORESTGREEN);
//                }
//                st.getChildren().add(ft);
//
//                String str = "";
//                for (int i = 0; i < level; i++) {
//                    str = str.concat("\t");
//                }
//                str = str.concat("DFS(" + source.name + ") Enter\n");
//                final String str2 = str;
//                FadeTransition fd = new FadeTransition(Duration.millis(10), textFlow);
//                fd.setOnFinished(e -> {
//                    textFlow.appendText(str2);
//                });
//                fd.onFinishedProperty();
//                st.getChildren().add(fd);
//                //</editor-fold>
//                for (Edge e : source.adjacents) {
//                    if (e != null) {
//                        Node v = e.target;
//                        if (!v.visited) {
//                            v.minDistance = source.minDistance + 1;
//                            v.visited = true;
//                            v.previous = source;
////                        v.circle.distance.setText("Dist. : " + v.minDistance);
//                            //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                            if (undirected) {
//                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.FORESTGREEN);
//                                st.getChildren().add(ftEdge);
//                            } else if (directed) {
//                                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.FORESTGREEN);
//                                st.getChildren().add(ftEdge);
//                            }
//                            //</editor-fold>
//                            DFSRecursion(v, level + 1);
//                            //<editor-fold defaultstate="collapsed" desc="Animation Control">
//                            //<editor-fold defaultstate="collapsed" desc="Change Edge colors">
//                            if (undirected) {
//                                StrokeTransition ftEdge = new StrokeTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.BLUEVIOLET);
//                                st.getChildren().add(ftEdge);
//                            } else if (directed) {
//                                FillTransition ftEdge = new FillTransition(Duration.millis(time), e.line);
//                                ftEdge.setToValue(Color.BLUEVIOLET);
//                                st.getChildren().add(ftEdge);
//                            }
//                            //</editor-fold>
//                            FillTransition ft1 = new FillTransition(Duration.millis(time), v.circle);
//                            ft1.setToValue(Color.BLUEVIOLET);
//                            ft1.onFinishedProperty();
//                            ft1.setOnFinished(ev -> {
//                                v.circle.distance.setText("Dist. : " + v.minDistance);
//                            });
//                            st.getChildren().add(ft1);
//                            //</editor-fold>
//                        }
//                    }
//                }
//                str = "";
//                for (int i = 0; i < level; i++) {
//                    str = str.concat("\t");
//                }
//                str = str.concat("DFS(" + source.name + ") Exit\n");
//                final String str1 = str;
//                fd = new FadeTransition(Duration.millis(10), textFlow);
//                fd.setOnFinished(e -> {
//                    textFlow.appendText(str1);
//                });
//                fd.onFinishedProperty();
//                st.getChildren().add(fd);
//            }
//        }
//
//        //</editor-fold>
//        public List<Node> getShortestPathTo(Node target) {
//            List<Node> path = new ArrayList<Node>();
//            for (Node i = target; i != null; i = i.previous) {
//                path.add(i);
//            }
//            Collections.reverse(path);
//            return path;
//        }
    }



}
