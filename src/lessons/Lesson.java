package lessons;

import config.BaseController;
import config.Config;
import graphicEngine.GraphicEngine;
import graphicEngine.TickEventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Lesson extends BaseController {
    private BorderPane borderPane;
    private Button autoButton;
    protected ArrayList<Slide> slides = new ArrayList<Slide>();
    protected GraphicEngine ge;
    protected Integer slideIndex = 0;
    protected Text procedure = new Text();
    protected Text currentSubProcedure = new Text();
    protected Text currentAtomicOperation = new Text();

    public Lesson() {
        procedure.setFont(Font.font(Config.TEXT_SIZE));
        currentSubProcedure.setFont(Font.font(Config.TEXT_SIZE));
        currentAtomicOperation.setFont(Font.font(Config.TEXT_SIZE));
    }

    protected void initialize(BorderPane borderPane, Button autoButton) {
        this.borderPane = borderPane;
        this.autoButton = autoButton;
        VBox vBox = new VBox(procedure, currentSubProcedure, currentAtomicOperation);
        vBox.setSpacing(16);
        vBox.setPadding(new Insets(8));
        borderPane.setRight(vBox);
    }

    @FXML protected void next() {
        if (!ge.ended()) {
            if (ge.isAutoPlaying())
                revertPlayMode();
            ge.next();
        } else {
            slideIndex++;
            if (slideIndex < slides.size())
                setSlide(slideIndex);
            else
                goToMenu(null);
        }
    }

    @FXML protected void prev() {
        if (!ge.atStart()) {
            if (ge.isAutoPlaying())
                revertPlayMode();
            ge.prev();
        } else {
            slideIndex--;
            if (slideIndex >= 0)
                setSlide(slideIndex);
            else
                goToMenu(null);
        }
    }

    @FXML protected void revertPlayMode() {
        if (ge.isAutoPlaying())
            autoButton.setText("PLAY");
        else
            autoButton.setText("PAUSE");
        ge.switchPlayMode();
    }

    protected void setSlide(int index) {
        Slide s = slides.get(index);
        procedure.setText(s.getProcedure());
        borderPane.setCenter(s);
        ge = s.getGraphicEngine();
        ge.setTickListener(new TickEventHandler() {
            @Override
            public void onTick() {
                currentSubProcedure.setText(ge.getCurrentSubProcedure());
                currentAtomicOperation.setText(ge.getCurrentAtomicOperation());
            }
        });
        autoButton.setText("PLAY");
    }

}
