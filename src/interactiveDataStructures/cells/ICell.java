package interactiveDataStructures.cells;

import javafx.animation.FillTransition;
import javafx.scene.paint.Color;

public interface ICell {
    void setContent(String content);
    void setContent(Integer content);
    String getContent();
    void setColor(Color color);
    FillTransition temporaryColorChange(Color color);
    FillTransition revertColorChange();
    Color getColor();
    double getXPosition();
    double getYPosition();
}
