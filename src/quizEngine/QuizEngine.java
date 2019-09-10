package quizEngine;

import config.BaseController;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class QuizEngine extends BaseController{

    @FXML StackPane domandaPane;
    @FXML StackPane nDomandaPane;
    @FXML StackPane showButtonPane;
    @FXML ProgressBar progressBar;

    @FXML
    RadioButton
            Risposta1, Risposta2, Risposta3, Risposta4;

    Button btnShow = new Button("Mostra Immagine");

    private JSONObject quiz;
    private JSONArray domande;
    private JSONObject actDomanda;
    private int nDomande;
    private int actualQuestion;

    private int actualCheckedAnswer;
    private int actualCorrectAnswer;

    private ToggleGroup toggleGroup;

    @Override protected void startCtrl() {

        this.getActualStage().setFullScreen(true);
        quiz = getQuizFromJson();


        this.nDomande = ((Long) quiz.get("nDomande")).intValue();


        this.domande = (JSONArray)quiz.get("Domande");
        this.actualQuestion = 0;

        //parameters initialized to start the question cycle
        this.actualCheckedAnswer = this.actualCorrectAnswer = 1;

        toggleGroup = new ToggleGroup();
        Risposta1.setToggleGroup(toggleGroup);
        Risposta2.setToggleGroup(toggleGroup);
        Risposta3.setToggleGroup(toggleGroup);
        Risposta4.setToggleGroup(toggleGroup);

        //set the ToggleGroup of Radio Buttons to intercept when the answer is changed and upload the value
        //of actualCheckedAnswer
        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> uploadCheckedAnswer());

        //make the button "Mostra Immagine" to show the pictures related to the Answer when it is clicked
        btnShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showImg(actDomanda.get("imgName").toString());
            }
        });

        showButtonPane.getChildren().add(btnShow);

        nextQuestion();


    }

    //this is called from the listener of the Toggle Group when a Radio Button is pressed
    private void uploadCheckedAnswer() {
        RadioButton tmpRadio = (RadioButton) toggleGroup.getSelectedToggle();
        String tmpID = tmpRadio.getId();

        if (tmpID.contains("1"))
            actualCheckedAnswer = 1;
        else if (tmpID.contains("2"))
            actualCheckedAnswer = 2;
        else if (tmpID.contains("3"))
            actualCheckedAnswer = 3;
        else
            actualCheckedAnswer = 4;
    }



    @FXML private void showQuestion() {

        Text tmp;

        Object obj = quiz.get("Contenuto");


//-----------------------------------------------------------------
//  Set and show the number of the question is actually running

        //if there is already one question printed, then remove it
        if (nDomandaPane.getChildren().size() != 0)
            nDomandaPane.getChildren().remove(0);

        tmp = new Text(getParam().toUpperCase() + " QUIZ - " + this.actualQuestion + "° Domanda");
        tmp.setTextAlignment(TextAlignment.CENTER);
        tmp.setFont(Font.font(40));
        tmp.setFill(Color.WHITE);
        nDomandaPane.getChildren().add(tmp);
//------------------------------------------------------------------

//------------------------------------------------------------------
//  Set and show the question,
//  if there is an Image related to the question -> make the "Show Image" button visible and clickable

        if (domandaPane.getChildren().size() != 0)
            domandaPane.getChildren().remove(0);

        actDomanda = (JSONObject)domande.get(this.actualQuestion-1);

        tmp = new Text(actDomanda.get("Domanda").toString());
        tmp.setTextAlignment(TextAlignment.CENTER);
        tmp.setFont(Font.font(null,FontPosture.ITALIC,35));
        tmp.setFill(Color.WHITE);

        domandaPane.getChildren().add(tmp);

        //se la domanda attuale non prevede un'immagine esplicativa, nascondo il button per mostrare l'immagine,
        //altrimenti lo rendo visibile
        //N.B. "null" è intesa come una stringa che è campo dell'oggetto JSON, non si intende il null pointer,
        //in tal modo tutte le domande che presentano immagini hanno come valore del campo "imgName" il nome dell'immagine,
        //mentre le domande che non presentano immagini hanno come valore del campo la stringa "null"
        if (actDomanda.get("imgName").toString().equals("null") )
            btnShow.setVisible(false);
        else
            btnShow.setVisible(true);
//------------------------------------------------------------------

//------------------------------------------------------------------
//  Set and show the answers
        actualCorrectAnswer = ((Long) actDomanda.get("RispostaCorretta")).intValue();
        JSONArray risposte = (JSONArray) actDomanda.get("Risposte");

        Risposta1.setText(risposte.get(0).toString());
        Risposta2.setText(risposte.get(1).toString());
        Risposta3.setText(risposte.get(2).toString());
        Risposta4.setText(risposte.get(3).toString());

//------------------------------------------------------------------


    }

    @FXML private void nextQuestion() {
        if (this.actualCheckedAnswer == this.actualCorrectAnswer) {
            if (this.actualQuestion < this.nDomande) {
                this.progressBar.setProgress((1.0 / this.nDomande) * this.actualQuestion);
                this.actualQuestion++;
                showQuestion();
            } else {
                this.progressBar.setProgress(1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Complimenti!");
                alert.setHeaderText(null);
                alert.setContentText("Hai completato correttamente il quiz! \nPremi OK per tornare al Menù");
                alert.showAndWait();

                goToMenu(null);
            }
        } else {//if got wrong answer

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sbagliato!");
            alert.setHeaderText(null);
            alert.setContentText("Hai scelto la risposta sbagliata! \nPremi OK per riprovare");
            alert.showAndWait();

            showQuestion();
        }

    }

    private JSONObject getQuizFromJson() {
        JSONParser parser = new JSONParser();
        try {
            InputStream in = getClass().getResourceAsStream("/lessons/" + getParam() + "/quiz/" + getParam() + "Quiz.json");
            Object obj = parser.parse(new BufferedReader(new InputStreamReader(in)));

            return (JSONObject)obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //to show an Image that is related to an Answer of the quiz and is stored in the topics -> quiz -> pictures folder
    private void showImg(String imgName) {
        if (imgName != "null") {
            try {
                BufferedImage buff = ImageIO.read(getClass().getResourceAsStream("/lessons/" + getParam() + "/quiz/pictures/" + imgName));
                final Stage dialog = new Stage();
                final ImageView imv = new ImageView();
                final Image img = SwingFXUtils.toFXImage(buff, null);
                imv.setImage(img);
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(this.getActualStage());
                VBox dialogVbox = new VBox(50);
                dialogVbox.getChildren().add(imv);
                Scene dialogScene = new Scene(dialogVbox, img.getWidth(), img.getHeight());
                dialog.setScene(dialogScene);
                dialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @FXML protected void goToMenu(ActionEvent event) {
        try {
            this.getActualStage().setFullScreen(false);
            switchSceneFromFxmlPath("/menu/menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

