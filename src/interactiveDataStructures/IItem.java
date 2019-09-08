package interactiveDataStructures;

public interface IItem<T> {
    T getContent();
    void setContent(T value);
    Status getStatus();
    void setStatus(Status status);
    void highlight();
    void unhighlight();
    void archive();
    void unarchive();
}
