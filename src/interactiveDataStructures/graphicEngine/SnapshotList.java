package interactiveDataStructures.graphicEngine;


import java.util.ArrayList;

public class SnapshotList {

    private ArrayList <SnapshotElement> list = new ArrayList<SnapshotElement>();
    private int index = 0;
    private boolean lastOperationForward = true;

    public SnapshotList() { }
    public SnapshotList(ArrayList<Integer> start) {
        this.list.add(new SnapshotElement(start));
    }

    public void addElement(SnapshotElement el) {
        list.add(el);
    }

    public SnapshotElement getCurrentSnapshot() {
        return list.get(index);
    }

    public SnapshotElement getFirst() {
        index = 0;
        return getCurrentSnapshot();
    }

    public SnapshotElement next() {
        if (index < list.size()-1) {
            if (!lastOperationForward)
                index--;
            index++;
            lastOperationForward = true;
        }
        return getCurrentSnapshot();
    }

    public SnapshotElement prev() {
        if (index > 0) {
            if (lastOperationForward)
                index++;
            index--;
            lastOperationForward = false;
        }
        return getCurrentSnapshot();
    }

    public SnapshotElement getPrev() {
        return list.get(index - 1);
    }

    public SnapshotElement getNext() {
        return list.get(index + 1);
    }

    public boolean ended() {
        return index == list.size() - 1;
    }

    public boolean atStart() {
        return index == 0;
    }

}
