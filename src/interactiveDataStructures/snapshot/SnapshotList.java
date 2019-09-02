package interactiveDataStructures.snapshot;


import java.util.ArrayList;

public class SnapshotList {

    private ArrayList <SnapshotElement> list = new ArrayList<SnapshotElement>();
    private int index = 0;


    public SnapshotList() { }
    public SnapshotList(ArrayList<Integer> start) {
        this.list.add(new SnapshotElement(start));
    }

    public void addElement(SnapshotElement el) {
        list.add(el);
    }


    private SnapshotElement getActualElement() {
        return list.get(index);
    }

    public SnapshotElement getFirst() {
        index = 0;
        return getActualElement();
    }

    public SnapshotElement getNextElement() {
        if (index < list.size()-1)
            index ++;
        return getActualElement();
    }

    public SnapshotElement getPrevElement() {
        if(index > 0)
            index --;
        return getActualElement();
    }


}
