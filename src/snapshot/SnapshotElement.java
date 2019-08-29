package snapshot;

import java.util.ArrayList;

public class SnapshotElement extends Object {

    private ArrayList heapArray;

    boolean insertTrans = false;
    boolean deleteTrans = false;
    boolean swapTrans   = false;
    int index;
    int index2;

    public SnapshotElement(ArrayList<Integer> array) {
        this.heapArray = array;
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index) {
        this.heapArray = array;
        this.index = index;
        if (op == "insert")
            this.insertTrans = true;
        else if (op == "delete")
            this.deleteTrans = true;
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index1, int index2) {
        this.heapArray = array;
        this.swapTrans = true;
        this.index = index1;
        this.index2 = index2;
    }

    SnapshotElement(int length) {
        this.heapArray = new ArrayList(length);
    }

    ArrayList getHeapArray() {
        return heapArray;
    }

    public void setInsertTrans() {
        this.insertTrans = true;
    }

    public boolean isInsertTrans() {
        return this.insertTrans;
    }

    public void setDeleteTrans() {
        this.deleteTrans = true;
    }

    public boolean isDeleteTrans() {
        return this.deleteTrans;
    }

    public void setSwapTrans() {
        this.swapTrans = true;
    }

    public boolean isSwapTrans() {
        return this.swapTrans;
    }
}
