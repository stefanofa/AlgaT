package graphic.array;

import graphic.parents.RowArray;
import javafx.scene.Parent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphicArray extends Parent {

    private List<GraphicArrayItem> items = new ArrayList<GraphicArrayItem>();
    private RowArray row = new RowArray();

    public GraphicArray() {
        this.getChildren().add(row);
    }

    public void push(int el) {
        GraphicArrayItem item = new GraphicArrayItem(el);
        items.add(item);
        row.add(item);
    }

    public void insertAt(int index, int el) {
        GraphicArrayItem item = new GraphicArrayItem(el);
        items.add(index, item);
        row.add(index, item);
    }

    public int indexOf(int el) {
        int i = 0;
        int index = -1;
        while (index < 0 && i < items.size()) {
            if (items.get(i).getKey() == el)
                index = i;
            i++;
        }
        return index;
    }

    public void removeAt(int index) {
        if (items.size() > 0 && index < items.size()) {
            items.remove(index);
            row.removeAt(index);
        }
    }

    public void remove(int el) {
        int index = indexOf(el);
        if (index >= 0)
            removeAt(index);
    }

    public void highlightAt(int index) {
        GraphicArrayItem item = items.get(index);
        item.highlight();
    }

    public void swap(int index1, int index2) {
        if (index1 != index2 && index1 >= 0 && index2 >= 0 && index1 < items.size() && index2 < items.size()) {
            Collections.swap(items, index1, index2);
            row.swap(index1, index2);
        }
    }

    public void archiveAt(int index) {
        items.get(index).archive();
    }

}
