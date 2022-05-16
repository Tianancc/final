package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.Faculty;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController  extends Controller<Validator> implements Initializable {

    @FXML
    private Button OkayButton;

    @FXML
    private Text errorText;

    @FXML
    public void ok() {
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        StringBuilder error = new StringBuilder();
        model.errors().forEach(e->{
            error.append(e);
        });
        errorText.setText(error.toString());
    }
}
