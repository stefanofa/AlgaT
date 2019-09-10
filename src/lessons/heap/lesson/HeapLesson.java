package lessons.heap.lesson;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import lessons.Lesson;
import lessons.heap.lesson.slides.Heap1;
import lessons.heap.lesson.slides.Heap2;
import lessons.heap.lesson.slides.Heap3;


public class HeapLesson extends Lesson {
    @FXML BorderPane borderPane;
    @FXML Button autoButton;

    @FXML private void initialize() {
        super.initialize(borderPane, autoButton);
        slides.add(new Heap1());
        slides.add(new Heap2());
        slides.add(new Heap3());
        setSlide(slideIndex);
        topic = "heap";
    }
}
