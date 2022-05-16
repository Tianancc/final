package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Faculty;
import model.Session;

import java.io.IOException;

public class LoginController extends Controller<Session> {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginOK;

    @FXML
    private Button loginCancle;

    @FXML
    private Label loginError;

    @FXML
    public void loginOK() throws IOException {
        loginError.setText("");
        if (emailField.getText().length() == 0) {
            loginError.setText("Email is require");
            emailField.requestFocus();
            return;
        }

        if (passwordField.getText().length() == 0) {
            loginError.setText("Password is require");
            passwordField.requestFocus();
            return;
        }

        //Incorrect login details
        Faculty faculty = model.getFaculty(emailField.getText(), passwordField.getText());
        if (null == faculty){
            loginError.setText("Incorrect login details");
        }
        // 登陆成功，设置登录人信息，关闭登录窗口，打开学生管理窗口
//        model.setFaculty(faculty);

        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/faculty.png"));
        stage.setX(ViewLoader.X);
        stage.setY(ViewLoader.Y);

        this.stage.close();

        ViewLoader.showStage(faculty, "/view/faculty.fxml", "Session Admin:" + faculty.getName(), stage);

    }
    @FXML
    public void loginCancle() {
        stage.close();
    }

}
