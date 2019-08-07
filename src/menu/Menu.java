package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class Menu {

    @FXML private void openLink(ActionEvent event) {
        try {
            String username = ((Hyperlink) event.getSource()).getId();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("xdg-open https://github.com/" + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
