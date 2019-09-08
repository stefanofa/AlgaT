package interactiveDataStructures.snapshot;

import baseController.Config;
import interactiveDataStructures.array.InteractiveArray;
import interactiveDataStructures.trees.InteractiveBinaryTree;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GraphicEngine extends VBox {
    private InteractiveArray ia = new InteractiveArray();
    private InteractiveBinaryTree ibt = new InteractiveBinaryTree();
    private Timer timer = new Timer();

    private boolean autoplay = false;

    private SnapshotList history = null;
    private Integer index = null;

    public GraphicEngine() {
        this.getChildren().addAll(ia, ibt);
    }

    public void load(SnapshotList history) {
        this.history = history;
        restart();
    }

    public void enqueueSnapshot(SnapshotElement el) {
        history.addElement(el);
    }

    private void restart() {
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
            } else if (snapshot.isArchiveTrans()) {
                ia.archiveAt(index);
                ibt.archive(index);
            } else if (snapshot.isHighlightTrans()) {
                ia.highlightAt(index);
                ibt.highlight(index);
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
            } else if (snapshot.isArchiveTrans()) {
                ia.unarchiveAt(index);
                ibt.unarchive(index);
            } else if (snapshot.isHighlightTrans()) {
                ia.unhighlightAt(index);
                ibt.unhighlight(index);
            }
        }
    }

    public void switchPlayMode() {
            switchPlayMode(Config.ANIMATION_DEFAULT_MILLIS * 3);
    }

    public void switchPlayMode(int timeout) {
        if (!autoplay)
            play(timeout);
        else
            pause();
    }


    private void play(int timeout) {
        autoplay = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                next();
                if (history.ended())
                    timer.cancel();
            }
        }, 0, timeout);
    }

    private void pause() {
        autoplay = false;
        timer.cancel();
    }

    public boolean isAutoPlaying() {
        return autoplay;
    }

}
