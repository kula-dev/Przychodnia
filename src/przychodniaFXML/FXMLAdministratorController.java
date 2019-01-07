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
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;
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
    
    //panele słoneczne
    @FXML
    private AnchorPane paneadd;
    @FXML
    private AnchorPane paneview;
    @FXML
    private AnchorPane paneedit;
    
    //tabelka widoku
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
    
    //edycja
    @FXML
    private TextField editimie;
    @FXML
    private TextField editnazwisko;
    @FXML
    private TextField editlogin;
    @FXML
    private PasswordField edithaslo;
    @FXML
    private TextField imiesearch;
    @FXML
    private TextField nazwiskosearch;
    @FXML
    private ComboBox<String> rolasearch;
    @FXML
    private Label alertpraadd;
    @FXML
    private Label alertpradel;
    @FXML
    private Label alertpraeditdane;
    @FXML
    private Label alertpraeditlog;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rola.getItems().clear();
        rola.getItems().addAll("Lekarz", "Pielegniarka");
        rola.getSelectionModel().select("Lekarz");
        rolasearch.getItems().clear();
        rolasearch.getItems().addAll("Wszyscy", "Lekarz", "Pielegniarka");
        rolasearch.getSelectionModel().select("Wszyscy");
        colimie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        colnazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        colrola.setCellValueFactory(new PropertyValueFactory<>("rola"));
        colid.setCellValueFactory(new PropertyValueFactory<>("idPracownik"));
        pracownikviewshow();
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
        paneedit.setVisible(false);
        paneview.setVisible(true);
        
        pracownikviewshow();
    }

    @FXML
    private void pracowniksearch(ActionEvent event) {
        String rola = "";
        tablepracownik.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        if(rolasearch.getValue() == "Wszyscy")
            rola = "";
        else
            rola = rolasearch.getValue();

        String hql = "SELECT P.id FROM Pracownik P WHERE"
                + "  P.imie LIKE '%" + imiesearch.getText() + "%' AND"
                + " P.nazwisko LIKE '%" + nazwiskosearch.getText() + "%' AND"
                + " P.rola LIKE '%" + rola + "%' AND"
                + " P.rola NOT LIKE 'Admin'";

        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            p = (Pracownik) session.get(Pracownik.class, (Integer)query.list().get(i));
            tablepracownik.getItems().add(p);
        }
        session.close();
    }

    @FXML
    private void pracownikaddmenu(ActionEvent event) {
        paneedit.setVisible(false);
        paneview.setVisible(false);
        paneadd.setVisible(true);
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
            alert(alertpraadd);
            alertaddpola.setVisible(false);
            imieadd.clear();
            nazwiskoadd.clear();
            loginadd.clear();
            hasloadd.clear();
        }
    }

    @FXML
    private void pracownikdel(ActionEvent event) {
        if(tablepracownik.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            pmysql.delete(tablepracownik.getSelectionModel().getSelectedItem());
            pracownikviewshow();
            alert(alertpradel);
        }
    }

    private void pracownikviewshow(){
        tablepracownik.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT P.id FROM Pracownik P "
                + "WHERE P.rola = 'Pielegniarka' OR P.rola = 'Lekarz'";
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            p = (Pracownik) session.get(Pracownik.class, (Integer)query.list().get(i));
            tablepracownik.getItems().add(p);
        }
        session.close();
    }
    
    @FXML
    private void editpracownik(ActionEvent event) {
        if(tablepracownik.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            paneview.setVisible(false);
            paneadd.setVisible(false);
            paneedit.setVisible(true);
            editimie.setText(tablepracownik.getSelectionModel().getSelectedItem().getImie());
            editnazwisko.setText(tablepracownik.getSelectionModel().getSelectedItem().getNazwisko());
            editlogin.setText(tablepracownik.getSelectionModel().getSelectedItem().getLogin());
            edithaslo.setText(tablepracownik.getSelectionModel().getSelectedItem().getHaslo());
            p = tablepracownik.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void editimienaz(ActionEvent event) {
        p.setImie(editimie.getText());
        p.setNazwisko(editnazwisko.getText());
        pmysql.update(p);
        alert(alertpraeditdane);
    }

    @FXML
    private void editloghas(ActionEvent event) {
        p.setLogin(editlogin.getText());
        p.setHaslo(edithaslo.getText());
        pmysql.update(p);
        alert(alertpraeditlog);
    }

    private void alert(Label a){
        a.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(
            event -> a.setVisible(false)
        );
        visiblePause.play();
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

}
