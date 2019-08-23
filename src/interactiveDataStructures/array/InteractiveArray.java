package interactiveDataStructures.array;

import interactiveDataStructures.array.ArrayContainer;
import interactiveDataStructures.array.ArrayItem;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteractiveArray extends Parent {
    List<ArrayItem> list = new ArrayList<ArrayItem>();
    ArrayContainer container = new ArrayContainer();

    public InteractiveArray() {
        this.getChildren().add(container);
    }

    public void push(int el) {
        ArrayItem item = new ArrayItem(el);
        list.add(item);
        container.add(item);
    }

    public void insertAt(int index, int el) {
        ArrayItem item = new ArrayItem(el);
        list.add(index, item);
        container.add(index, item);
    }

    public int indexOf(int el) {
        int i = 0;
        int index = -1;
        while (index < 0 && i < list.size()) {
            if (list.get(i).getContent() == el)
                index = i;
            i++;
        }
        return index;
    }

    public void removeAt(int index) {
        if (list.size() > 0 && index < list.size()) {
            list.remove(index);
            container.removeAt(index);
        }
    }

    public void remove(int el) {
        int index = indexOf(el);
        if (index >= 0)
            removeAt(index);
    }

    public void highlightAt(int index) {
        ArrayItem item = list.get(index);
        item.highlight();
    }

    public void swap(int index1, int index2) {
        if (index1 != index2 && index1 >= 0 && index2 >= 0 && index1 < list.size() && index2 < list.size()) {
            Collections.swap(list, index1, index2);
            container.swap(index1, index2);
        }
    }

    public void archiveAt(int index) {
        list.get(index).archive();
    }
}
