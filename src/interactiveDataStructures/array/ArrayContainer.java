package interactiveDataStructures.array;

import config.Config;
import interactiveDataStructures.cells.Cell;
import interactiveDataStructures.cells.IndexCell;
import interactiveDataStructures.cells.SquareCell;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ArrayContainer extends Pane {

    private int size = 0;

    public void add(ArrayItem el) {
        SquareCell cell = (SquareCell) el.getCell();
        cell.setLayoutX(Config.CELL_SIZE * size);
        IndexCell ic = new IndexCell(size + 1);
        ic.setLayoutX(Config.CELL_SIZE * size);
        ic.setLayoutY(Config.CELL_SIZE);
        size++;
        this.getChildren().addAll(cell, ic);

        FadeTransition ft = new FadeTransition(Config.ANIMATION_DURATION, cell);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        ft.play();
    }

    public void add(int index, ArrayItem el) {
        ObservableList<Node> items = this.getChildren();
        Node toAdd = el.getCell();
        toAdd.setLayoutX(Config.CELL_SIZE * index);
        IndexCell ic = new IndexCell(size + 1);
        ic.setLayoutX(Config.CELL_SIZE * size);
        ic.setLayoutY(Config.CELL_SIZE);
        items.addAll(toAdd, ic);
        size++;

        ParallelTransition parT = new ParallelTransition();
        for (int i = index; i < items.size(); i++) {
            TranslateTransition tt = new TranslateTransition(Config.ANIMATION_DURATION, items.get(i));
            tt.setByX(Config.CELL_SIZE);
            parT.getChildren().add(tt);
        }

        FadeTransition ft = new FadeTransition(Config.ANIMATION_DURATION, toAdd);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        SequentialTransition seqT = new SequentialTransition(parT, ft);

        seqT.play();
    }

    void addAll(ArrayList<ArrayItem> a) {
        this.getChildren().clear();
        size = 0;
        ParallelTransition parT = new ParallelTransition();
        for (ArrayItem item : a) {
            SquareCell cell = (SquareCell) item.getCell();
            cell.setLayoutX(size * Config.CELL_SIZE);
            IndexCell ic = new IndexCell(size + 1);
            ic.setLayoutX(Config.CELL_SIZE * size);
            ic.setLayoutY(Config.CELL_SIZE);
            size++;
            this.getChildren().addAll(cell, ic);
            FadeTransition ft = new FadeTransition(Config.ANIMATION_DURATION, cell);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            parT.getChildren().add(ft);
        }
        parT.play();
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

        TranslateTransition tt1 = new TranslateTransition(Config.ANIMATION_DURATION, toSwap1);
        tt1.setByX(offset);
        TranslateTransition tt2 = new TranslateTransition(Config.ANIMATION_DURATION, toSwap2);
        tt2.setByX(-offset);
        ParallelTransition parTranslate = new ParallelTransition(tt1, tt2);

        FillTransition rt1 = cell1.revertColorChange();
        FillTransition rt2 = cell2.revertColorChange();
        ParallelTransition resetHighlight = new ParallelTransition(rt1, rt2);

        SequentialTransition seqT = new SequentialTransition(parHighlight, parTranslate, resetHighlight);

        seqT.play();
    }

}
