package menu;

import javafx.fxml.FXML;
import baseController.BaseController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

import javafx.event.ActionEvent;

public class Menu extends BaseController {

    @FXML
    protected void initialize() {

    }

    @FXML private void learn(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        String topic, type, suffix;
        if (id.contains("Lesson")) {
            topic = id.replace("Lesson", "");
            type = "lesson";
            suffix = "Lesson.fxml";
        } else { // Contains "Quiz"
            topic = id.replace("Quiz", "");
            type = "quiz";
            suffix = "Quiz.fxml";
        }
        switchSceneFromFxmlPath("../topics/" + topic + "/" + type + "/" + topic + suffix);
    }

    @FXML protected void goToQuiz(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        switchSceneFromFxmlPath("../topics/" + id + "/quiz/" + id + "Quiz.fxml");
    }

    @FXML private void stop(ActionEvent event) {
        System.out.println("Exiting application...");
        Platform.exit();
    }


}
