package playground;

import config.BaseController;
import interactiveDataStructures.heap.InteractiveHeap;
import graphicEngine.GraphicEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        try {
            Integer i1, i2;
            i1 = Integer.parseInt(index1.getText()) - 1;
            i2 = Integer.parseInt(index2.getText()) - 1;
            if (i1 < 0 || i2 < 0 || i1 == i2 || i1 >= h.size() || i2 >= h.size())
                throw new NumberFormatException();
            h.swap(i1, i2);
            ge.next();
            clearInputs();
        } catch (NumberFormatException e) {
            error();
        }
    }

    @FXML private void prev() {
        ge.prev();
    }

    @FXML private void next() {
        ge.next();
    }

    @FXML private void exit() {
        switchSceneFromFxmlPath("/menu/menu.fxml");
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

    private void error() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText("I valori inseriti non sono corretti");
        alert.showAndWait();
        clearInputs();
    }

    private void clearInputs() {
        index1.setText("");
        index2.setText("");
    }

}
