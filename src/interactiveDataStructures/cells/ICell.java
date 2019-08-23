package interactiveDataStructures.cells;

import javafx.animation.FillTransition;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public interface ICell {
    public void setContent(String content);
    public void setContent(Integer content);
    public String getContent();
    public void setColor(Color color);
    public FillTransition temporaryColorChange(Color color);
    public FillTransition revertColorChange();
    public Color getColor();
    public double getXPosition();
    public double getYPosition();
}
