package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Session;

import java.io.IOException;

public class SessionController extends Controller<Session> {

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    public void exit() {
//        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
//        confirm.setTitle("Message");
//        confirm.setHeaderText(null);
//        confirm.setContentText("Do you want to exit.");
//        confirm.initStyle(StageStyle.UTILITY);
//
//        if (confirm.showAndWait().get() == ButtonType.OK) {
//            Platform.exit();
//        }
        Platform.exit();
    }

    @FXML
    public void login() throws IOException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/book.png"));
        stage.setX(ViewLoader.X);
        stage.setY(ViewLoader.Y);
        ViewLoader.showStage(this.model, "/view/login.fxml", "Sign In", stage);
    }


}
