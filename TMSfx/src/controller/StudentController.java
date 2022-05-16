package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Faculty;
import model.Student;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController extends Controller<Faculty> implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField IDField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField creditsField;
    @FXML
    private TextField scholarshipField;
    @FXML
    private TextField deductionField;

    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button closeButton;

    @FXML
    public void add() throws IOException {
        if (checkValue(null)){
            close();
        }
    }

    @FXML
    public void update() throws IOException {
        Student userData = (Student)stage.getUserData();
        if (null != userData){
            if (checkValue(userData)){
                close();
            }
        }
    }

    @FXML
    public void close() {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton.disableProperty().set(null != stage.getUserData());
        updateButton.disableProperty().set(null == stage.getUserData());
        if (null != stage.getUserData()){
            Student userData = (Student)stage.getUserData();
            nameField.setText(userData.getName());
            emailField.setText(userData.getEmail());
            phoneField.setText(userData.getPhone());
            addressField.setText(userData.getAddress());
            IDField.setText(userData.getID());
            typeField.setText(userData.getType());
            creditsField.setText(userData.creditsProperty().getValue()+"");
            scholarshipField.setText(userData.getScholarship() + "");
            deductionField.setText(userData.deductionProperty().doubleValue()+"");
        }else{
            creditsField.setText("0");
            scholarshipField.setText("0.0");
            deductionField.setText("Ccde");
        }
    }

    private boolean checkValue(Student student) throws IOException {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String type = typeField.getText();
        String ID = IDField.getText();
        int credits = Integer.valueOf(creditsField.getText());
        double scholarship = new BigDecimal(scholarshipField.getText()).doubleValue();
        Validator validator = new Validator();
        if (!validator.isValid(name,email,phone,address,type,ID,credits,scholarship)){
            validator.generateErrors(name,email,phone,address,type,ID,credits,scholarship);
            Stage stage = new Stage();
            stage.getIcons().add(new Image("view/error.png"));
            stage.setX(ViewLoader.X);
            stage.setY(ViewLoader.Y);
            ViewLoader.showStage(validator, "/view/error.fxml", "Input Exceptions" , stage);
            return false;
        }
        String deduction = deductionField.getText();
        if (null != student){
            student.updateDetails(name,email,phone,address,ID,type,credits,scholarship,deduction);
        }else{
            model.addStudent(new Student(name,email,phone,address,ID,type,credits,scholarship,deduction));
        }
        return true;
    }
}
