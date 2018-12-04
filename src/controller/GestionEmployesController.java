/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Employe;
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
import service.EmployeService;
import service.EtablissementService;
import service.ProfilService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class GestionEmployesController implements Initializable {

    @FXML
    private DatePicker dateBirth;

    @FXML
    private DatePicker dateEmbau;

    @FXML
    private ComboBox etablissement;

    @FXML
    private ComboBox profils;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField pass;

    @FXML
    private TableView<?> tbl;

    @FXML
    private TableColumn<?, ?> cl_id;

    @FXML
    private TableColumn<?, ?> cl_profil;

    @FXML
    private TableColumn cl_Etablissement;

    @FXML
    private TableColumn cl_dateBirth;

    @FXML
    private TableColumn cl_dateEmbauche;

    @FXML
    private TableColumn<?, ?> cl_pass;

    @FXML
    private TableColumn<?, ?> cl_email;

    @FXML
    private TableColumn<?, ?> cl_prenom;

    @FXML
    private TableColumn<?, ?> cl_nom;

    private static int index;

    ObservableList list = FXCollections.observableArrayList();
    EmployeService ems = new EmployeService();
    ProfilService ps = new ProfilService();
    EtablissementService ets = new EtablissementService();
    private Object datebirt;

    public void load() {
        list.clear();
        cl_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        cl_profil.setCellValueFactory(new PropertyValueFactory<>("profil"));
        cl_Etablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        cl_dateBirth.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cl_dateEmbauche.setCellValueFactory(new PropertyValueFactory<>("dateEmbouche"));
        cl_pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        cl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        cl_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cl_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        for (Employe em : ems.findAll()) {
            list.add(new Employe(em.getId(), em.getNom(), em.getPrenom(), em.getEmail(), ps.findById(1), em.getPassword(), em.getDateNaissance(), em.getDateEmbouche(),em.getEtablissement()));
            //System.out.println("etablissemen::::::::::::::::::::::::::"+ets.findById(em.getEtablissement().getId()).getNom());
        }
        tbl.setItems(list);
    }

    @FXML
    private void save(ActionEvent e) {
        LocalDate localDatebirt = dateBirth.getValue();
        Instant instant = Instant.from(localDatebirt.atStartOfDay(ZoneId.systemDefault()));
        Date dateBirthh = Date.from(instant);

        LocalDate localDateOut = dateEmbau.getValue();
        Instant instant2 = Instant.from(localDateOut.atStartOfDay(ZoneId.systemDefault()));
        Date dateEmbauu = Date.from(instant);

        ems.create(new Employe(nom.getText(), prenom.getText(), email.getText(), ps.findById(1), pass.getText(), dateEmbauu, null, ets.findById(1)));
        System.out.println("gooooooood");
        list.clear();
        initialize(null, null);

    }

    @FXML
    private void update() {
        Employe et = ems.findById(index);
        et.setPrenom(prenom.getText());
        et.setNom(nom.getText());
        et.setEmail(email.getText());
        et.setPassword(pass.getText());

        //convertir date local to date
        //et.setDateEmbouche(dateBirth.getValue());
//        et.setDateNaissance(null);
//        et.setEtablissement((Etablissement) etablissement.getValue());
//        et.setProfil((Profil) profils.getValue());
        ems.update(et);
        list.clear();
        initialize(null, null);
        System.out.println("update machen");
    }

    public void loadCombo() {

        profils.getItems().addAll("مدير");
        profils.getItems().addAll("حارس عام");
        profils.getItems().addAll("المشرف");
        for (Etablissement et : ets.findAll()) {
            etablissement.getItems().addAll(et.getId() + "-" + et.getNom());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        loadCombo();
        tbl.setOnMousePressed(new EventHandler<javafx.scene.input.MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                TablePosition pos = (TablePosition) tbl.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Employe item = (Employe) tbl.getItems().get(row);
                nom.setText(item.getNom());
                prenom.setText(item.getPrenom());
                email.setText(item.getEmail());
                pass.setText(item.getPassword());
                etablissement.setValue(item.getEtablissement().getNom());//can not convert to CAP
                profils.setValue(item.getProfil().getLibelle());
                // dateBirth.setValue(item.getDateNaissance().);
                index = item.getId();
                System.out.println("indeeeeeeeeeex" + index + "__" + item.getId());
            }
        }
        );

    }

}
