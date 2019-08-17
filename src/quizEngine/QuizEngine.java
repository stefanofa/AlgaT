package quizEngine;
import baseController.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.FileReader;
import java.nio.file.FileSystem;
import java.util.Iterator;
import java.util.Stack;

import javafx.scene.paint.Color;
import javafx.scene.text.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.*;

public class QuizEngine extends BaseController{

    @FXML StackPane domandaPane;
    @FXML StackPane nDomandaPane;
    @FXML ProgressBar progressBar;
    @FXML StackPane rispostePane;

    private JSONObject quiz;
    private JSONArray domande;
    private int nDomande;
    private int actualQuestion;

    private int actualCheckedAnswer;
    private int actualCorrectAnswer;

    @Override protected void startCtrl() {


        quiz = getQuizFromJson();


        this.nDomande = Integer.parseInt((quiz.get("nDomande").toString()));


        this.domande = (JSONArray)quiz.get("Domande");
        this.actualQuestion = 0;

        //parameters initialized to start the question cycle
        this.actualCheckedAnswer = this.actualCorrectAnswer = 1;

        nextQuestion();


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
        if (rispostePane.getChildren().size() != 0)
            rispostePane.getChildren().remove(0);
        //da implementare parsing e visualizzazione risposte su schermo, associate ad un checkbox
        //ogni checkbox ha un suo id numerico che corrisponde al numero di risposta cliccata
        //quando viene cliccato il checkbox viene chiamata una funzione di callback che aggiorna
        //il numero del checkbox attualmente cliccato, dopodichè il controllo per verificare se la risposta
        //é esatta viene fatto internamente alla funzione nextQuestion(), se è esatta permette di avanzare, altrimenti
        //l'utente deve riprovare a rispondere
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

