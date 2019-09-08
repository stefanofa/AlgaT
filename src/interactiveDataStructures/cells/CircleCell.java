package interactiveDataStructures.cells;

import baseController.Config;
import interactiveDataStructures.cells.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleCell extends Cell {

    private void initialize() {
        super.shape(new Circle(Config.HALF_CELL_SIZE), Color.WHITE, Color.BLACK);
    }

    public CircleCell() {
        initialize();
    }

    public CircleCell(String content) {
        super(content);
        initialize();
    }

    public CircleCell(Integer content) {
        super(content);
        initialize();
    }

}
