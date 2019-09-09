package interactiveDataStructures.heap;

import javafx.scene.Parent;
import graphicEngine.SnapshotElement;
import graphicEngine.SnapshotList;

import java.util.ArrayList;
import java.util.Collections;

public class InteractiveHeap extends Parent {
    private SnapshotList history = null;
    private ArrayList<Integer> heap = new ArrayList<Integer>();
    private String subProcedure;

    public void load(ArrayList<Integer> a) {
        heap = a;
        history = new SnapshotList(a);
    }

    private static int p(int i) {
        return (i + 1) / 2 - 1;
    }

    private static int l(int i) {
        return (i + 1) * 2 - 1;
    }

    private static int r(int i) {
        return (i + 1) * 2;
    }

    public int size() {
        return heap.size();
    }

    public void swap(int index1, int index2) {
        Collections.swap(heap, index1, index2);
        addSnapshot("swap", index1, index2);
    }

    public void maxHeapRestore(int i) {
        maxHeapRestore(i, heap.size());
    }

    private void maxHeapRestore(int i, int dim) {
        setSubProcedure("maxHeapRestore(" + (i + 1) + ")");
        int max = i;
        if (l(i) <= dim && heap.get(l(i)) > heap.get(max))
            max = l(i);
        if (r(i) <= dim && heap.get(r(i)) > heap.get(max))
            max = r(i);
        if (i != max) {
            swap(i, max);
            maxHeapRestore(max, dim);
        }
        setSubProcedure("");
    }

    public void heapBuild() {
        setSubProcedure("heapBuild()");
        for (int i = heap.size() / 2; i >= 0; i--)
            maxHeapRestore(i);
        setSubProcedure("");
    }

    public void heapsort() {
        heapBuild();
        for (int i = size() - 1; i >= 1; i--) {
            swap(i, 0);
            archive(i);
            maxHeapRestore(0, i-1);
        }
        archive(0);
    }

    public void archive(int i) {
        if (history != null) {
            SnapshotElement el = new SnapshotElement(heap, "archive", i);
            history.addElement(el);
        }
    }

    public void highlight(int i) {
        if (history != null) {
            SnapshotElement se = new SnapshotElement(heap, "highlight", i);
            history.addElement(se);
        }
    }

    private void setSubProcedure(String subProcedure) {
        if (subProcedure != "" && this.subProcedure == "" || subProcedure == "")
            this.subProcedure = subProcedure;
    }

    public void startRecording() {
        history = new SnapshotList(heap);
    }

    private void addSnapshot(String op, int i1, int i2) {
        if (history != null)
            history.addElement(new SnapshotElement(getSnapshot(), op, i1, i2, subProcedure, op + "(" + (i1 + 1) + ", " + (i2 + 1) + ")"));
    }

    public ArrayList<Integer> getSnapshot() {
        return new ArrayList<Integer>(heap);
    }

    public SnapshotList getHistory() {
        return history;
    }

    public SnapshotList stopRecordingAndGetHistory() {
        SnapshotList h = history;
        history = null;
        return h;
    }

}
