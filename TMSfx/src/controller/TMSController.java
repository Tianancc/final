package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Report;
import model.Student;
import model.TMS;

import java.net.URL;
import java.util.ResourceBundle;

public class TMSController extends Controller<TMS> implements Initializable {

    @FXML
    private Text totalTuitionText;
    @FXML
    private Text totalNetFeeText;
    @FXML
    private Text totalBASText;
    @FXML
    private Text totalScholarshipText;
    @FXML
    private Text totalDeductionText;
    @FXML
    private TableView<Report> reportTable;

    @FXML
    private Button closeButton;

    @FXML
    public void close() {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Report, StringProperty> colName = new TableColumn<>("Name");
        TableColumn<Report, DoubleProperty> colTuition = new TableColumn<>("Tuition");
        TableColumn<Report, DoubleProperty> colScholarship = new TableColumn<>("Scholarship");
        TableColumn<Report, DoubleProperty> colDeduction = new TableColumn<>("Deduction");
        TableColumn<Report, DoubleProperty> colNetFee = new TableColumn<>("NetFee");

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTuition.setCellValueFactory(new PropertyValueFactory<>("totalFee"));
        colScholarship.setCellValueFactory(new PropertyValueFactory<>("scholarship"));
        colDeduction.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        colNetFee.setCellValueFactory(new PropertyValueFactory<>("netFee"));

        reportTable.getColumns().clear();
        reportTable.getColumns().addAll(
                colName, colTuition, colScholarship, colDeduction, colNetFee
        );
        reportTable.getItems().clear();
        reportTable.getItems().addAll(
                model.reports()
        );

        totalTuitionText.setText("$"+model.getTotalTuitionFee());
        totalNetFeeText.setText("$"+model.getTotalNetFee());
        totalBASText.setText("$"+model.getBas());
        totalScholarshipText.setText("$"+model.getTotalScholarship());
        totalDeductionText.setText("$"+model.getTotalDeduction());
    }
}
