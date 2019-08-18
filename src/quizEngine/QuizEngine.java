package quizEngine;

import baseController.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class QuizEngine extends BaseController{

    @FXML StackPane domandaPane;
    @FXML StackPane nDomandaPane;
    @FXML ProgressBar progressBar;

    @FXML
    RadioButton
            Risposta1, Risposta2, Risposta3, Risposta4;

    private JSONObject quiz;
    private JSONArray domande;
    private int nDomande;
    private int actualQuestion;

    private int actualCheckedAnswer;
    private int actualCorrectAnswer;

    private ToggleGroup toggleGroup;

    @Override protected void startCtrl() {


        quiz = getQuizFromJson();


        this.nDomande = Integer.parseInt((quiz.get("nDomande").toString()));


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

        tmp = new Text("DOMANDA  " + this.actualQuestion);
        tmp.setTextAlignment(TextAlignment.CENTER);
        tmp.setFont(Font.font(50));
        tmp.setFill(Color.WHITE);
        nDomandaPane.getChildren().add(tmp);
//------------------------------------------------------------------

//------------------------------------------------------------------
//  Set and show the question

        if (domandaPane.getChildren().size() != 0)
            domandaPane.getChildren().remove(0);

        JSONObject actDomanda = (JSONObject)domande.get(this.actualQuestion-1);
        tmp = new Text(actDomanda.get("Domanda").toString());
        tmp.setTextAlignment(TextAlignment.CENTER);
        tmp.setFont(Font.font(null,FontPosture.ITALIC,35));
        domandaPane.getChildren().add(tmp);
//------------------------------------------------------------------

//------------------------------------------------------------------
//  Set and show the answers
        actualCorrectAnswer = Integer.parseInt(actDomanda.get("RispostaCorretta").toString());
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
                this.progressBar.setProgress(this.progressBar.getProgress() + (1.0 / this.nDomande) * this.actualQuestion);
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
            Object obj = parser.parse(new FileReader(
                    "src/topics/" + getParam() + "/quiz/" + getParam() + "Quiz.json"));


            return (JSONObject)obj;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML private void goToMenu(ActionEvent event) {
        try {
            switchSceneFromFxmlPath("../menu/menu.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

