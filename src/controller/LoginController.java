/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static antlr.build.ANTLR.root;
import classe.Employe;
import classe.Profil;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.EmployeService;

/**
 * FXML Controller class
 *
 * @author imadeddine
 */
public class LoginController implements Initializable {

    EmployeService es = new EmployeService();

    @FXML
    private AnchorPane forme;

    @FXML
    private TextField email;

    @FXML
    private  PasswordField pass;

    @FXML
    private Button effacer;

    @FXML
    private void clean(javafx.event.ActionEvent e) {
        email.setText("");
        pass.setText("");
    }

    @FXML
    private void connecter(javafx.event.ActionEvent e) throws IOException {
        for (Employe em : es.findAll()) {
            if (em.getEmail().equals(email.getText())) {
                if(em.getPassword().equals(pass.getText()))
                {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/vue/Home.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                Stage stage = new Stage();
                stage.setTitle("New Window");
                stage.setScene(scene);
                stage.show();
                System.out.println("good");
                //FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
                break; 
                }
            } else {
                clean(e);
                System.out.println("bad");
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
