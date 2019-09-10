package interactiveDataStructures;

public interface IItem<T> {
    void setStatus(Status status);
    void highlight();
    void unhighlight();
    void archive();
    void unarchive();
}
