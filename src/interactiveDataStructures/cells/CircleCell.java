package interactiveDataStructures.cells;

import interactiveDataStructures.cells.Cell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleCell extends Cell {

    private void initialize() {
        super.shape(new Circle(40), Color.WHITE, Color.BLACK);
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
