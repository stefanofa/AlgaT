package interactiveDataStructures.array;

import interactiveDataStructures.cells.SquareCell;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.List;

public class ArrayContainer extends HBox {

    public void add(ArrayItem el) {
        SquareCell cell = (SquareCell) el.getCell();
        FadeTransition ft = new FadeTransition(Duration.millis(1000), cell);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        this.getChildren().add(cell);
        ft.play();
    }

    public void add(int index, ArrayItem el) {
        List<Node> items = this.getChildren();
        Node toAdd = el.getCell();

        ParallelTransition parT = new ParallelTransition();
        ParallelTransition reset = new ParallelTransition();
        for (int i = index; i < items.size(); i++) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), items.get(i));
            tt.setByX(80f);
            parT.getChildren().add(tt);

            TranslateTransition r = new TranslateTransition(Duration.millis(1), items.get(i));
            r.setByX(-80f);
            reset.getChildren().add(r);
        }

        FadeTransition ft = new FadeTransition(Duration.millis(1000), toAdd);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);

        parT.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                items.add(index, toAdd);
                ft.play();
                reset.play();
            }
        });

        parT.play();
    }

    public void removeAt(int index) {
        List<Node> items = this.getChildren();
        Node toRemove = items.get(index);

        FadeTransition ft = new FadeTransition(Duration.millis(1000), toRemove);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);

        ParallelTransition parT = new ParallelTransition();
        ParallelTransition reset = new ParallelTransition();
        for (int i = index + 1; i < items.size(); i++) {
            TranslateTransition tt = new TranslateTransition(Duration.millis(1000), items.get(i));
            tt.setByX(-80f);
            parT.getChildren().add(tt);

            TranslateTransition r = new TranslateTransition(Duration.millis(1), items.get(i));
            r.setByX(80f);
            reset.getChildren().add(r);
        }

        SequentialTransition seqT = new SequentialTransition(ft, parT, reset);
        seqT.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                items.remove(index);
            }
        });
        seqT.play();
    }

    public void swap(int index1, int index2) {
        List<Node> items = this.getChildren();
        if (index1 > index2) {
            int tmp = index1;
            index1 = index2;
            index2 = tmp;
        }

        Node toSwap1 = items.get(index1);
        Node toSwap2 = items.get(index2);

        SquareCell cell1 = (SquareCell) toSwap1;
        SquareCell cell2 = (SquareCell) toSwap2;

        FillTransition ft1 = cell1.temporaryColorChange(Color.YELLOW);
        FillTransition ft2 = cell2.temporaryColorChange(Color.YELLOW);
        ParallelTransition parHighlight = new ParallelTransition(ft1, ft2);

        TranslateTransition tt1 = new TranslateTransition(Duration.millis(500), toSwap1);
        tt1.setByX(80f * (index2 - index1));
        TranslateTransition tt2 = new TranslateTransition(Duration.millis(500), toSwap2);
        tt2.setByX(-80f * (index2 - index1));
        ParallelTransition parTranslate = new ParallelTransition(tt1, tt2);

        FillTransition rt1 = cell1.revertColorChange();
        FillTransition rt2 = cell2.revertColorChange();
        ParallelTransition resetHighlight = new ParallelTransition(rt1, rt2);

        SequentialTransition seqT = new SequentialTransition(parHighlight, parTranslate, resetHighlight);

        TranslateTransition r1 = new TranslateTransition(Duration.millis(1), toSwap1);
        r1.setByX(-80f * (index2 - index1));
        TranslateTransition r2 = new TranslateTransition(Duration.millis(1), toSwap2);
        r2.setByX(80f * (index2 - index1));
        ParallelTransition reset = new ParallelTransition(r1, r2);

        int finalIndex1 = index1;
        int finalIndex2 = index2;
        seqT.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Node node2 = items.remove(finalIndex2);
                Node node1 = items.remove(finalIndex1);

                items.add(finalIndex1, node2);
                items.add(finalIndex2, node1);
                reset.play();
            }
        });

        seqT.play();
    }

}
