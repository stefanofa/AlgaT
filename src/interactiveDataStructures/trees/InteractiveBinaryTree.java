package interactiveDataStructures.trees;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

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
}
