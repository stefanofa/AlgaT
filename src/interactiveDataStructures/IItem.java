package interactiveDataStructures;

public interface IItem<T> {
    public T getContent();
    public void setContent(T value);
    public Status getStatus();
    public void setStatus(Status status);
    public void highlight();
    public void unhighlight();
    public void archive();
    public void unarchive();
}
