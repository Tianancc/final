package controller;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Faculty;
import model.Session;
import model.Student;
import model.TMS;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FacultyController extends Controller<Faculty> implements Initializable{

    @FXML
    private TextField filterName;
    @FXML
    private TextField filterEmail;
    @FXML
    private TableView<Student> filterTable;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button selectButton;
    @FXML
    private Button slipButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button closeButton;

    @FXML
    public void add() throws IOException {
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/edit.png"));
        stage.setX(ViewLoader.X);
        stage.setY(ViewLoader.Y);
        stage.setUserData(null);
        ViewLoader.showStage(model, "/view/student.fxml", "Adding New Student", stage);
    }

    @FXML
    public void delete() {
        Student selectedItem = filterTable.getSelectionModel().getSelectedItem();
        model.removeStudent(selectedItem);
    }

    @FXML
    public void select() throws IOException {
        Student selectedItem = filterTable.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/edit.png"));
        stage.setX(ViewLoader.X);
        stage.setY(ViewLoader.Y);
        stage.setUserData(selectedItem);
        ViewLoader.showStage(model, "/view/student.fxml", "Accessing File:" + selectedItem.getName(), stage);
    }

    @FXML
    public void slip() throws IOException {
        TMS tms = new TMS(model);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/edit.png"));
        stage.setX(ViewLoader.X);
        stage.setY(ViewLoader.Y);
        Student selectedItem = filterTable.getSelectionModel().getSelectedItem();
        stage.setUserData(selectedItem);
        ViewLoader.showStage(tms, "/view/slip.fxml", selectedItem.getName() + " SLIP Report", stage);
    }

    @FXML
    public void report() throws IOException {
        TMS tms = new TMS(model);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("view/uts.jpeg"));
        stage.setX(ViewLoader.X);
        stage.setY(ViewLoader.Y);
        ViewLoader.showStage(tms, "/view/tms.fxml", "TMS Report", stage);
    }

    @FXML
    public void close() {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        initListener();
        deleteButton.disableProperty().bind(filterTable.getSelectionModel().selectedItemProperty().isNull());
        selectButton.disableProperty().bind(filterTable.getSelectionModel().selectedItemProperty().isNull());
        slipButton.disableProperty().bind(filterTable.getSelectionModel().selectedItemProperty().isNull());
    }

    private void initTable(){
        TableColumn<Student, StringProperty> colName = new TableColumn<>("Name");
        TableColumn<Student, StringProperty> colEmail = new TableColumn<>("Email");
        TableColumn<Student, StringProperty> colPhone = new TableColumn<>("Phone");
        colName.setCellValueFactory( new PropertyValueFactory<>("name") );
        colEmail.setCellValueFactory( new PropertyValueFactory<>("email") );
        colPhone.setCellValueFactory( new PropertyValueFactory<>("phone") );
        filterTable.getColumns().clear();
        filterTable.getColumns().addAll(
                colName, colEmail, colPhone
        );
        filterTable.getItems().clear();
        filterTable.getItems().addAll(
                model.getStudents()
        );

    }

    private void initListener(){
        filterName.textProperty().addListener((observable, oldValue, newValue) -> {
            String email = filterEmail.getText();
            model.filterList(newValue,email);
        });

        filterEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            String name = filterName.getText();
            model.filterList(name,newValue);
        });

        model.getStudents().addListener((ListChangeListener<Student>) c -> {
            initTable();
        });
    }
}
