package graphic.array;

import graphic.cells.BoxCell;
import javafx.scene.paint.Color;

public class GraphicArrayItem {
    private Integer key;
    private Status status = Status.DEFAULT;
    private BoxCell cell = new BoxCell();

    public GraphicArrayItem(int key) {
        setKey(key);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
        cell.setValue(key);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
        cell.setColor(getColor(status));
    }

    public void archive() {
        setStatus(Status.ARCHIVED);
    }

    public BoxCell getCell() {
        return this.cell;
    }

    public void highlight() {
        cell.highlight();
    }

    private Color getColor(Status status) {
        if (status == Status.DEFAULT)
            return Color.WHITE;
        else if (status == Status.ARCHIVED)
            return Color.GRAY;
        else if (status == Status.DANGER)
            return Color.RED;
        else if (status == Status.WARNING)
            return Color.ORANGE;
        else if (status == Status.SUCCESS)
            return Color.GREEN;
        else if (status == Status.HIGHLIGHTED)
            return Color.YELLOW;
        else return Color.BLACK;
    }

}
