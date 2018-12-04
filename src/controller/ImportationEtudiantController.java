/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Etablissement;
import classe.Etudiant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.converter.DateTimeStringConverter;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.EtablissementService;
import service.EtudiantService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class ImportationEtudiantController implements Initializable {

    @FXML
    private Button add;

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
    EtablissementService ets = new EtablissementService();

    public void importerDB() throws IOException, ParseException {
        System.out.println("Start");
        FileChooser filechooser = new FileChooser();
        File selectedFile = filechooser.showOpenDialog(null);

        if (filechooser != null) {
            System.out.println(selectedFile.getPath());

            FileInputStream fis = new FileInputStream(new File(selectedFile.getAbsolutePath()));
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet mySheet = myWorkBook.getSheetAt(1);

            for (int i = 1; i < mySheet.getLastRowNum(); i++) {
                XSSFRow rowx = mySheet.getRow(i);
                XSSFCell cell2 = rowx.getCell(2);
                if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
                    continue;
                } else {
                    XSSFCell cell1 = rowx.getCell(1);
                    String codeInsCell = "";
                    codeInsCell = rowx.getCell(2).toString();

                    String nomcell = "";
                    nomcell = rowx.getCell(2).toString();

                    String dateInsCell = "";
                    dateInsCell = rowx.getCell(3).toString();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                    String dateInString = rowx.getCell(3).toString();
                    Date date1 = formatter.parse(dateInString);
                    System.out.println("dateeeeeeeeeeeeeeeeeeeeeeeeee" + dateInString);

                    String lieuCell = "";
                    lieuCell = rowx.getCell(4).toString();
                    SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
                    String dateInString2 = rowx.getCell(3).toString();
                    Date date2 = formatter2.parse(dateInString2);

                    String niveauCell = "";
                    niveauCell = rowx.getCell(5).toString();

                    String codeNatioCell = "";
                    codeNatioCell = rowx.getCell(6).toString();

                    String dateOutCell = "";
                    dateOutCell = rowx.getCell(7).toString();

                    String deciCell = "";
                    deciCell = rowx.getCell(8).toString();

                    String numDosCell = "";
                    numDosCell = rowx.getCell(9).toString();
                    Etablissement etabCell = null;
                    //etabCell = rowx.getCell(9).toString();

                    list.add(new Etudiant());
                    es.create(new Etudiant(codeInsCell, nomcell, date1, lieuCell, niveauCell, codeNatioCell, date2, deciCell, numDosCell, etabCell));
                    System.out.println("guuuuuuut");
                }

            }
            tbl.setItems(list);
            load();
            System.out.println("gut arbiet");
            initialize(null, null);
        }
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
            list.add(new Etudiant(p.getId(), p.getCodeInscription(), p.getNom(), p.getDateNaissance(), p.getLieuNaissance(), p.getNiveau(), p.getCodeNationale(), p.getDateOut(), p.getDecision(), p.getNumDossier(), p.getEtablissement()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
