package lessons;

import interactiveDataStructures.heap.InteractiveHeap;
import interactiveDataStructures.graphicEngine.GraphicEngine;
import javafx.scene.layout.VBox;

public abstract class Slide extends VBox {
    protected GraphicEngine ge = new GraphicEngine();
    protected InteractiveHeap heap = new InteractiveHeap();

    public Slide() { }

    public GraphicEngine getGraphicEngine() { return ge; }
    public InteractiveHeap getInteractiveHeap() { return heap; }
    public abstract String getProcedure();
}
