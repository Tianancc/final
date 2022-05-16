package controller;


import au.edu.uts.ap.javafx.Controller;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import model.Faculty;
import model.Report;
import model.Student;
import model.TMS;

import java.net.URL;
import java.util.ResourceBundle;

public class SlipController extends Controller<TMS> implements Initializable {

    @FXML
    private Text creditsText;
    @FXML
    private Text totalFeeText;
    @FXML
    private Text netFeeText;
    @FXML
    private Text payPerCreditText;
    @FXML
    private Text scholarshipText;
    @FXML
    private Text deductionText;

    @FXML
    public void close() {
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Student userData = (Student) stage.getUserData();
        ObservableList<Report> reports = model.reports();
        Report userReport = reports.stream().filter(report -> report.getName().equals(userData.getName())).findFirst().orElse(null);
        creditsText.setText(userData.getCredits() + " credits");
        totalFeeText.setText("$"+userReport.getTotalFee());
        netFeeText.setText("$"+userReport.getNetFee());
        payPerCreditText.setText("$"+userData.getPayPerCredit());
        scholarshipText.setText("$"+userReport.getScholarship());
        deductionText.setText("$"+userReport.getDeduction());
    }
}
