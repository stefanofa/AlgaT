package interactiveDataStructures.trees;

import interactiveDataStructures.Status;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InteractiveBinaryTree extends Parent {
    BinaryTree tree = new BinaryTree();
    TreeContainer container = new TreeContainer();

    public InteractiveBinaryTree() {
        this.getChildren().add(container);
    }

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
