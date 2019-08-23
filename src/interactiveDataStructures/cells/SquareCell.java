package interactiveDataStructures.cells;

import interactiveDataStructures.cells.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareCell extends Cell {

    private void initialize() {
        super.shape(new Rectangle(80, 80), Color.WHITE, Color.BLACK);
    }

    public SquareCell() {
        initialize();
    }

    public SquareCell(String content) {
        super(content);
        initialize();
    }

    public SquareCell(Integer content) {
        super(content);
        initialize();
    }

}
