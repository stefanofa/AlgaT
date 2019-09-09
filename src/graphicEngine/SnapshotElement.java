package graphicEngine;

import java.util.ArrayList;

public class SnapshotElement extends Object {

    private ArrayList heapArray;

    boolean swapTrans    = false;
    boolean archiveTrans = false;
    boolean highlightTrans = false;
    int index;
    int index2;
    String subProcedure = null;
    String atomicOperation = null;

    public SnapshotElement(ArrayList<Integer> array) {
        this.heapArray = new ArrayList<Integer>(array);
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index) {
        this.heapArray = array;
        this.index = index;
        if (op == "archive")
            this.archiveTrans = true;
        else if (op == "highlight")
            this.highlightTrans = true;
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index1, int index2, String subProcedure, String atomicOperation) {
        this.heapArray = array;
        this.swapTrans = true;
        this.index = index1;
        this.index2 = index2;
        this.subProcedure = subProcedure;
        this.atomicOperation = atomicOperation;
    }

    public ArrayList getHeapArray() {
        return heapArray;
    }
    public int getIndex() { return index; }
    public int getIndex2() { return index2; }

    public boolean isSwapTrans() {
        return this.swapTrans;
    }
    public boolean isArchiveTrans() { return this.archiveTrans; }
    public boolean isHighlightTrans() { return this.highlightTrans; }
    public String getSubProcedure() { return this.subProcedure; }
    public String getAtomicOperation() { return this.atomicOperation; }

}
