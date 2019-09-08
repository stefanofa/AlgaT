package baseController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseController {

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

    protected void startCtrl() {}

    protected void switchSceneFromFxmlPath (String fxmlPath) {
        switchSceneFromFxmlPath(fxmlPath, null);
    }

    protected void switchSceneFromFxmlPath (String fxmlPath, String param) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            System.out.println(root);
            BaseController ctrl = loader.<BaseController>getController();
            ctrl.setStage(this.getActualStage());
            ctrl.setParam(param);
            getActualStage().setScene(new Scene(root, 1366, 768));
            ctrl.startCtrl();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
