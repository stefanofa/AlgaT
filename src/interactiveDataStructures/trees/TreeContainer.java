package interactiveDataStructures.trees;

import config.Config;
import interactiveDataStructures.cells.Cell;
import interactiveDataStructures.cells.CircleCell;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeContainer extends Pane {

    public void swap(TreeItem t1, TreeItem t2) {
        CircleCell c1 = (CircleCell) t1.getCell();
        CircleCell c2 = (CircleCell) t2.getCell();

        double x1 = c1.getXPosition();
        double x2 = c2.getXPosition();
        double y1 = c1.getYPosition();
        double y2 = c2.getYPosition();

        double deltaX = x2 - x1;
        double deltaY = y2 - y1;

        TranslateTransition tt1 = new TranslateTransition(Config.ANIMATION_DURATION, c1);
        tt1.setByX(deltaX);
        tt1.setByY(deltaY);

        TranslateTransition tt2 = new TranslateTransition(Config.ANIMATION_DURATION, c2);
        tt2.setByX(-deltaX);
        tt2.setByY(-deltaY);

        // Preparazione delle animazioni di evidenziamento e de-evidenziamento
        FillTransition ft1 = c1.temporaryColorChange(Color.YELLOW);
        FillTransition ft2 = c2.temporaryColorChange(Color.YELLOW);
        FillTransition rt1 = c1.revertColorChange();
        FillTransition rt2 = c2.revertColorChange();

        // Implementazione della sequenzialità/parallelismo tra le animazioni
        ParallelTransition highlight = new ParallelTransition(ft1, ft2);
        ParallelTransition pt = new ParallelTransition(tt1, tt2);
        ParallelTransition unhighlight = new ParallelTransition(rt1, rt2);
        SequentialTransition st = new SequentialTransition(highlight, pt, unhighlight);

        // Alla fine delle animazioni occorre riportare gli elementi in primo piano, altrimenti compare la linea sopra di essi
        st.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                c1.toFront();
                c2.toFront();
            }
        });
        st.play();
    }

    public void load(BinaryTree tree) {
        this.getChildren().clear();
        int n = tree.size();
        int height = (int) (Math.log(n) / Math.log(2));
        int level = 0;

        TreeItem t = tree.getRoot();
        t.tempLevel = 0;
        Queue<TreeItem> q = new LinkedList<TreeItem>();
        q.add(t);
        // La variabile j identifica la posizione dell'elemento orizzontalmente, ogni volta che "si va a capo" viene riazzerata
        int j = -1;

        while (!q.isEmpty()) {
            t = q.remove();

            // Se il nuovo elemento in coda è al livello inferiore aumento level e azzero j, altrimenti incremento j
            if (t.tempLevel > level) {
                level++;
                j = 0;
            } else
                j++;

            // Posizionamento cella
            Cell c = t.getCell();
            t.tempX = Math.pow(2, height - level) * (Config.HALF_CELL_SPACE + Config.CELL_SPACE * j);
            c.setLayoutX(t.tempX);
            c.setLayoutY(Config.TREE_VERTICAL_OFFSET * level);
            c.toFront();
            this.getChildren().add(c);

            // Tracciamento linea di collegamento padre-figlio
            TreeItem p = t.getParent();
            if (p != null) {
                Line line = new Line();
                line.setStartX(p.tempX + Config.HALF_CELL_SIZE);
                line.setStartY((level - 1) * Config.TREE_VERTICAL_OFFSET + Config.HALF_CELL_SIZE);
                line.setEndX(t.tempX + Config.HALF_CELL_SIZE);
                line.setEndY(level * Config.TREE_VERTICAL_OFFSET + Config.HALF_CELL_SIZE);
                this.getChildren().add(line);
                line.toBack();
                p.getCell().toFront();
                t.getCell().toFront();
            }

            // Aggiunta figli destro e sinistro (se presenti) alla coda BFS
            TreeItem l = t.getLeftChild();
            TreeItem r = t.getRightChild();
            if (l != null) {
                l.tempLevel = level + 1;
                q.add(l);
            }
            if (r != null) {
                r.tempLevel = level + 1;
                q.add(r);
            }
        }
    }
}
