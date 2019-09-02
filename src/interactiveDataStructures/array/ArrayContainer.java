package interactiveDataStructures.array;

import interactiveDataStructures.cells.Cell;
import interactiveDataStructures.cells.SquareCell;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayContainer extends Pane {

    private int size = 0;

    public void add(ArrayItem el) {
        SquareCell cell = (SquareCell) el.getCell();
        cell.setLayoutX(80 * size);
        size++;
        this.getChildren().add(cell);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), cell);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        ft.play();
    }

    public void add(int index, ArrayItem el) {
        List<Node> items = this.getChildren();
        Node toAdd = el.getCell();
        toAdd.setLayoutX(80 * index);
        items.add(toAdd);
        size++;

        ParallelTransition parT = new ParallelTransition();
        for (int i = index; i < items.size(); i++) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), items.get(i));
            tt.setByX(80f);
            parT.getChildren().add(tt);
        }

        FadeTransition ft = new FadeTransition(Duration.millis(1000), toAdd);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        SequentialTransition seqT = new SequentialTransition(parT, ft);

        seqT.play();
    }

    public void addAll(ArrayList<ArrayItem> a) {
        size = 0;
        ParallelTransition parT = new ParallelTransition();
        for (ArrayItem item : a) {
            SquareCell cell = (SquareCell) item.getCell();
            cell.setLayoutX(size * 80);
            size++;
            this.getChildren().add(cell);
            FadeTransition ft = new FadeTransition(Duration.millis(1000), cell);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            parT.getChildren().add(ft);
        }
        parT.play();
    }

    public void removeAt(int index) {
        List<Node> items = this.getChildren();
        Node toRemove = items.get(index);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), toRemove);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                items.remove(toRemove);
            }
        });

        ParallelTransition parT = new ParallelTransition();
        for (int i = index + 1; i < items.size(); i++) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), items.get(i));
            tt.setByX(-80f);
            parT.getChildren().add(tt);
        }

        SequentialTransition seqT = new SequentialTransition(ft, parT);
        size--;

        seqT.play();
    }

    public void swap(ArrayItem a1, ArrayItem a2) {
        Cell toSwap1 = a1.getCell();
        Cell toSwap2 = a2.getCell();

        double offset = toSwap2.getXPosition() - toSwap1.getXPosition();

        SquareCell cell1 = (SquareCell) toSwap1;
        SquareCell cell2 = (SquareCell) toSwap2;

        FillTransition ft1 = cell1.temporaryColorChange(Color.YELLOW);
        FillTransition ft2 = cell2.temporaryColorChange(Color.YELLOW);
        ParallelTransition parHighlight = new ParallelTransition(ft1, ft2);

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(1), toSwap1);
        tt1.setByX(offset);
        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(1), toSwap2);
        tt2.setByX(-offset);
        ParallelTransition parTranslate = new ParallelTransition(tt1, tt2);

        FillTransition rt1 = cell1.revertColorChange();
        FillTransition rt2 = cell2.revertColorChange();
        ParallelTransition resetHighlight = new ParallelTransition(rt1, rt2);

        SequentialTransition seqT = new SequentialTransition(parHighlight, parTranslate, resetHighlight);

        seqT.play();
    }

}
