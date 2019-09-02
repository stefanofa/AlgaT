package playground;

import baseController.BaseController;
import interactiveDataStructures.array.InteractiveArray;
import interactiveDataStructures.trees.InteractiveBinaryTree;
import interactiveDataStructures.trees.TreeItem;
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
    @FXML Button treeButton;
    @FXML Button leftButton;
    @FXML Button rightButton;
    @FXML Button deleteButton;

    private Integer count = 0;

    InteractiveArray ia = new InteractiveArray();
    InteractiveBinaryTree ibt = new InteractiveBinaryTree();

    @FXML private void initialize() {
        vBox.getChildren().addAll(ia, ibt);
    }

    @FXML private void addElement() {
        if (input.getText().equals(""))
            ia.push(count);
        else
            ia.insertAt(Integer.parseInt(input.getText()), count);
        count++;
    }

    @FXML private void removeElement() {
        ia.removeAt(Integer.parseInt(input.getText()));
    }

    @FXML private void archiveElement() {
        ia.archiveAt(Integer.parseInt(input.getText()));
    }

    @FXML private void swap() {
        int i1 = Integer.parseInt(index1.getText());
        int i2 = Integer.parseInt(index2.getText());
        System.out.println(i1 + " " + i2);
        ibt.swapElements(i1, i2);
    }

    @FXML private void tree() {
        if (ibt.isEmpty())
            ibt.insertRoot(count);

        count++;
    }

    @FXML private void insertLeft() {
        ibt.insertLeft(count);
        count++;
    }

    @FXML private void insertRight() {
        ibt.insertRight(count);
        count++;
    }

    @FXML private void delete() {

    }


}
