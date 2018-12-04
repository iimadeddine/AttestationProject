package attestationproject;

import classe.Employe;
import classe.Etablissement;
import classe.Profil;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import service.EmployeService;
import service.EtablissementService;
import service.EtudiantService;
import service.ProfilService;
import util.HibernateUtil;

/**
 *
 * @author imadeddine
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/vue/Home.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        //Session s = HibernateUtil.getSessionFactory().openSession();
        //s.beginTransaction();
//        EtudiantService es = new EtudiantService();
//        EmployeService ems=new EmployeService();
//        System.out.print("nbr des employes :"+ems.CountEmploye());
    }

}
