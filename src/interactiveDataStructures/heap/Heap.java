package interactiveDataStructures.heap;

import snapshot.SnapshotList;

import java.util.ArrayList;
import java.util.Collections;

public class Heap {
    private ArrayList<Integer> heap = new ArrayList<Integer>();

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public void insert(Integer el) {
        heap.add(el);
        int i = size() - 1;
        while (i > 1 && heap.get(i) < heap.get(p(i))) {
            Collections.swap(heap, i, p(i));
            i = p(i);
        }
    }

    // Precondition: size() > 0
    public Integer deleteMin() {
        Collections.swap(heap, 0, size() - 1);
        minHeapRestore(heap, 1);
        return heap.remove(size() - 1);
    }

    public static void minHeapRestore(ArrayList<Integer> A, int i) {
        int min = i;
        if (l(i) < A.size() && A.get(l(i)) < A.get(min))
            min = l(i);
        if (r(i) < A.size() && A.get(r(i)) < A.get(min))
            min = r(i);
        if (i != min) {
            Collections.swap(A, i, min);
            minHeapRestore(A, min);
        }
    }

    public ArrayList<Integer> getSnapshot() {
        return new ArrayList<Integer>(heap);
    }

    public static int p(int i) {
        return i/2;
    }

    public static int l(int i) {
        return i * 2;
    }

    public static int r(int i) {
        return i * 2 + 1;
    }


}
