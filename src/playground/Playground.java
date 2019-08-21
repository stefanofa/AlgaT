package playground;

import baseController.BaseController;
import graphic.array.GraphicArray;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Playground extends BaseController {
    @FXML VBox vBox;
    @FXML Button addButton;
    @FXML Button removeButton;
    @FXML Button archiveButton;
    @FXML TextField input;
    @FXML TextField index1;
    @FXML TextField index2;
    @FXML Button swapButton;

    private Integer count = 0;

    GraphicArray ga = new GraphicArray();

    @FXML private void initialize() {
        vBox.getChildren().add(ga);
    }

    @FXML private void addElement() {
        ga.insertAt(Integer.parseInt(input.getText()), count);
        count++;
    }

    @FXML private void removeElement() {
        ga.removeAt(Integer.parseInt(input.getText()));
    }

    @FXML private void archiveElement() {
        ga.archiveAt(Integer.parseInt(input.getText()));
    }

    @FXML private void swap() {
        int i1 = Integer.parseInt(index1.getText());
        int i2 = Integer.parseInt(index2.getText());
        ga.swap(i1, i2);
    }
}
