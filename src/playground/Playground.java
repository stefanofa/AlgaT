package playground;

import baseController.BaseController;
import interactiveDataStructures.heap.InteractiveHeap;
import interactiveDataStructures.snapshot.GraphicEngine;
import interactiveDataStructures.snapshot.SnapshotList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class Playground extends BaseController {
    @FXML VBox vBox;
    @FXML TextField input;
    @FXML TextField index1;
    @FXML TextField index2;

    private Integer count = 0;

    GraphicEngine ge = new GraphicEngine();
    InteractiveHeap h = new InteractiveHeap();

    @FXML private void initialize() {
        vBox.getChildren().addAll(ge);
        ArrayList<Integer> a = new ArrayList<Integer>();
        Random random = new Random();
        int n = 15;
        for (int i = 0; i < n; i++)
            a.add(random.nextInt(n * 3 + 1));
        h.startRecording();
        h.load(a);
        h.heapsort();
        SnapshotList history = h.stopRecordingAndGetHistory();
        ge.load(history);
    }

    @FXML private void addElement() {

    }

    @FXML private void removeElement() {
        //ia.removeAt(Integer.parseInt(input.getText()));
    }

    @FXML private void archiveElement() {
        //ia.archiveAt(Integer.parseInt(input.getText()));
    }

    @FXML private void swap() {
        int i1 = Integer.parseInt(index1.getText());
        int i2 = Integer.parseInt(index2.getText());
        System.out.println(i1 + " " + i2);
    }

    @FXML private void tree() {

        count++;
    }

    @FXML private void insertLeft() {
        //ibt.insertLeft(count);
        count++;
    }

    @FXML private void insertRight() {
        //ibt.insertRight(count);
        count++;
    }

    @FXML private void delete() {

    }

    @FXML private void prev() {
        ge.prev();
    }

    @FXML private void next() {
        ge.next();
    }

    @FXML private void start() {
        ge.switchPlayMode();
    }

}
