/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodniaFXML;


import hibernate.HibernateUtil;
import hibernate.PacjentMySQL;
import hibernate.PracownikMySQL;
import hibernate.WizytyMySQL;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLData;
import java.text.DateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;
import przychodnia.Pacjent;
import przychodnia.Pracownik;
import przychodnia.Wizyty;

/**
 * FXML Controller class
 *
 * @author Soprano
 */
public class FXMLPielegniarkaController implements Initializable {

    static Session session = null; 
    
    Pracownik pra;
    PracownikMySQL pramyslq;        
    Pacjent pa = new Pacjent();
    PacjentMySQL pamysql;
    Wizyty wiz;
    WizytyMySQL wizmysql;
    
    @FXML
    private AnchorPane rootpane;
    
    //panele
    @FXML
    private AnchorPane paneview;
    @FXML
    private AnchorPane paneshow;
    
    //menubar
    
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
    @FXML
    private TableColumn<Wizyty, Pracownik> colwizdok;
    @FXML
    private TableColumn<Wizyty, String> colwizdata;
    @FXML
    private TableColumn<Wizyty, String> colwizid;
    @FXML
    private TableView<Wizyty> tablewizyty;
    
    //dodawanie wizyt
    @FXML
    private DatePicker wizadddata;
    @FXML
    private TableView<Pracownik> tablewizadd;
    @FXML
    private TableColumn<Pracownik, String> tablewizaddid;
    @FXML
    private TableColumn<Pracownik, String> tablewizaddimie;
    @FXML
    private TableColumn<Pracownik, String> tablewizaddnaz;
    
    //edycja pacjenta
    @FXML
    private TextField imieedit;
    @FXML
    private TextField nazwiskoedit;
    @FXML
    private DatePicker datauredit;
    @FXML
    private TextField peseledit;
    
    //dodawanie pacjenta
    @FXML
    private AnchorPane paneadd;
    @FXML
    private TextField imieadd;
    @FXML
    private TextField nazwiskoadd;
    @FXML
    private DatePicker wiekadd;
    @FXML
    private TextField peseladd;
    @FXML
    private Label alertpacdel;
    @FXML
    private Label alertwizadd;
    @FXML
    private Label alertpacedit;
    @FXML
    private Label alertpacadd;
    @FXML
    private Label alertpeseladd;
    @FXML
    private Label alertpacpola;
    @FXML
    private Label alertwizdel;

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
        
        colwizdok.setCellValueFactory(new PropertyValueFactory<>("pracownik"));
        colwizdata.setCellValueFactory(new PropertyValueFactory<>("dataWiz"));
        colwizid.setCellValueFactory(new PropertyValueFactory<>("idWizyty"));
        
