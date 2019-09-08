package topics.heap.lesson;

import baseController.BaseController;
import baseController.Config;
import interactiveDataStructures.array.InteractiveArray;
import interactiveDataStructures.heap.InteractiveHeap;
import interactiveDataStructures.snapshot.GraphicEngine;
import interactiveDataStructures.trees.InteractiveBinaryTree;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class HeapLesson extends BaseController {
    @FXML BorderPane borderPane;
    List<Node> slides = new LinkedList<Node>();
    GraphicEngine ge;

    @FXML
    protected void initialize() {
        InteractiveHeap heap = new InteractiveHeap();
        borderPane.setCenter(slide1());
    }

    // Relazione tra albero e vettore
    private Node slide1() {
        VBox n = new VBox();
        n.setMinWidth(Config.SLIDE_WIDTH);
        n.setMinHeight(Config.SLIDE_HEIGHT);
        Text t = new Text();
        t.setFont(Font.font(Config.FONT_SIZE));
        t.setText("Come introduzione, descriviamo brevemente la struttura HEAP e le sue proprietà.\n" +
                "La struttura HEAP si presenta come un albero binario avente le seguenti proprietà\n" +
                "\t1) L'albero è bilanciato a meno di una differenza di altezza pari a 1\n" +
                "\t2) Tutte le foglie del livello più basso sono addossate a sinistra\n" +
                "\t3) Gli eventuali figli di un nodo contengono valori minori o uguali di quelli del padre\n");

        Random random = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            a.add(random.nextInt(Config.RANDOM_RANGE));
        InteractiveHeap h = new InteractiveHeap(a);
        ge = new GraphicEngine();
        h.heapBuild();
        h.startRecording();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            h.highlight(i);
        ge.load(h.stopRecordingAndGetHistory());
        n.getChildren().addAll(t, ge);

        return n;
    }

    @FXML
    private void next() {
        ge.switchPlayMode(1000);
    }

}
