import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import baseController.BaseController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("intro/intro.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("AlgaT");
        primaryStage.setScene(new Scene(root, 1366, 768));
        BaseController ctrl = loader.<BaseController>getController();
        ctrl.setStage(primaryStage);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
