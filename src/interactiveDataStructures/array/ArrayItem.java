package interactiveDataStructures.array;

import interactiveDataStructures.InteractiveItem;
import interactiveDataStructures.cells.SquareCell;

public class ArrayItem extends InteractiveItem<Integer> {
    public ArrayItem(Integer content) {
        super(content, new SquareCell());
    }
}
