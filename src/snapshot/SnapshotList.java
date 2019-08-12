package snapshot;


import java.util.ArrayList;

public class SnapshotList {

    private ArrayList <SnapshotElement> list;
    private int iter = 0;

    SnapshotList() {
        this.list = new ArrayList<SnapshotElement>();
    }

    public void addElement(SnapshotElement el) {
        list.add(el);
    }


    private SnapshotElement getActualElement() {
        return list.get(iter);
    }

    public SnapshotElement getFirst() {
        iter = 0;
        return getActualElement();
    }

    public SnapshotElement getNextElement() {
        if (iter < list.size()-1)
            iter ++;
        return getActualElement();
    }

    public SnapshotElement getPrevElement() {
        if(iter > 0)
            iter --;
        return getActualElement();
    }


}
