package interactiveDataStructures.trees;

import interactiveDataStructures.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private TreeItem root = null;
    private Integer height = 0;
    private Integer size = 0;
    private TreeItem selected = null;
    private ArrayList<TreeItem> heapArray = null;

    public BinaryTree() { }

    public TreeItem getRoot() {
        return root;
    }

    public int size() {
        return size;
    }

    public void insertRoot(TreeItem t) {
        root = t;
        size = 1;
        heapArray = new ArrayList<TreeItem>();
        heapArray.add(t);
    }

    public void swap(TreeItem t1, TreeItem t2) {
        TreeItem t;
        TreeItem p1 = t1.getParent();
        TreeItem p2 = t2.getParent();

        if (p1 != null) {
            if (p1.getLeftChild() == t1) {
                p1.insertLeft(t2);
            }
            else {
                p1.insertRight(t2);
            }
        } else
            setRoot(t2);

        if (p2 != null) {
            if (p2.getLeftChild() == t2) {
                p2.insertLeft(t1);
            }
            else {
                p2.insertRight(t1);
            }
        } else
            setRoot(t1);

        t = t1.getLeftChild();
        t1.insertLeft(t2.getLeftChild());
        t2.insertLeft(t);

        t = t1.getRightChild();
        t1.insertRight(t2.getRightChild());
        t2.insertRight(t);

        if (heapArray != null)
            Collections.swap(heapArray, heapArray.indexOf(t1), heapArray.indexOf(t2));
    }

    public void loadHeap(ArrayList<Integer> a) {
        this.heapArray = new ArrayList<TreeItem>();
        if (!a.isEmpty()) {
            int index = 0;
            Queue<TreeItem> q = new LinkedList<TreeItem>();
            TreeItem t = new TreeItem(a.get(index));
            heapArray.add(t);
            insertRoot(t);
            index++;
            q.add(root);

            while (index < a.size()) {
                t = q.remove();

                TreeItem l = new TreeItem(a.get(index));
                t.insertLeft(l);
                heapArray.add(l);
                index++;
                q.add(l);

                if (index < a.size()) {
                    TreeItem r = new TreeItem(a.get(index));
                    t.insertRight(r);
                    heapArray.add(r);
                    index++;
                    q.add(r);
                }
            }
        }
        size = a.size();
    }

    public TreeItem getByIndex(int index) {
        return heapArray.get(index);
    }

    private void setRoot(TreeItem t) {
        root = t;
        t.setParent(null);
    }
}
