package snapshot;

import java.util.ArrayList;

public class SnapshotElement extends Object {


    private ArrayList heapArray;

    boolean insertTrans = false;
    int     insertIndex = 0;
    boolean deleteTrans = false;
    int     deleteIndex = 0;
    boolean swapTrans   = false;
    int[]   swapIndexes = {0 , 0};

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

    public void setInsertIndex(int index) {
        this.insertIndex = index;
    }

    public int getInsertIndex() {
        return this.insertIndex;
    }

    public void setDeleteTrans() {
        this.deleteTrans = true;
    }

    public boolean isDeleteTrans() {
        return this.deleteTrans;
    }

    public void setDeleteIndex(int index) {
        this.deleteIndex = index;
    }

    public int getDeleteIndex() {
        return this.deleteIndex;
    }

    public void setSwapTrans() {
        this.swapTrans = true;
    }

    public boolean isSwapTrans() {
        return this.swapTrans;
    }

    public void setSwapIndexes(int a, int b) {
        this.swapIndexes[0] = a;
        this.swapIndexes[1] = b;
    }

    public int[] getSwapIndexes() {
        return this.swapIndexes;
    }
}
