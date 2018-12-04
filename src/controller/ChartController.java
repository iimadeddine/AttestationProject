/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Employe;
import classe.Etudiant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.EmployeService;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class ChartController implements Initializable {

    @FXML
    private VBox dataPane;

    @FXML
    private Label nbrEtudiant,nbrEtablissement,nbrEloye;

    @FXML
    private BarChart<?, ?> mChart;

    @FXML
    private CategoryAxis xAxes;

    @FXML
    private NumberAxis yAxes;

    EtudiantService es = new EtudiantService();
    EtablissementService ets = new EtablissementService();
    EmployeService ems=new EmployeService();

    
   private void setChart(){
       mChart.getData().clear();
        
        XYChart.Series chartSeries = new XYChart.Series();
        for(Etudiant e:es.findAll())
        {
           chartSeries.getData().add(new XYChart.Data(e.getEtablissement().getNom(), 120)); 
        }
        mChart.getData().addAll(chartSeries);
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setChart();
       nbrEtudiant.setText(""+es.CountEtudiant()+"");
       nbrEtablissement.setText(""+ets.CountEtablissement()+"");
       nbrEloye.setText(""+ems.CountEmploye()+"");
    }

}
