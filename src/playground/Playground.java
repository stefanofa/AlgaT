package playground;

import config.BaseController;
import interactiveDataStructures.heap.InteractiveHeap;
import interactiveDataStructures.graphicEngine.GraphicEngine;
import interactiveDataStructures.graphicEngine.SnapshotList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class Playground extends BaseController {
    @FXML VBox vBox;
    @FXML TextField index1;
    @FXML TextField index2;

    private Integer count = 0;

    GraphicEngine ge = new GraphicEngine();
    InteractiveHeap h = new InteractiveHeap();

    @FXML private void initialize() {
        vBox.getChildren().addAll(ge);
        reset();
    }

    @FXML private void swap() {
        int i1 = Integer.parseInt(index1.getText()) - 1;
        int i2 = Integer.parseInt(index2.getText()) - 1;

        h.swap(i1, i2);
        ge.next();
    }

    @FXML private void prev() {
        ge.prev();
    }

    @FXML private void next() {
        ge.next();
    }

    @FXML private void exit() {
        switchSceneFromFxmlPath("../menu/menu.fxml");
    }

    @FXML private void reset() {
        ArrayList<Integer> a = new ArrayList<Integer>();
        Random random = new Random();
        int n = 15;
        for (int i = 0; i < n; i++)
            a.add(random.nextInt(n * 3 + 1));
        h.load(a);
        h.startRecording();
        ge.load(h.getHistory());
    }

}
