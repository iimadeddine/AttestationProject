/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etablissement;
import classe.Etablissement;
import classe.Etudiant;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.EtablissementService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class GestionEtablissementController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField region;

    @FXML
    private TextField type;

    @FXML
    private TableView<Etablissement> tblEtablissement;

    @FXML
    private TableColumn<Etablissement, String> cl_id;
    @FXML
    private TableColumn<Etablissement, String> cl_nom;
    @FXML
    private TableColumn<Etablissement, String> cl_region;
    @FXML
    private TableColumn<Etablissement, String> cl_type;

    ObservableList<Etablissement> EtablissementList = FXCollections.observableArrayList();

    EtablissementService ets = new EtablissementService();
    private static int index;

    @FXML
    private void save(ActionEvent e) {
        ets.create(new Etablissement(nom.getText(), region.getText(), type.getText()));
        clear();
        System.out.println("gooooooood");
        EtablissementList.clear();
        initialize(null, null);
    }
    
    @FXML
    private void delete() {
        ets.delete(ets.findById(index));
        EtablissementList.clear();
        initialize(null, null);
    }

    @FXML
    private void update() {
        Etablissement m2 = ets.findById(index);
        m2.setNom(nom.getText());
        m2.setRegion(region.getText());
        m2.setType(type.getText());
        ets.update(m2);
        EtablissementList.clear();
        initialize(null, null);
        clear();
    }
    
    //funtion de vide les champs
    public void clear() {
        nom.setText("");
        region.setText("");
        type.setText("");
    }

    //fonction de remplir la table
    public void load() {
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        cl_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        for (Etablissement et : ets.findAll()) {
            EtablissementList.add(new Etablissement(et.getId(), et.getNom(), et.getRegion(), et.getType()));
        }
//        EtablissementList.add(new Etablissement(1,"ee","ee","ee"));
        tblEtablissement.setItems(EtablissementList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();

        tblEtablissement.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                    TablePosition pos = (TablePosition) tblEtablissement.getSelectionModel().getSelectedCells().get(0);
                        int row = pos.getRow();
                        Etablissement item = tblEtablissement.getItems().get(row);
                        nom.setText(item.getNom());
                        region.setText(item.getRegion());
                        type.setText(item.getType());
                        index = item.getId();
                        System.out.println("" + item.getId());
            }

           
        }
        );
    }

}
    

