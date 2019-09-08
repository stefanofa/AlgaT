package interactiveDataStructures.heap;

import javafx.scene.Parent;
import interactiveDataStructures.snapshot.SnapshotElement;
import interactiveDataStructures.snapshot.SnapshotList;

import java.util.ArrayList;
import java.util.Collections;

public class InteractiveHeap extends Parent {
    private SnapshotList history = null;
    private ArrayList<Integer> heap = new ArrayList<Integer>();

    public InteractiveHeap() {}
    public InteractiveHeap(ArrayList<Integer> a) {
        load(a);
    }

    public void startRecording() {
        history = new SnapshotList(heap);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void insert(Integer el) {
        heap.add(el);
        int i = size() - 1;
        addSnapshot("insert", i);
        while (i > 1 && heap.get(i) < heap.get(p(i))) {
            swap(i, p(i));
            i = p(i);
        }
    }

    public void swap(int index1, int index2) {
        Collections.swap(heap, index1, index2);
        addSnapshot("swap", index1, index2);
    }

    // Precondition: size() > 0
    public Integer deleteMin() {
        swap(0, size() - 1);
        minHeapRestore(0);
        return remove(size() - 1);
    }

    public void minHeapRestore(int i) {
        int min = i;
        if (l(i) < heap.size() && heap.get(l(i)) < heap.get(min))
            min = l(i);
        if (r(i) < heap.size() && heap.get(r(i)) < heap.get(min))
            min = r(i);
        if (i != min) {
            swap(i, min);
            minHeapRestore(min);
        }
    }

    public void maxHeapRestore(int i) {
        int max = i;
        if (l(i) < heap.size() && heap.get(l(i)) > heap.get(max))
            max = l(i);
        if (r(i) < heap.size() && heap.get(r(i)) > heap.get(max))
            max = r(i);
        if (i != max) {
            swap(i, max);
            maxHeapRestore(max);
        }
    }

    public void maxHeapRestore(int i, int dim) {
        int max = i;
        if (l(i) <= dim && heap.get(l(i)) > heap.get(max))
            max = l(i);
        if (r(i) <= dim && heap.get(r(i)) > heap.get(max))
            max = r(i);
        if (i != max) {
            swap(i, max);
            maxHeapRestore(max,dim);
        }
    }

    public Integer remove(int i) {
        addSnapshot("delete", i);
        return heap.remove(i);
    }

    public ArrayList<Integer> getSnapshot() {
        return new ArrayList<Integer>(heap);
    }

    public void load(ArrayList<Integer> a) {
        heap = a;
        history = new SnapshotList(a);
    }

    public void heapBuild() {
        for (int i = heap.size() / 2; i >= 0; i--)
            maxHeapRestore(i);
    }

    public SnapshotList getHistory() {
        return history;
    }

    public SnapshotList stopRecordingAndGetHistory() {
        SnapshotList h = history;
        history = null;
        return h;
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

    private void addSnapshot(String op, int index) {
        if (history != null)
            history.addElement(new SnapshotElement(getSnapshot(), op, index));
    }

    private void addSnapshot(String op, int i1, int i2) {
        if (history != null)
            history.addElement(new SnapshotElement(getSnapshot(), op, i1, i2));
    }

    public void archive(int i) {
        if (history != null) {
            SnapshotElement el = new SnapshotElement(heap, "archive", i);
            history.addElement(el);
        }
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

    public void highlight(int i) {
        if (history != null) {
            SnapshotElement se = new SnapshotElement(heap, "highlight", i);
            history.addElement(se);
        }
    }

}
