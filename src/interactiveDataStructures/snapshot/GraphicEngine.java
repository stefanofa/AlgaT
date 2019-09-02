package interactiveDataStructures.snapshot;

import interactiveDataStructures.array.InteractiveArray;
import interactiveDataStructures.trees.InteractiveBinaryTree;
import javafx.scene.Parent;
import interactiveDataStructures.snapshot.SnapshotList;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class GraphicEngine extends Parent {
    private HBox hBox = new HBox();
    private InteractiveArray ia = new InteractiveArray();
    private InteractiveBinaryTree ibt = new InteractiveBinaryTree();

    SnapshotList history = null;
    Integer index = null;

    public GraphicEngine() {
        hBox.getChildren().addAll(ia, ibt);
        this.getChildren().add(hBox);
    }

    public void load(SnapshotList history) {
        this.history = history;
        restart();
    }

    public void restart() {
        index = 0;
        ArrayList heap = history.getFirst().getHeapArray();
        ia.load(heap);
        ibt.load(heap);
    }
}
