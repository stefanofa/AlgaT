package intro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import baseController.BaseController;

public class Intro extends BaseController{


    @FXML private void openLink(ActionEvent event) {
        try {
            String username = ((Hyperlink) event.getSource()).getId();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("xdg-open https://github.com/" + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML private void Start(ActionEvent event) {
        try {
            switchSceneFromFxmlPath("../menu/menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
