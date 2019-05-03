package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
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
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private ImageView playPauseImage, openHidden;

    int nVertex = 0;
    int time = 500;

    VertexFX selectedVertex = null;

    List<VertexFX> circles = new ArrayList<>();
    List<Edge> realEdges = new ArrayList<>();
    List<Shape> edges = new ArrayList<>();

    boolean addVertex = true, addEdge = false, calculate = false,
            calculated = false, playing = false, paused = false, pinned = false;

    private boolean weighted = false, unweighted = true,bfs = true, dfs = true, dijkstra = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("In intit");
//        hiddenPane.setContent(canvasGroup);
//        anchorRoot.setManaged(false);

        ResetHandle(null);
//        viewer.prefHeightProperty().bind(border.heightProperty());
//        viewer.prefWidthProperty().bind(border.widthProperty());
//        AddVertexHandle(null);
        addEdgeButton.setDisable(true);
        addVertexButton.setDisable(true);
        clearButton.setDisable(true);

        if (weighted) {
            bfsButton.setDisable(true);
            dfsButton.setDisable(true);
        }

        if (unweighted) {
            dijkstraButton.setDisable(true);
        }

//        if (directed) {
//            articulationPointButton.setDisable(true);
//        }

//        //Set back button action
//        canvasBackButton.setOnAction(e -> {
//            try {
//                ResetHandle(null);
//                Parent root = FXMLLoader.load(getClass().getResource("Panel1FXML.fxml"));
//
//                Scene scene = new Scene(root);
//                FXSimulatorMain.primaryStage.setScene(scene);
//            } catch (IOException ex) {
//                Logger.getLogger(CanvasController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

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
                    tr.setByX(10f);
                    tr.setByY(10f);
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
//                                selectedNode.node.adjacents.add(new Edge(selectedNode.node, circle.node, Double.valueOf(weight.getText()), edgeLine, weight));
//                                circle.node.adjacents.add(new Edge(circle.node, selectedNode.node, Double.valueOf(weight.getText()), edgeLine, weight));
//                                edges.add(edgeLine);
//                                realEdges.add(selectedNode.node.adjacents.get(selectedNode.node.adjacents.size() - 1));
//                                realEdges.add(circle.node.adjacents.get(circle.node.adjacents.size() - 1));
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
                        if (addVertex || (calculate && !calculated) || addEdge) {
                            selectedVertex.isSelected = false;
                            FillTransition ft1 = new FillTransition(Duration.millis(300), selectedVertex, Color.GREEN, javafx.scene.paint.Color.BLACK);
                            ft1.play();
                        }
                        selectedVertex = null;
                        return;
                    }

                    FillTransition ft = new FillTransition(Duration.millis(300), circle, javafx.scene.paint.Color.BLACK, Color.GREEN);
                    ft.play();
                    circle.isSelected = true;
                    selectedVertex = circle;

//                    // WHAT TO DO WHEN SELECTED ON ACTIVE ALGORITHM
//                    if (calculate && !calculated) {
//                        if (bfs) {
//                            algo.newBFS(circle.vertex);
//                        } else if (dfs) {
//                            algo.newDFS(circle.vertex);
//                        } else if (dijkstra) {
//                            algo.newDijkstra(circle.vertex);
//                        }
//
//                        calculated = true;
//                    } else if (calculate && calculated && !articulationPoint & !mst && !topSortBool) {
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
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(false);
        addVertexButton.setDisable(true);
        addEdgeButton.setDisable(true);
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
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(false);
        addVertexButton.setDisable(true);
        addEdgeButton.setDisable(true);
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
        addVertexButton.setSelected(false);
        addEdgeButton.setSelected(false);
        addVertexButton.setDisable(true);
        addEdgeButton.setDisable(true);
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
        clearButton.setDisable(true);
//        algo = new Algorithm();
//        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResourceAsStream("/pause_black_48x48.png"));
//        playPauseImage.setImage(image);
//        hiddenPane.setPinnedSide(null);

        bfsButton.setDisable(true);
        dfsButton.setDisable(true);
        dijkstraButton.setDisable(true);
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
//        if(st != null && st.getStatus() != Animation.Status.STOPPED)
//            st.stop();
//        if(st != null) st.getChildren().clear();
//        menuBool = false;
        selectedVertex = null;
        calculated = false;
        System.out.println("IN CLEAR:" + circles.size());
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
//        canvasGroup.getChildren().remove(sourceText);
//        for (Label x : distances) {
//            x.setText("Distance : INFINITY");
//            canvasGroup.getChildren().remove(x);
//        }
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
//        javafx.scene.image.Image image = new Image(getClass().getResourceAsStream("/pause_black_48x48.png"));
//        playPauseImage.setImage(image);
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
            canvasGroup.getChildren().add(id);
            id.setLayoutX(x - 18);
            id.setLayoutY(y - 18);
            this.setOpacity(0.5);
            this.setBlendMode(BlendMode.MULTIPLY);
            this.setId("vertex");

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
//            circles.add(this);
//            System.out.println("ADDing: " + circles.size());
        }
    }

}
