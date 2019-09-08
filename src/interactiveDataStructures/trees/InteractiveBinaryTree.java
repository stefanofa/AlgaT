package interactiveDataStructures.trees;

import interactiveDataStructures.Status;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class InteractiveBinaryTree extends Parent {
    BinaryTree tree = new BinaryTree();
    TreeContainer container = new TreeContainer();

    public InteractiveBinaryTree() {
        this.getChildren().add(container);
    }
    public InteractiveBinaryTree(ArrayList<Integer> a) {
        this.getChildren().add(container);
        load(a);
    }

    private void makeClickable(TreeItem t) {
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (tree.getSelected() == t) {
                    tree.select(null);
                    t.unhighlight();
                } else {
                    tree.select(t);
                    tree.unhighlightAll();
                    t.highlight();
                }
            }
        });
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public void insertRoot(Integer el) {
        if (tree.getRoot() == null) {
            TreeItem t = new TreeItem(el);
            makeClickable(t);
            tree.insertRoot(t);
            container.insertRoot(t);
        }
    }

    public void insert(Integer el) {
        TreeItem t = new TreeItem(el);
        tree.insert(t);
        TreeItem p = t.getParent();
        if (p.getLeftChild() == t)
            container.insertLeft(tree, p, t);
        else
            container.insertRight(tree, p, t);
    }

    /*
    public void insertLeft(Integer el) {
        TreeItem cur = tree.getSelected();
        if (cur != null && cur.getLeftChild() == null) {
            TreeItem l = new TreeItem(el);
            makeClickable(l);
            container.insertLeft(tree, cur, l);
            tree.insertLeft(cur, l);
        }
    }

    public void insertRight(Integer el) {
        TreeItem cur = tree.getSelected();
        if (cur != null && cur.getRightChild() == null) {
            TreeItem r = new TreeItem(el);
            makeClickable(r);
            container.insertRight(tree, cur, r);
            tree.insertRight(cur, r);
        }
    }

    public void insertLeft(TreeItem l) {
        TreeItem cur = tree.getSelected();
        if (cur != null && cur.getLeftChild() == null) {
            makeClickable(l);
            container.insertLeft(tree, cur, l);
            tree.insertLeft(cur, l);
        }
    }

    public void insertRight(TreeItem r) {
        TreeItem cur = tree.getSelected();
        if (cur != null && cur.getRightChild() == null) {
            makeClickable(r);
            container.insertRight(tree, cur, r);
            tree.insertRight(cur, r);
        }
    }
    */
    public void swap(TreeItem t1, TreeItem t2) {
        tree.swap(t1, t2);
        container.swap(t1, t2);
    }

    public void swap(int i1, int i2) {
        TreeItem t1 = tree.getByIndex(i1);
        TreeItem t2 = tree.getByIndex(i2);
        tree.swap(t1, t2);
        container.swap(t1, t2);
    }

    public void swapElements(Integer e1, Integer e2) {
        TreeItem t1 = tree.findOne(e1);
        TreeItem t2 = tree.findOne(e2);
        swap(t1, t2);
    }

    public void load(ArrayList<Integer> a) {
        tree.loadHeap(a);
        container.load(tree);
    }

    public void archive(int i) {
        TreeItem t = tree.getByIndex(i);
        t.archive();
    }

    public void unarchive(int i) {
        TreeItem t = tree.getByIndex(i);
        t.unarchive();
    }

    public void highlight(int i) {
        TreeItem t = tree.getByIndex(i);
        t.highlight();
    }

    public void unhighlight(int i) {
        TreeItem t = tree.getByIndex(i);
        t.setStatus(Status.DEFAULT);
    }
}
