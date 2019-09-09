package interactiveDataStructures.cells;

import config.Config;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class IndexCell extends Cell {
    public IndexCell(Integer index) {
        text.setFont(Font.font(Config.FONT_SIZE / 2));
        text.setText(index.toString());
        super.shape(new Rectangle(Config.CELL_SIZE, Config.CELL_SIZE / 3), Color.LIGHTGRAY, Color.BLACK);
    }
}
