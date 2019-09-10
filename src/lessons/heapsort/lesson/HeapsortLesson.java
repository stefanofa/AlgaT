package lessons.heapsort.lesson;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import lessons.Lesson;
import lessons.heapsort.lesson.slides.Heapsort1;

public class HeapsortLesson extends Lesson {
    @FXML BorderPane borderPane;
    @FXML Button autoButton;

    @FXML private void initialize() {
        super.initialize(borderPane, autoButton);
        slides.add(new Heapsort1());
        setSlide(slideIndex);
        topic = "heapsort";
    }

}
