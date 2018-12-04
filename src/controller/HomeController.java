/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etablissement;
import classe.Etudiant;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.EmployeService;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class HomeController implements Initializable {

    @FXML
    private Button btn1;
    
    @FXML
    private Button btn2;
    
    @FXML
    private Button btn3;
    
    @FXML
    private Button out;
    
    @FXML
    private Button btnadd;
    
    @FXML
    private VBox dataPane;
    
    @FXML
    private CategoryAxis xAxes = new CategoryAxis();

    @FXML
    private NumberAxis yAxes = new NumberAxis();
    
    @FXML
    private Label nbrEtudiant;
    
    @FXML
    private Label nbrEloye;

    @FXML
    private Label nbrEtablissement;
    
    @FXML
    private BarChart<String, Number> mChart = new BarChart<String, Number>(xAxes, yAxes);
    
    int a;
    int b;
    EtudiantService es=new EtudiantService();
    EtablissementService ets=new EtablissementService();
    EmployeService ems=new EmployeService();
    Etablissement eta=new Etablissement();
    
    
    private void setChart(){
       mChart.getData().clear();
        
        XYChart.Series chartSeries = new XYChart.Series();
        for(Etudiant e:es.findAll())
        {
           chartSeries.getData().add(new XYChart.Data(e.getEtablissement().getNom(), 120)); 
        }
        mChart.getData().addAll(chartSeries);
    }
    
    public void setDataPane(Node node) {
        dataPane.getChildren().setAll(node);
    }
    
    public VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
    
    
    @FXML
    public void loadPane(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/vue/GestionEmploye.fxml"));
    }
    
    @FXML
    public void loadPane2(ActionEvent event) throws IOException {
        
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vue/Login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
                System.out.println("good");
    }
    @FXML
    public void loadPane3(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/vue/AddEtudiant.fxml"));
    }
    
    @FXML
    public void loadEtablissement(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/vue/GestionEtablissement.fxml"));
    }
    
    @FXML
    public void btnAdd(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/vue/ImportationEtudiant.fxml"));
        
    }
    
    @FXML
    public void loadhome(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/vue/Chart.fxml"));
    }
    
    @FXML
    public void loadEmployes(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/vue/GestionEmployes.fxml"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          setChart();
       nbrEtudiant.setText(""+es.CountEtudiant()+"");
       nbrEtablissement.setText(""+ets.CountEtablissement()+"");
       nbrEloye.setText(""+ems.CountEmploye()+"");
    }    
    
}
