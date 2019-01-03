/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodniaFXML;

import przychodnia.*;
import hibernate.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Soprano
 */
public class FXMLDocumentController implements Initializable {

    static Session session = null; 
    
    Pracownik p;
    
    //Pacjent p;
    //PacjentMySQL pmysql;
    
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private Label alertpola;
    @FXML
    private Label alertlog;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void close(ActionEvent event) {
        Platform.exit();
    }
    

//    @FXML
//    private void pacjentadd(ActionEvent event) {
//        if(imieadd.getText() == null || nazwiskoadd.getText() == null || wiekadd.getValue() == null || peseladd.getText() == null)
//        {
//            System.out.println("wypełnij wszystkie pola");
//        }
//        else
//        {
//            p = new Pacjent(imieadd.getText(), nazwiskoadd.getText(), Date.valueOf(wiekadd.getValue()), Integer.parseInt(peseladd.getText()));
//            pmysql.insert(p);
//        }
//        //pacjentdisp.appendText(k.pacjenci.toString());
//        //pacjentdisp.setText(k.writepacjenci());
//    }

    @FXML
    private void log(ActionEvent event) throws Exception {
        alertpola.setVisible(false);
        alertlog.setVisible(false);
        
        if(login.getText().isEmpty() == true || pass.getText().isEmpty() == true)
        {
            alertpola.setVisible(true);
        }
        else
        {
            session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT P.id FROM Pracownik P WHERE P.login = '" + login.getText() + "' AND P.haslo = '" + pass.getText() + "'";
            Query query = session.createQuery(hql);
            List results = query.list();
            
            if(results.isEmpty())
            {
                alertlog.setVisible(true);
                session.close();
            }
            else
            {
                p = (Pracownik) session.get(Pracownik.class, (Integer)query.list().get(0));
                System.out.println(p);
                session.close();
                AnchorPane pane;
                
                if(p.getRola() == "Admin")
                    pane = FXMLLoader.load(getClass().getResource("FXMLAdministrator.fxml"));
                else if(p.getRola() == "Lekarz")
                    pane = FXMLLoader.load(getClass().getResource("FXMLAdministrator.fxml"));
                else
                    pane = FXMLLoader.load(getClass().getResource("FXMLAdministrator.fxml"));
                
                //AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLAdministrator.fxml"));
                rootpane.getChildren().setAll(pane);
            }
        }
            
        
        
        
        

    }
}
