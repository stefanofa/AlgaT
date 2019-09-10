package interactiveDataStructures.array;

import interactiveDataStructures.array.ArrayContainer;
import interactiveDataStructures.array.ArrayItem;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Collections;

public class InteractiveArray extends Parent {
    ArrayList<ArrayItem> list = new ArrayList<ArrayItem>();
    ArrayContainer container = new ArrayContainer();

    public InteractiveArray() {
        this.getChildren().add(container);
    }

    public void highlightAt(int index) {
        ArrayItem item = list.get(index);
        item.highlight();
    }

    public void unhighlightAt(int index) {
        ArrayItem item = list.get(index);
        item.unhighlight();
    }

    public void swap(int index1, int index2) {
        if (index1 != index2 && index1 >= 0 && index2 >= 0 && index1 < list.size() && index2 < list.size()) {
            container.swap(list.get(index1), list.get(index2));
            Collections.swap(list, index1, index2);
        }
    }

    public void archiveAt(int index) {
        list.get(index).archive();
    }

    public void unarchiveAt(int index) {
        list.get(index).unarchive();
    }

    public void load(ArrayList<Integer> a) {
        list.clear();
        for (Integer x : a)
            list.add(new ArrayItem(x));
        container.addAll(list);
    }
}
