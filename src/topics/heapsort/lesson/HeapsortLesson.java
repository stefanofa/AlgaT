package topics.heapsort.lesson;

import baseController.BaseController;
import interactiveDataStructures.heap.InteractiveHeap;
import interactiveDataStructures.snapshot.GraphicEngine;
import interactiveDataStructures.snapshot.SnapshotList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Random;

public class HeapsortLesson extends BaseController {

    @FXML VBox vBox;
    @FXML Button autoButton;
    private GraphicEngine ge = new GraphicEngine();
    private InteractiveHeap heap = new InteractiveHeap();

    @FXML private void initialize() {
        vBox.getChildren().addAll(ge);
        ArrayList<Integer> a = new ArrayList<Integer>();
        Random random = new Random();
        int n = 15;
        for (int i = 0; i < n; i++)
            a.add(random.nextInt(n * 3 + 1));
        heap.startRecording();
        heap.load(a);
        heapsort();
        SnapshotList history = heap.stopRecordingAndGetHistory();
        ge.load(history);
    }

    private void heapsort() {
        heap.heapBuild();
        for (int i = heap.size()-1; i >=1 ; i--) {
            heap.swap(i, 0);
            heap.archive(i);
            heap.maxHeapRestore(0, i-1);
        }
        heap.archive(0);
        for (int i = 0; i < heap.size(); i++)
            heap.highlight(i);
    }

    @FXML private void next() {
        if (ge.isAutoPlaying()) revertPlayMode();
        ge.next();
    }

    @FXML private void prev() {
        if (ge.isAutoPlaying()) revertPlayMode();
        ge.prev();
    }

    @FXML private void revertPlayMode() {
        if(ge.isAutoPlaying())
            autoButton.setText("MANUAL");

        else
            autoButton.setText("AUTO");
        ge.switchPlayMode();
    }


    @FXML private void goToMenu(ActionEvent event) {
        try {
            switchSceneFromFxmlPath("../../../menu/menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
