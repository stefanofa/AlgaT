package baseController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseController {

    private Stage stage;

    protected Stage getActualStage () {
        return this.stage;
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }

    protected void switchSceneFromFxmlPath (String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent scene = loader.load();
            BaseController ctrl = loader.<BaseController>getController();
            ctrl.setStage(this.getActualStage());
            getActualStage().setScene(new Scene(scene, 1366, 768));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
