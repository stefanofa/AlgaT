package lessons.heap.lesson.slides;

import config.Config;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lessons.Slide;

import java.util.ArrayList;
import java.util.Random;

public class Heap1 extends Slide {
    public Heap1() {
        Text title = new Text();
        title.setFont(Font.font(Config.TITLE_SIZE));
        title.setText("Le proprietà dell'HEAP");

        Text t = new Text();
        t.setFont(Font.font(Config.TEXT_SIZE));
        t.setText("Come introduzione, descriviamo brevemente la struttura HEAP e le sue proprietà.\n" +
                "La struttura HEAP si presenta come un albero binario avente le seguenti proprietà\n" +
                "\t1) L'albero è bilanciato a meno di una differenza di altezza pari a 1\n" +
                "\t2) Tutte le foglie del livello più basso sono addossate a sinistra\n" +
                "\t3) Gli eventuali figli di un nodo contengono valori minori o uguali di quelli del padre\n");

        Random random = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            a.add(random.nextInt(Config.RANDOM_RANGE));
        heap.load(a);
        heap.heapBuild();
        heap.startRecording();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            heap.highlight(i);
        ge.load(heap.stopRecordingAndGetHistory());
        ge.setPlaySpeed(1000);

        Text t2 = new Text();
        t2.setFont(Font.font(Config.TEXT_SIZE));
        t2.setText("\nL'HEAP può anche essere rappresentato come un array ordinato i cui elementi corrispondono a quelli dell'albero\n" +
                "secondo lo schema della visita BFS. In tal modo, se l'array parte dall'indice 1, valgono le seguenti relazioni:\n" +
                "\t1) Il padre di un elemento di indice i ha come indice floor(i/2)\n" +
                "\t2) Il figlio sinistro di un elemento di indice i ha come indice i*2\n" +
                "\t3) Il figlio destro di un elemento di indice i ha come indice i*2+1\n" +
                "Da ora in poi adotteremo il metodo di indicare gli elementi con l'indice che hanno nell'array, ma rappresenteremo\n" +
                "graficamente sia l'array che l'albero per non perdere di vista la corrispondenza e le proprietà dell'HEAP");
        this.getChildren().addAll(title, t, ge, t2);
    }

    @Override
    public String getProcedure() {
        return "";
    }

}
