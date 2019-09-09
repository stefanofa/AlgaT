package interactiveDataStructures.trees;

import interactiveDataStructures.InteractiveItem;
import interactiveDataStructures.cells.CircleCell;

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

    public void setParent(TreeItem parent) {
        this.parent = parent;
    }

    public TreeItem getLeftChild() {
        return leftChild;
    }

    public TreeItem getRightChild() {
        return rightChild;
    }

    public void deleteLeft() {
        if (leftChild != null) {
            leftChild.deleteLeft();
            leftChild.deleteRight();
            leftChild = null;
        }
    }

    public void deleteRight() {
        if (rightChild != null) {
            rightChild.deleteLeft();
            rightChild.deleteRight();
            rightChild = null;
        }
    }

    public void insertLeft(TreeItem t) {
        leftChild = t;
        if (t != null)
            t.parent = this;
    }

    public void insertRight(TreeItem t) {
        rightChild = t;
        if (t != null)
            t.parent = this;
    }
}
