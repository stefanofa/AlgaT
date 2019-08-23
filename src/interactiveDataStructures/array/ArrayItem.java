package interactiveDataStructures.array;

import interactiveDataStructures.InteractiveItem;
import interactiveDataStructures.cells.SquareCell;

public class ArrayItem extends InteractiveItem<Integer> {

    public ArrayItem() {
        super(new SquareCell());
    }

    public ArrayItem(String content) {
        super(Integer.parseInt(content), new SquareCell());
    }

    public ArrayItem(Integer content) {
        super(content, new SquareCell());
    }
}
