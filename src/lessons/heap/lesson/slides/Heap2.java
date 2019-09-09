package lessons.heap.lesson.slides;

import config.Config;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import lessons.Slide;

import java.util.ArrayList;
import java.util.Random;

public class Heap2 extends Slide {
    public Heap2() {
        Text title = new Text();
        title.setFont(Font.font(Config.TITLE_SIZE));
        title.setText("La procedura maxHeapRestore");

        Text t = new Text();
        t.setFont(Font.font(Config.TEXT_SIZE));
        t.setText("Ora vediamo la procedura maxHeapRestore(), che sarà utile in seguito per svariati utilizzi.\n" +
                "Questa procedura consente di ripristinare la proprietà di ordinamento tra il padre e i figli di un sottoalbero\n" +
                "assumendo che tutto il resto dell'albero rispetti già tale proprietà. Per farlo, partendo dalla radice del sottoalbero\n" +
                "che viola il vincolo, si scambia tale radice con il figlio maggiore, e si ripete ricorsivamente sul nuovo figlio,\n" +
                "fermandosi quando si arriva ad una foglia oppure quando si incontra un sottoalbero che non viola più la proprietà\n");

        Random random = new Random();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < Config.HEAP_DEFAULT_SIZE; i++)
            a.add(random.nextInt(Config.RANDOM_RANGE));
        heap.load(a);
        heap.heapBuild();
        heap.swap(0, Config.HEAP_DEFAULT_SIZE - 1);
        heap.startRecording();
        heap.highlight(0);
        heap.maxHeapRestore(0);
        ge.load(heap.stopRecordingAndGetHistory());

        this.getChildren().addAll(title, t, ge);
    }

    @Override
    public String getProcedure() {
        return "maxHeapRestore(Item H[], integer i)\n" +
                "\tinteger max <- i\n" +
                "\tif 2*i <= size and H[2*i] > H[i] then max <- 2*i\n" +
                "\tif 2*i+1 <= size and H[2*i+1] > H[i] then max <- 2*i+1\n" +
                "\tif i != max then\n" +
                "\t\tH[i] <-> H[max]\n" +
                "\t\tmaxHeapRestore(max)\n";
    }
}
