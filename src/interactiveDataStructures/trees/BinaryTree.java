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

    public Integer getHeight() {
        return height;
    }

    public TreeItem getRoot() {
        return root;
    }

    public TreeItem getSelected() {
        return selected;
    }

    public void select(TreeItem t) {
        selected = t;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insertRoot(TreeItem t) {
        if (root == null) {
            root = t;
            size = 1;
            heapArray = new ArrayList<TreeItem>();
            heapArray.add(t);
        }
    }

    public void insert(TreeItem t) {
        int lastParentIndex = heapArray.size() / 2 - 1;
        TreeItem p = heapArray.get(lastParentIndex);
        if (p.getRightChild() == null)
            p.insertRight(t);
        else {
            p = heapArray.get(lastParentIndex + 1);
            p.insertLeft(t);
        }
        heapArray.add(t);
    }

    /*
    public void insertLeft(TreeItem t, TreeItem l) {
        if (t.getLeftChild() == null) {
            t.insertLeft(l);
            size++;
            if (l.height() > height)
                height = l.height();
        }
    }

    public void insertRight(TreeItem t, TreeItem r) {
        if (t.getRightChild() == null) {
            t.insertRight(r);
            size++;
            if (r.height() > height)
                height = r.height();
        }
    }
    */

    public LinkedList<TreeItem> find(Integer el) {
        LinkedList<TreeItem> list = new LinkedList<TreeItem>();
        Queue<TreeItem> q = new LinkedList<TreeItem>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeItem t = q.remove();
            if (t.getLeftChild() != null)
                q.add(t.getLeftChild());
            if (t.getRightChild() != null)
                q.add(t.getRightChild());

            if (t.getContent() == el)
                list.add(t);
        }

        return list;
    }

    //BFS
    public TreeItem findOne(Integer el) {
        TreeItem item = null;
        Queue<TreeItem> q = new LinkedList<TreeItem>();
        q.add(root);

        while (!q.isEmpty() && item == null) {
            TreeItem t = q.remove();
            if (t.getLeftChild() != null)
                q.add(t.getLeftChild());
            if (t.getRightChild() != null)
                q.add(t.getRightChild());

            if (t.getContent() == el)
                item = t;
        }

        return item;
    }

    public void removeLeaf(TreeItem t) {
        if (t.isLeaf()) {
            TreeItem p = t.getParent();

            if (p.getLeftChild() == t)
                p.deleteLeft();
            else
                p.deleteRight();
        }
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

    public void unhighlightAll() {
        root.unhighlightAll();
    }

    public void archive() {
        selected.setStatus(Status.ARCHIVED);
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

    private Integer getMaxHeight(TreeItem t) {
        if (t.isLeaf())
            return 0;
        else
            return 1 + Math.max(getMaxHeight(t.getLeftChild()), getMaxHeight(t.getRightChild()));
    }

    private void setRoot(TreeItem t) {
        root = t;
        t.setParent(null);
    }
}
