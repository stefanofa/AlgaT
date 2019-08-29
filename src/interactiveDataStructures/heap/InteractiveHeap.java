package interactiveDataStructures.heap;

import interactiveDataStructures.array.InteractiveArray;
import interactiveDataStructures.trees.InteractiveBinaryTree;
import javafx.scene.Parent;
import snapshot.SnapshotElement;
import snapshot.SnapshotList;

import java.util.ArrayList;
import java.util.Collections;

public class InteractiveHeap extends Parent {
    private SnapshotList history = null;
    private ArrayList<Integer> heap = new ArrayList<Integer>();

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

    public Integer remove(int i) {
        addSnapshot("delete", i);
        return heap.remove(i);
    }

    public ArrayList<Integer> getSnapshot() {
        return new ArrayList<Integer>(heap);
    }

    public void load(ArrayList<Integer> a) {
        if (history == null)
            heap = a;
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
        return i/2;
    }

    private static int l(int i) {
        return i * 2;
    }

    private static int r(int i) {
        return i * 2 + 1;
    }

    private void addSnapshot(String op, int index) {
        if (history != null)
            history.addElement(new SnapshotElement(getSnapshot(), op, index));
    }

    private void addSnapshot(String op, int i1, int i2) {
        if (history != null)
            history.addElement(new SnapshotElement(getSnapshot(), op, i1, i2));
    }

}
