/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodniaFXML;

import hibernate.HibernateUtil;
import hibernate.PacjentMySQL;
import hibernate.PracownikMySQL;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Query;
import org.hibernate.Session;
import przychodnia.Pacjent;
import przychodnia.Pracownik;

/**
 * FXML Controller class
 *
 * @author Soprano
 */
public class FXMLPielegniarkaController implements Initializable {

    static Session session = null; 
    
    Pracownik pra;
    PracownikMySQL pramyslq;        
    Pacjent pa;
    PacjentMySQL pamysql;
    
    @FXML
    private AnchorPane rootpane;
    
    //panele
    @FXML
    private AnchorPane paneview;
    @FXML
    private AnchorPane paneshow;
    
    //wyszukiwarka
    @FXML
    private TableView<Pacjent> tablepacjent;
    @FXML
    private TableColumn<Pacjent, String> colimie;
    @FXML
    private TableColumn<Pacjent, String> colnazwisko;
    @FXML
    private TableColumn<Pacjent, String> colpesel;
    @FXML
    private TableColumn<Pacjent, String> colid;
    @FXML
    private TableColumn<Pacjent, Date> coldata;
    @FXML
    private TextField imiesearch;
    @FXML
    private TextField nazwiskosearch;
    @FXML
    private TextField peselsearch;
    
    
    //danepac
    @FXML
    private Label pacjentiimie;
    @FXML
    private Label pacjentnazwisko;
    @FXML
    private Label pacjentdataur;
    @FXML
    private Label pacjentpesel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colimie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        colnazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        colpesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        colid.setCellValueFactory(new PropertyValueFactory<>("idPacjent"));
        coldata.setCellValueFactory(new PropertyValueFactory<>("dataUr"));
        pacjentviewshow();
    }    

    @FXML
    private void pacjentdel(ActionEvent event) {
        if(tablepacjent.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            System.out.println(tablepacjent.getSelectionModel().getSelectedItem());
            pamysql.delete(tablepacjent.getSelectionModel().getSelectedItem());
            pacjentviewshow();
        }
    }

    @FXML
    private void pacjentshow(ActionEvent event) {
        if(tablepacjent.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            paneview.setVisible(false);
            //paneadd.setVisible(false);
            paneshow.setVisible(true);
            pacjentiimie.setText(tablepacjent.getSelectionModel().getSelectedItem().getImie());
            pacjentnazwisko.setText(tablepacjent.getSelectionModel().getSelectedItem().getNazwisko());
            pacjentdataur.setText(String.valueOf(tablepacjent.getSelectionModel().getSelectedItem().getDataUr()));
            pacjentpesel.setText(String.valueOf(tablepacjent.getSelectionModel().getSelectedItem().getPesel()));
            pa = tablepacjent.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void pracowniksearch(ActionEvent event) {
    }
    
    private void pacjentviewshow(){
        tablepacjent.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT P.id FROM Pacjent P ";
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            pa = (Pacjent) session.get(Pacjent.class, (Integer)query.list().get(i));
            tablepacjent.getItems().add(pa);
        }
        session.close();
    }
}
