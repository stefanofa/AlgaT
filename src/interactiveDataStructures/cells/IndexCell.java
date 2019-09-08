package interactiveDataStructures.cells;

import baseController.Config;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class IndexCell extends Cell {
    public IndexCell(int index) {
        text.setFont(Font.font(Config.FONT_SIZE / 4));
        super.shape(new Rectangle(Config.CELL_SIZE, Config.CELL_SIZE / 5), Color.WHITE, Color.GRAY);
    }
}
