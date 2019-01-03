/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodniaFXML;

import hibernate.HibernateUtil;
import hibernate.PracownikMySQL;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Query;
import org.hibernate.Session;
import przychodnia.Pracownik;
import static przychodniaFXML.FXMLDocumentController.session;

/**
 * FXML Controller class
 *
 * @author Soprano
 */
public class FXMLAdministratorController implements Initializable {

    static Session session = null; 
    
    Pracownik p;
    PracownikMySQL pmysql;
    
    @FXML
    private AnchorPane rootpane;
    @FXML
    private ComboBox<String> rola;
    @FXML
    private TextField imieadd;
    @FXML
    private TextField nazwiskoadd;
    @FXML
    private TextField loginadd;
    @FXML
    private PasswordField hasloadd;
    @FXML
    private Label alertaddpola;
    @FXML
    private AnchorPane paneadd;
    @FXML
    private AnchorPane paneview;
    @FXML
    private TableView<Pracownik> tablepracownik = new TableView<Pracownik>();
    @FXML
    private TableColumn<Pracownik, String> colimie;
    @FXML
    private TableColumn<Pracownik, String> colnazwisko;
    @FXML
    private TableColumn<Pracownik, String> colrola;
    @FXML
    private TableColumn<Pracownik, String> colid;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rola.getItems().clear();
        rola.getItems().addAll("Lelarz", "Pielegniarka");
        rola.getSelectionModel().select("Lelarz");
        colimie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        colnazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        colrola.setCellValueFactory(new PropertyValueFactory<>("rola"));
        colid.setCellValueFactory(new PropertyValueFactory<>("idPracownik"));
    }   
    
    //przyciski menu
    @FXML
    private void logout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        rootpane.getChildren().setAll(pane);
    }
    
    @FXML
    private void pracownikview(ActionEvent event) {
        paneadd.setVisible(false);
        paneview.setVisible(true);
        
        pracownikviewshow();
    }

    @FXML
    private void pracowniksearch(ActionEvent event) {
        
    }

    @FXML
    private void pracownikaddmenu(ActionEvent event) {
        
    }
    
    //button'y
    @FXML
    private void pracownikadd(ActionEvent event) {
        if(imieadd.getText().isEmpty() == true || nazwiskoadd.getText().isEmpty() == true 
           || loginadd.getText().isEmpty() == true || hasloadd.getText().isEmpty() == true)
        {
            alertaddpola.setVisible(true);
        }
        else
        {
            p = new Pracownik(imieadd.getText(), nazwiskoadd.getText(), rola.getValue(), loginadd.getText(), hasloadd.getText());
            pmysql.insert(p);
        }
    }

    @FXML
    private void pracownikdel(ActionEvent event) {
        //System.out.println(tablepracownik.getSelectionModel().getSelectedCells().get(0));
        if(tablepracownik.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            System.out.println(tablepracownik.getSelectionModel().getSelectedItem());
            pmysql.delete(tablepracownik.getSelectionModel().getSelectedItem());
            pracownikviewshow();
        }
    }

    @FXML
    private void pracownikclick(MouseEvent event) {
        //System.out.println(tablepracownik.);
    }

    private void pracownikviewshow(){
        tablepracownik.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT P.id FROM Pracownik P WHERE P.rola = 'Pielegniarka'";
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            p = (Pracownik) session.get(Pracownik.class, (Integer)query.list().get(i));
            tablepracownik.getItems().add(p);
        }
        session.close();
    }
            
    
}
