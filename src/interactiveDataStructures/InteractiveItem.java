package interactiveDataStructures;

import interactiveDataStructures.cells.Cell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class InteractiveItem<T> extends Observable implements IItem<T> {
    protected T content;
    protected Status status = Status.DEFAULT;
    protected Cell cell;
    private Status prevStatus;

    public InteractiveItem() { }

    public InteractiveItem(T el) {
        this.content = el;
    }

    public InteractiveItem(Cell cell) {
        this.cell = cell;
    }

    public InteractiveItem(T el, Cell cell) {
        this.content = el;
        cell.setContent(el.toString());
        this.cell = cell;
    }

    public final Cell getCell() {
        return cell;
    }

    @Override
    public final void setStatus(Status status) {
        prevStatus = this.status;
        this.status = status;
        cell.setColor(getColorForStatus(status));
    }

    @Override
    public final void highlight() {
        setStatus(Status.HIGHLIGHTED);
    }

    @Override
    public final void unhighlight() {
        if (status == Status.HIGHLIGHTED)
            setStatus(prevStatus);
    }

    @Override
    public final void archive() {
        setStatus(Status.ARCHIVED);
    }

    @Override
    public final void unarchive() {
        setStatus(Status.DEFAULT);
    }

    private static Color getColorForStatus(Status status) {
        if (status == Status.DEFAULT)
            return Color.WHITE;
        else if (status == Status.ARCHIVED)
            return Color.GRAY;
        else if (status == Status.HIGHLIGHTED)
            return Color.YELLOW;
        else return Color.BLACK;
    }

    public void onMouseClicked(Observer o) {
        this.addObserver(o);
    }

    public void setOnMouseClicked(EventHandler<MouseEvent> eventHandler) {
        this.cell.setOnMouseClicked(eventHandler);
    }
}
