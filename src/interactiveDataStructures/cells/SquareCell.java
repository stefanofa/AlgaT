package interactiveDataStructures.cells;

import config.Config;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SquareCell extends Cell {

    private void initialize() {
        super.shape(new Rectangle(Config.CELL_SIZE, Config.CELL_SIZE), Color.WHITE, Color.BLACK);
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
