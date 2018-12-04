/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etablissement;
import classe.Etudiant;
import classe.Profil;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class AddEtudiantController implements Initializable {

    EtudiantService es = new EtudiantService();
    EtablissementService ets = new EtablissementService();

    @FXML
    private TextField code;

    @FXML
    private TextField nom;

    @FXML
    private TextField lieu;

    @FXML
    private DatePicker datebirt;

    @FXML
    private DatePicker dateOut;

    @FXML
    private TextField niveau;

    @FXML
    private TextField cme;

    @FXML
    private ComboBox decision;

    @FXML
    private TextField num;

    @FXML
    private ComboBox etablissement;

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

    Date dateBirtt = new Date();
    Date dateOutt = new Date();
    private static int index;

    @FXML
    private void save(ActionEvent e) {
        LocalDate localDatebirt = datebirt.getValue();
        Instant instant = Instant.from(localDatebirt.atStartOfDay(ZoneId.systemDefault()));
        dateBirtt = Date.from(instant);

        LocalDate localDateOut = dateOut.getValue();
        Instant instant2 = Instant.from(localDateOut.atStartOfDay(ZoneId.systemDefault()));
        dateOutt = Date.from(instant);

        es.create(new Etudiant(code.getText(), nom.getText(), dateBirtt, lieu.getText(), niveau.getText(), cme.getText(), dateOutt, decision.getValue().toString(), num.getText(), ets.findById(1)));
        clear();
        System.out.println("gooooooood");
        list.clear();
        initialize(null, null);
    }

    @FXML
    private void delete() {
        es.delete(es.findById(index));
        list.clear();
        initialize(null, null);
    }

    @FXML
    private void update() {
        Etudiant et = es.findById(index);
        //MachinList.set(index, m2);
        et.setCodeInscription(code.getText());
        et.setNom(nom.getText());
        et.setDateNaissance(dateBirtt);
        et.setLieuNaissance(lieu.getText());
        et.setNiveau(niveau.getText());
        et.setCodeNationale(cme.getText());
        et.setDateOut(dateOutt);
        et.setDecision(decision.getValue().toString());
        et.setNumDossier(num.getText());
        es.update(et);
        list.clear();
        initialize(null, null);
        clear();
    }

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
            list.add(new Etudiant(p.getId(),p.getCodeInscription(), p.getNom(), p.getDateNaissance(), p.getLieuNaissance(), p.getNiveau(), p.getCodeNationale(), p.getDateOut(), p.getDecision(), p.getNumDossier(), ets.findById(p.getEtablissement().getId())));
        }

        tbl.setItems(list);
    }

    public void clear() {
        nom.setText("");
        lieu.setText("");
        niveau.setText("");
        code.setText("");
    }

    public void loadCombo() {

        decision.getItems().addAll("ناجح");
        decision.getItems().addAll("يفصل");
        decision.getItems().addAll("تشطيب");
        decision.getItems().addAll("تثلث و لم تلتحق");
        decision.getItems().addAll("تكرار وعدم الإلتحاق");
        for (Etablissement et : ets.findAll()) {
            etablissement.getItems().addAll(et.getId() + "-" + et.getNom());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCombo();
        load();
        tbl.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                TablePosition pos = (TablePosition) tbl.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Etudiant item = (Etudiant) tbl.getItems().get(row);
                code.setText(item.getCodeNationale());
                nom.setText(item.getNom());
                lieu.setText(item.getLieuNaissance());
                niveau.setText(item.getNiveau());
                code.setText(item.getCodeInscription());
                decision.setValue(item.getDecision());
                num.setText(item.getNumDossier());
                etablissement.setValue(item.getEtablissement().getNom());
                cme.setText(item.getCodeNationale());
                index = item.getId();
                System.out.println("indeeeeeeeeeex" + index+"__"+item.getId());
            }
        }
        );
    }

}
