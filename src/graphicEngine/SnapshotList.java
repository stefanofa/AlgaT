package graphicEngine;


import java.util.ArrayList;

public class SnapshotList {

    private ArrayList <SnapshotElement> list = new ArrayList<SnapshotElement>();
    private int index = 0;
    private boolean lastOperationForward = true; // Serve per rilevare se sto cambiando direzione di scorrimento

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

    // Se sto cambiando direzione di scorrimento (da prev a next)
    // semplicemente effettuo l'operazione inversa a quella appena analizzata
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

    public boolean ended() {
        return index == list.size() - 1;
    }

    public boolean atStart() {
        return index == 0;
    }

}
