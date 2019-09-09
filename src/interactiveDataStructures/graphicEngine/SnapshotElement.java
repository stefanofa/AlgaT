package interactiveDataStructures.graphicEngine;

import java.util.ArrayList;

public class SnapshotElement extends Object {

    private ArrayList heapArray;

    boolean insertTrans  = false;
    boolean deleteTrans  = false;
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

    public SnapshotElement(ArrayList<Integer> array, String subProcedure, String atomicOperation) {
        this.heapArray = new ArrayList<Integer>(array);
        this.subProcedure = subProcedure;
        this.atomicOperation = atomicOperation;
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index) {
        this.heapArray = array;
        this.index = index;
        if (op == "insert")
            this.insertTrans = true;
        else if (op == "delete")
            this.deleteTrans = true;
        else if (op == "archive")
            this.archiveTrans = true;
        else if (op == "highlight")
            this.highlightTrans = true;
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index, String subProcedure, String atomicOperation) {
        this.heapArray = array;
        this.index = index;
        if (op == "insert")
            this.insertTrans = true;
        else if (op == "delete")
            this.deleteTrans = true;
        else if (op == "archive")
            this.archiveTrans = true;
        else if (op == "highlight")
            this.highlightTrans = true;
        this.subProcedure = subProcedure;
        this.atomicOperation = atomicOperation;
    }

    public SnapshotElement(ArrayList<Integer> array, String op, int index1, int index2) {
        this.heapArray = array;
        this.swapTrans = true;
        this.index = index1;
        this.index2 = index2;
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

    public boolean isInsertTrans() {
        return this.insertTrans;
    }
    public boolean isDeleteTrans() {
        return this.deleteTrans;
    }
    public boolean isSwapTrans() {
        return this.swapTrans;
    }
    public boolean isArchiveTrans() { return this.archiveTrans; }
    public boolean isHighlightTrans() { return this.highlightTrans; }
    public String getSubProcedure() { return this.subProcedure; }
    public String getAtomicOperation() { return this.atomicOperation; }

}
