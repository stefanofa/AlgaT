package interactiveDataStructures.cells;

import config.Config;
import javafx.animation.FillTransition;
import javafx.geometry.Bounds;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Cell extends StackPane implements ICell {
    protected Shape shape;
    protected Color color;
    protected Text text;
    private Color prevColor;

    private void initialize() {
        text = new Text();
        text.setFont(Font.font(Config.FONT_SIZE));
    }

    protected void shape(Shape shape, Color color, Color stroke) {
        shape.setStroke(stroke);
        shape.setFill(color);
        this.color = color;
        this.shape = shape;
        this.getChildren().addAll(shape, text);
    }

    public Cell() {
        initialize();
    }

    public Cell(String content) {
        initialize();
        setContent(content);
    }

    public Cell(Integer content) {
        initialize();
        setContent(content);
    }

    @Override
    public void setContent(String content) {
        text.setText(content);
    }

    @Override
    public void setContent(Integer content) {
        text.setText(content.toString());
    }

    @Override
    public void setColor(Color color) {
        FillTransition ft = new FillTransition(Config.ANIMATION_DURATION, shape, this.color, color);
        this.color = color;
        ft.play();
    }

    @Override
    public FillTransition temporaryColorChange(Color color) {
        prevColor = this.color;
        FillTransition ft = new FillTransition(Config.ANIMATION_DURATION, shape, this.color, color);
        this.color = color;
        return ft;
    }

    @Override
    public FillTransition revertColorChange() {
        FillTransition ft = new FillTransition(Config.ANIMATION_DURATION, shape, color, prevColor);
        color = prevColor;
        prevColor = null;
        return ft;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getXPosition() {
        Bounds bounds = this.getBoundsInParent();
        return (bounds.getMinX() + bounds.getMaxX()) / 2;
    }

    @Override
    public double getYPosition() {
        Bounds bounds = this.getBoundsInParent();
        return (bounds.getMinY() + bounds.getMaxY()) / 2;
    }
}
