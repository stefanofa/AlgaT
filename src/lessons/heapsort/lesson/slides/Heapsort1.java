package lessons.heapsort.lesson.slides;

import config.Config;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lessons.Slide;

import java.util.ArrayList;
import java.util.Random;

public class Heapsort1 extends Slide {
    public Heapsort1() {
        Text title = new Text();
        title.setFont(Font.font(Config.TITLE_SIZE));
        title.setText("La procedura heapsort");

        Text t = new Text();
        t.setFont(Font.font(Config.TEXT_SIZE));
        t.setText("La procedura heapsort permette di ordinare in maniera crescente un vettore in tempo O(n*log(n)), e quindi è un algoritmo ottimo.\n" +
                "Essa fa uso, appunto, della struttura dati HEAP. I passi sono i seguenti:\n" +
                "\t- Si esegue heapBuild sul vettore, in modo da trasformarlo in un HEAP\n" +
                "\t- Iterando dall'ultimo al primo elemento (con i che va da size-1 a 0), si esegue quanto segue:\n" +
                "\t\t% Si scambia il primo elemento (che è il massimo, in quanto radice dell'albero che rispetta la proprietà di HEAP)\n" +
                "\t\t  con l'i-esimo (l'ultimo, il penultimo, ...)\n" +
                "\t\t% Ora l'i-esimo elemento è nella posizione corretta, mentre il primo viola la proprietà di HEAP\n" +
                "\t\t% Quindi si richiama la procedura maxHeapRestore sul primo elemento (che è la radice), in modo da ristabilire la proprietà dell'HEAP\n" +
                "\t- Dopo aver iterato su tutti gli elementi a ritroso, per via del fatto che di volta in volta si mette il massimo alla fine\n" +
                "\t  del sotto-vettore considerato\n" +
                "\t  nella singola iterazione, il vettore completo è stato ordinato.\n" +
                "Visto che maxHeapRestore richiede tempo log(n) e viene eseguito n volte, heapsort richiede tempo O(n*log(n))\n");

        Random random = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            a.add(random.nextInt(Config.RANDOM_RANGE));
        heap.load(a);
        heap.startRecording();
        heap.heapsort();
        ge.load(heap.stopRecordingAndGetHistory());

        this.getChildren().addAll(title, t, ge);
    }

    @Override
    public String getProcedure() {
        return "heapsort(Item[] A)\n" +
                "\theapBuild(A)\n" +
                "\tfor i from size to 1 do\n" +
                "\t\tA[1] <-> A[i]\n" +
                "\t\tmaxHeapRestore(A, 1)\n";
    }
}
