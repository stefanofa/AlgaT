package menu;

import javafx.fxml.FXML;
import config.BaseController;
import javafx.scene.control.Button;
import javafx.application.Platform;

import javafx.event.ActionEvent;


public class Menu extends BaseController {

    @FXML
    protected void initialize() {
    }

    //to correctly resize the dimension of the stage in the menu (this have to be done because of a bug in JAVAFX)
    @Override protected void startCtrl() {
        final boolean resizable = getActualStage().isResizable();
        getActualStage().setResizable(!resizable);
        getActualStage().setResizable(resizable);
    }

    @FXML private void learn(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        String topic, type, suffix;
        if (id.contains("Lesson")) {
            topic = id.replace("Lesson", "");
            type = "lesson";
            suffix = "Lesson.fxml";
            switchSceneFromFxmlPath("../lessons/" + topic + "/" + type + "/" + topic + suffix, topic);
        } else { // Contains "Quiz"
            topic = id.replace("Quiz", "");
            type = "quiz";
            suffix = "Quiz.fxml";
            switchSceneFromFxmlPath("../quizEngine/quizEngine.fxml", topic);
        }

    }

    @FXML protected void goToQuiz(ActionEvent event) {
        String id = ((Button) event.getSource()).getId();
        switchSceneFromFxmlPath("../lessons/" + id + "/quiz/" + id + "Quiz.fxml");
    }

    @FXML private void stop(ActionEvent event) {
        Platform.exit();
    }

    @FXML protected void openPlayground() {
        switchSceneFromFxmlPath("../playground/playground.fxml");
    }


}
