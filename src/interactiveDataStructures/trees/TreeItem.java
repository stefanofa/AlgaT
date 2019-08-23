package interactiveDataStructures.trees;

import interactiveDataStructures.InteractiveItem;
import interactiveDataStructures.cells.CircleCell;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.Observable;
import java.util.Observer;

public class TreeItem extends InteractiveItem<Integer> {
    private TreeItem parent;
    private TreeItem leftChild;
    private TreeItem rightChild;
    public Integer tempLevel;
    public double tempX;

    public TreeItem(Integer el) {
        super(el);
        if (el != null)
            this.cell = new CircleCell(el);
    }

    public TreeItem getParent() {
        return parent;
    }

    public TreeItem getLeftChild() {
        return leftChild;
    }

    public TreeItem getRightChild() {
        return rightChild;
    }

    public void deleteLeft() {
        leftChild.deleteLeft();
        leftChild.deleteRight();
        leftChild = null;
    }

    public void deleteRight() {
        rightChild.deleteLeft();
        rightChild.deleteRight();
        rightChild = null;
    }

    public void insertLeft(TreeItem t) {
        leftChild = t;
        t.parent = this;
    }

    public void insertRight(TreeItem t) {
        rightChild = t;
        t.parent = this;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }

    public Integer height() {
        if (parent == null)
            return 0;
        else return 1 + parent.height();
    }

    public void unhighlightAll() {
        unhighlight();
        if (leftChild != null)
            leftChild.unhighlightAll();
        if (rightChild != null)
            rightChild.unhighlightAll();
    }
}
