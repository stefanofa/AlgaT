package lessons.heap.lesson.slides;

import config.Config;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lessons.Slide;

import java.util.ArrayList;
import java.util.Random;

public class Heap3 extends Slide {
    public Heap3() {
        Text title = new Text();
        title.setFont(Font.font(Config.TITLE_SIZE));
        title.setText("La procedura heapBuild");

        Text t = new Text();
        t.setFont(Font.font(Config.TEXT_SIZE));
        t.setText("Infine vediamo la procedura heapBuild(), che riordina un array in modo da fargli rispettare le proprietà dell'HEAP\n" +
                "Per farlo, si utilizza la procedura maxHeapRestore iterativamente, partendo prima dai livelli più bassi (ovvero partendo da n/2\n" +
                "in quanto tutti gli elementi da n/2+1 fino a n sono foglie) per poi risalire verso la radice. Questo perchè la procedura\n" +
                "maxHeapRestore richiamata sulla radice di un sottoalbero assume che tutto il resto del sottoalbero rispetti già le proprietà\n" +
                "dell'HEAP, quindi ovviamente non la si può richiamare la prima volta sulla radice perchè tale assunzione non sarebbe vera.");

        Random random = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            a.add(random.nextInt(Config.RANDOM_RANGE));
        heap.load(a);
        heap.startRecording();
        heap.heapBuild();
        ge.load(heap.stopRecordingAndGetHistory());

        this.getChildren().addAll(title, t, ge);
    }

    @Override
    public String getProcedure() {
        return "heapBuild(Item[] A, integer i)\n" +
                "\tfor i from size/2 to i do\n" +
                "\t\tmaxHeapRestore(A, i)\n";
    }
}
