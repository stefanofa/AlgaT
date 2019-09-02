package interactiveDataStructures.snapshot;

import interactiveDataStructures.array.InteractiveArray;
import interactiveDataStructures.trees.InteractiveBinaryTree;
import javafx.scene.Parent;
import interactiveDataStructures.snapshot.SnapshotList;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GraphicEngine extends VBox {
    private InteractiveArray ia = new InteractiveArray();
    private InteractiveBinaryTree ibt = new InteractiveBinaryTree();

    SnapshotList history = null;
    Integer index = null;

    public GraphicEngine() {
        this.getChildren().addAll(ia, ibt);
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

    public void next() {
        if (!history.ended()) {
            SnapshotElement snapshot = history.next();
            ArrayList<Integer> a = snapshot.getHeapArray();
            int index = snapshot.getIndex();
            if (snapshot.isSwapTrans()) {
                int index2 = snapshot.getIndex2();
                ia.swap(index, index2);
                ibt.swap(index, index2);
            } else if (snapshot.isInsertTrans()) {
                int el = a.get(index);
                ia.push(el);
                ibt.insert(el);
            }
        }
    }

    public void prev() {
        if (!history.atStart()) {
            SnapshotElement snapshot = history.prev();
            ArrayList<Integer> a = snapshot.getHeapArray();
            int index = snapshot.getIndex();
            if (snapshot.isSwapTrans()) {
                int index2 = snapshot.getIndex2();
                ia.swap(index2, index);
                ibt.swap(index2, index);
            }
        }
    }
}
