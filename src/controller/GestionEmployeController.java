/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etudiant;
import classe.Profil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class GestionEmployeController implements Initializable {

    @FXML
    private Label resultat;

    @FXML
    private TextField recherche;

    @FXML
    private TableView<?> tbl;

    @FXML
    private TableColumn<?, ?> cl_etablissement;

    @FXML
    private TableColumn<?, ?> cl_num;

    @FXML
    private TableColumn<?, ?> cl_decision;

    @FXML
    private TableColumn<?, ?> cl_cme;

    @FXML
    private TableColumn<?, ?> cl_niveau;

    @FXML
    private TableColumn<?, ?> cl_dateOut;

    @FXML
    private TableColumn<?, ?> cl_datebirt;

    @FXML
    private TableColumn<?, ?> cl_lieu;

    @FXML
    private TableColumn<?, ?> cl_nom;

    @FXML
    private TableColumn<?, ?> cl_code;

    @FXML
    private TableColumn<?, ?> cl_id;

    ObservableList list = FXCollections.observableArrayList();
    EtudiantService es = new EtudiantService();

    public void load() {
        list.clear();
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_code.setCellValueFactory(new PropertyValueFactory<>("codeInscription"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_datebirt.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cl_lieu.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        cl_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        cl_cme.setCellValueFactory(new PropertyValueFactory<>("codeNationale"));
        cl_dateOut.setCellValueFactory(new PropertyValueFactory<>("dateOut"));
        cl_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
        cl_num.setCellValueFactory(new PropertyValueFactory<>("numDossier"));
        cl_etablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Etudiant p : es.findAll()) {
            list.add(new Etudiant(p.getId(), p.getCodeInscription(), p.getNom(), p.getDateNaissance(), p.getLieuNaissance(), p.getNiveau(), p.getCodeNationale(), p.getDateOut(), p.getDecision(), p.getNumDossier(), p.getEtablissement()));
        }

        tbl.setItems(list);
        resultat.setText(null);
    }

    public void chercher() {
        list.clear();
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_code.setCellValueFactory(new PropertyValueFactory<>("codeInscription"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cl_datebirt.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cl_lieu.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        cl_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        cl_cme.setCellValueFactory(new PropertyValueFactory<>("codeNationale"));
        cl_dateOut.setCellValueFactory(new PropertyValueFactory<>("dateOut"));
        cl_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
        cl_num.setCellValueFactory(new PropertyValueFactory<>("numDossier"));
        cl_etablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Etudiant p : es.findAll()) {
            if (recherche.getText().equals(p.getNom())|| recherche.getText().equals(p.getCodeNationale())) {
                list.add(new Etudiant(p.getId(), p.getCodeInscription(), p.getNom(), p.getDateNaissance(), p.getLieuNaissance(), p.getNiveau(), p.getCodeNationale(), p.getDateOut(), p.getDecision(), p.getNumDossier(), p.getEtablissement()));
                resultat.setText(null);
            } else {
                resultat.setText("لا يوجد");
            }
        }

        tbl.setItems(list);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
    }

}
