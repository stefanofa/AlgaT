package config;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class BaseController {

    private Stage stage;
    private String param;

    protected Stage getActualStage () {
        return this.stage;
    }
    protected String getParam () { return this.param; }

    public void setStage (Stage stage) {
        this.stage = stage;
    }
    public void setParam (String param) { this.param = param; }

    //a controller may want to override this method to get it executed when the SwitchScene happens
    //it will be executed after all the initialization of the new fxml context, including the function "initialize()"
    protected void startCtrl() {}

    protected void switchSceneFromFxmlPath (String fxmlPath) {
        switchSceneFromFxmlPath(fxmlPath, null);
    }

    //this method allows to switch from a context to another fxml context (file), staying in the same stage
    //could be the needs to pass a String parameter into the new controller, that's why there is the "param" var
    protected void switchSceneFromFxmlPath (String fxmlPath, String param) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            BaseController ctrl = loader.<BaseController>getController();
            //the method initialize is called at the end of the above row, so what is after this comment is not
            //already configured in the initialize call. example : when you override and then call initialize,
            //the fields stage and param have null value inside the initialize scope
            ctrl.setStage(this.getActualStage());
            ctrl.setParam(param);
            getActualStage().setScene(new Scene(root, 1354, 772));
            //if it's not overrided, startthis.progressBar.getProgress() + Ctrl() does nothing
            ctrl.startCtrl();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void goToMenu(ActionEvent event) {
        try {
            switchSceneFromFxmlPath("/menu/menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML protected void goToQuiz(String topic) {
        switchSceneFromFxmlPath("/quizEngine/quizEngine.fxml", topic);
    }

}
