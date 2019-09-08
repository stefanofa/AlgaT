package interactiveDataStructures.trees;

import baseController.Config;
import interactiveDataStructures.cells.Cell;
import interactiveDataStructures.cells.CircleCell;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeContainer extends Pane {

    public TreeContainer() {
        this.setPrefSize(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
    }

    public void insertRoot(TreeItem t) {
        CircleCell c = (CircleCell) t.getCell();
        FadeTransition ft = new FadeTransition(Config.ANIMATION_DURATION, c);
        ft.setFromValue(0);
        ft.setToValue(1);

        this.getChildren().add(c);
        ft.play();
    }

    private ParallelTransition addLevel(BinaryTree tree) {
        List<Node> nodes = this.getChildren();
        int k = 0;
        while (k < nodes.size()) {
            if (nodes.get(k) instanceof Line)
                nodes.remove(k);
            else
                k++;
        }

        ParallelTransition restructure = new ParallelTransition();
        Queue<TreeItem> q = new LinkedList<TreeItem>();
        int i = 0;
        int j = -1;
        int h = tree.getHeight();

        tree.getRoot().tempLevel = 0;
        q.add(tree.getRoot());

        while (!q.isEmpty()) {
            TreeItem t = q.remove();
            TreeItem l = t.getLeftChild();
            TreeItem r = t.getRightChild();

            if (t.tempLevel > i) {
                i = t.tempLevel;
                j = 0;
            } else
                j++;

            if (t.getContent() != null) {
                if (l == null)
                    l = new TreeItem(null);
                l.tempLevel = i + 1;
                q.add(l);

                if (r == null)
                    r = new TreeItem(null);
                r.tempLevel = i + 1;
                q.add(r);

                TranslateTransition tt = new TranslateTransition(Config.ANIMATION_DURATION, t.getCell());
                double offset = Math.pow(2, h - i) * (Config.HALF_CELL_SPACE + Config.CELL_SPACE * j);
                tt.setByX(offset);
                t.tempX = t.getCell().getXPosition() + offset;
                restructure.getChildren().add(tt);

                TreeItem p = t.getParent();

                if (p != null) {
                    Line line = new Line();
                    line.setStartX(p.tempX);
                    line.setStartY((i - 1) * Config.TREE_VERTICAL_OFFSET + Config.HALF_CELL_SIZE);
                    line.setEndX(t.tempX);
                    line.setEndY(i * Config.TREE_VERTICAL_OFFSET + Config.HALF_CELL_SIZE);
                    this.getChildren().add(line);
                    line.toBack();
                    p.getCell().toFront();
                    t.getCell().toFront();

                    FadeTransition ft = new FadeTransition(Config.ANIMATION_DURATION, line);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    restructure.getChildren().add(ft);
                }
            }
        }

        return restructure;
    }

    private void insert(BinaryTree tree, TreeItem t, TreeItem c, int sign) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        int h = tree.getHeight();

        if (tree.getHeight() == t.height()) {
            sequentialTransition.getChildren().add(addLevel(tree));
            h++;
        }

        TreeContainer that = this;
        int finalH = h;
        sequentialTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CircleCell parent = (CircleCell) t.getCell();
                CircleCell child = (CircleCell) c.getCell();

                double x = parent.getXPosition();
                double y = parent.getYPosition();
                int i = t.height() + 1;
                child.setLayoutX(x - Config.HALF_CELL_SIZE + sign * Config.HALF_CELL_SPACE * Math.pow(2, finalH - i));
                child.setLayoutY(y + Config.TREE_VERTICAL_OFFSET - Config.HALF_CELL_SIZE);

                FadeTransition ft1 = new FadeTransition(Config.ANIMATION_DURATION, child);
                ft1.setFromValue(0);
                ft1.setToValue(1);

                Line line = new Line();
                line.setStartX(parent.getXPosition());
                line.setStartY(parent.getYPosition());
                line.setEndX(child.getXPosition() + Config.HALF_CELL_SIZE);
                line.setEndY(child.getYPosition() + Config.HALF_CELL_SIZE);
                line.toBack();

                FadeTransition ft2 = new FadeTransition(Config.ANIMATION_DURATION, line);
                ft2.setFromValue(0);
                ft2.setToValue(1);

                that.getChildren().add(line);
                that.getChildren().add(child);
                parent.toFront();
                child.toFront();

                ParallelTransition p = new ParallelTransition(ft1, ft2);
                p.play();
            }
        });

        sequentialTransition.play();
    }

    public void insertLeft(BinaryTree tree, TreeItem t, TreeItem l) {
        insert(tree, t, l, -1);
    }

    public void insertRight(BinaryTree tree, TreeItem t, TreeItem r) {
        insert(tree, t, r, 1);
    }

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

        FillTransition ft1 = c1.temporaryColorChange(Color.YELLOW);
        FillTransition ft2 = c2.temporaryColorChange(Color.YELLOW);
        FillTransition rt1 = c1.revertColorChange();
        FillTransition rt2 = c2.revertColorChange();

        ParallelTransition highlight = new ParallelTransition(ft1, ft2);
        ParallelTransition pt = new ParallelTransition(tt1, tt2);
        ParallelTransition unhighlight = new ParallelTransition(rt1, rt2);

        SequentialTransition st = new SequentialTransition(highlight, pt, unhighlight);
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
        int n = tree.size();
        int height = (int) (Math.log(n) / Math.log(2));
        System.out.println(n + " " + height);
        int level = 0;
        TreeItem t = tree.getRoot();
        t.tempLevel = 0;
        Queue<TreeItem> q = new LinkedList<TreeItem>();
        q.add(t);
        int j = -1;

        while (!q.isEmpty()) {
            t = q.remove();

            if (t.tempLevel > level) {
                level++;
                j = 0;
            } else
                j++;

            Cell c = t.getCell();
            t.tempX = Math.pow(2, height - level) * (Config.HALF_CELL_SPACE + Config.CELL_SPACE * j);
            c.setLayoutX(t.tempX);
            c.setLayoutY(Config.TREE_VERTICAL_OFFSET * level);
            this.getChildren().add(c);

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
