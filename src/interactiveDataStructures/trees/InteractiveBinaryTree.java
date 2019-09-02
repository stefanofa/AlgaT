package interactiveDataStructures.trees;

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

    public void swap(TreeItem t1, TreeItem t2) {
        tree.swap(t1, t2);
        container.swap(t1, t2);
    }

    public void swapElements(Integer e1, Integer e2) {
        TreeItem t1 = tree.findOne(e1);
        TreeItem t2 = tree.findOne(e2);
        swap(t1, t2);
    }

    public void load(ArrayList<Integer> a) {
        int index = 0;
        if (!a.isEmpty()) {
            insertRoot(a.get(index));
            index++;
            TreeItem t = tree.getRoot();
            Queue<TreeItem> q = new LinkedList<TreeItem>();
            q.add(t);

            while (index < a.size()) {
                t = q.remove();
                tree.select(t);

                TreeItem l = new TreeItem(a.get(index));
                index++;
                insertLeft(l);
                q.add(l);

                if (index < a.size()) {
                    TreeItem r = new TreeItem(a.get(index));
                    index++;
                    insertRight(r);
                    q.add(r);
                }
            }
        }
    }
}