        tablewizaddimie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        tablewizaddnaz.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        tablewizaddid.setCellValueFactory(new PropertyValueFactory<>("idPracownik"));
        
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
            alert(alertpacdel);
        }
    }

    @FXML
    private void pacjentshow(ActionEvent event) {
        if(tablepacjent.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            pa = tablepacjent.getSelectionModel().getSelectedItem();
            wizytyshow();
            paneview.setVisible(false);
            paneadd.setVisible(false);
            paneshow.setVisible(true);
            pacjentiimie.setText(tablepacjent.getSelectionModel().getSelectedItem().getImie());
            pacjentnazwisko.setText(tablepacjent.getSelectionModel().getSelectedItem().getNazwisko());
            pacjentdataur.setText(String.valueOf(tablepacjent.getSelectionModel().getSelectedItem().getDataUr()));
            pacjentpesel.setText(String.valueOf(tablepacjent.getSelectionModel().getSelectedItem().getPesel()));
        }
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

    @FXML
    private void logout(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void pacjentshowall(ActionEvent event) {
        
        paneadd.setVisible(false);
        paneshow.setVisible(false);
        paneview.setVisible(true);
        pacjentviewshow();
        wizytyshow();
        imiesearch.clear();
        nazwiskosearch.clear();
        peselsearch.clear();
    }

    @FXML
    private void pacjentadd(ActionEvent event) {
        paneshow.setVisible(false);
        paneview.setVisible(false);
        paneadd.setVisible(true);
    }
    
    @FXML
    private void addpacjent(ActionEvent event) {
        session = HibernateUtil.getSessionFactory().openSession();

        String hql = "SELECT P.id FROM Pacjent P WHERE P.pesel = '" + peseladd.getText() + "'";
        
        Query query = session.createQuery(hql);
        List results = query.list();
        if(results.isEmpty() && peselcheck())
        {
            if(imieadd.getText() == null || nazwiskoadd.getText() == null || wiekadd.getValue() == null || peseladd.getText() == null)
            {
               alert(alertpacpola);
            }
            else
            {
                pa = new Pacjent(imieadd.getText(), nazwiskoadd.getText(), Date.valueOf(wiekadd.getValue()), Long.parseLong(peseladd.getText()));
                pamysql.insert(pa);
                alert(alertpacadd);
                imieadd.clear();
                nazwiskoadd.clear();
                wiekadd.getEditor().clear();
                peseladd.clear();
            }
        }
        else
        {
            alert(alertpeseladd);
        }
    }
    
    private boolean peselcheck(){
        String pesel = peseladd.getText();
        if(pesel.length() == 11)
            return true;
        else
            return false;
    }

    @FXML
    private void pacjentedit(ActionEvent event) {
        if(imieedit.getText().isEmpty() || nazwiskoedit.getText().isEmpty() || datauredit.getEditor().getText().isEmpty() || peseledit.getText().isEmpty())
        {
            
        }
        else
        {
            pa.setImie(imieedit.getText());
            pa.setNazwisko(nazwiskoedit.getText());
            pa.setDataUr(Date.valueOf(datauredit.getValue()));
            pa.setPesel(Long.parseLong(peseledit.getText()));
            pamysql.update(pa);
            alert(alertpacedit);
        }
    }
    
    @FXML
    private void pacjentshowpane(Event event) {
        pacjentiimie.setText(pa.getImie());
        pacjentnazwisko.setText(pa.getNazwisko());
        pacjentdataur.setText(String.valueOf(pa.getDataUr()));
        pacjentpesel.setText(String.valueOf(pa.getPesel()));
    }
    
    @FXML
    private void pacjenteditshow(Event event) {
        imieedit.setText(pa.getImie());
        nazwiskoedit.setText(pa.getNazwisko());
        String date = String.valueOf(pa.getDataUr());
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        datauredit.setValue(sqlDate.toLocalDate());
        peseledit.setText(String.valueOf(pa.getPesel()));
    }
    
    @FXML
    private void wizytadel(ActionEvent event) {
        if(tablewizyty.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            wizmysql.delete(tablewizyty.getSelectionModel().getSelectedItem());
            alert(alertwizdel);
            wizytyshow();
        }
    }

    @FXML
    private void wizytaadd(ActionEvent event) {
        if(tablewizadd.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            wiz = new Wizyty(pa, tablewizadd.getSelectionModel().getSelectedItem(), Date.valueOf(wizadddata.getValue()));
            wizmysql.insert(wiz);
            wizytyshow();
            alert(alertwizadd);
            wizadddata.getEditor().clear();
            tablewizadd.getSelectionModel().clearSelection();
        }
    }

    @FXML
    private void tabwiz(Event event) {
        wizytyshow();
        lekarzshow();
    }
    
    private void wizytyshow(){
        tablewizyty.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        //String hql = "SELECT W.id FROM Wizyty W, Pacjent P WHERE P.id = '" + pa.getIdPacjent() + "'";
        
        String hql = "SELECT W.id FROM Wizyty W WHERE W.pacjent = '" + pa.getIdPacjent() + "'";
        
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            wiz = (Wizyty) session.get(Wizyty.class, (Integer)query.list().get(i));
            //System.out.println(wiz.getPracownik());
            tablewizyty.getItems().add(wiz);
        }
        session.close();
    }
    
    private void lekarzshow(){
        tablewizadd.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT P.id FROM Pracownik P WHERE P.rola = 'Lekarz'";
        
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            pra = (Pracownik) session.get(Pracownik.class, (Integer)query.list().get(i));
            //System.out.println(wiz.getPracownik());
            tablewizadd.getItems().add(pra);
        }
        session.close();
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
    private void pacjentsearch(ActionEvent event) {
        tablepacjent.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();

        String hql = "SELECT P.id FROM Pacjent P WHERE"
                + "  P.imie LIKE '%" + imiesearch.getText() + "%' AND"
                + " P.nazwisko LIKE '%" + nazwiskosearch.getText() + "%' AND"
                + " P.pesel LIKE '%" + peselsearch.getText() + "%'";

        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            pa = (Pacjent) session.get(Pacjent.class, (Integer)query.list().get(i));
            tablepacjent.getItems().add(pa);
        }
        session.close();
    }

    @FXML
    private void peseldata(ActionEvent event) {
        String data = wiekadd.getEditor().getText();
        String datacheck = data.substring(6, 8);
        int a = Integer.parseInt(data.substring(3,5));

        if(datacheck.equals("19"))
        {
            datacheck = data.substring(8) + data.substring(3,5) + data.substring(0,2);  
        }
        else if(datacheck.equals("20"))
        {
            datacheck = data.substring(8) + String.valueOf(a + 20) + data.substring(0,2);  
        }
        peseladd.setText(datacheck);
    }

}
