/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodniaFXML;

import hibernate.HibernateUtil;
import hibernate.KartaMySQL;
import hibernate.PacjentkartaMySQL;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.hibernate.Query;
import org.hibernate.Session;
import przychodnia.Globalne;
import przychodnia.Karta;
import przychodnia.Pacjent;
import przychodnia.Pacjentkarta;
import przychodnia.PacjentkartaId;
import przychodnia.Pracownik;
import przychodnia.Wizyty;
import przychodniaFXML.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author Soprano
 */
public class FXMLLekarzController implements Initializable {

    String idpra = Globalne.idpra;
    Wizyty wiz;
    
    Karta kar;
    KartaMySQL karmysql;
    PacjentkartaMySQL pkmysql;
    
    Pacjent pa = new Pacjent();
    
    

    static Session session = null;
    
    @FXML
    private AnchorPane rootpane;
    
    //panele
    @FXML
    private AnchorPane panewizytyshow;
    @FXML
    private TabPane panepacjent;
    
    
    @FXML
    private TableView<Wizyty> tablewizytyshow;
    @FXML
    private TableColumn<Wizyty, String> colwizdata;
    @FXML
    private TableColumn<Wizyty, Pacjent> colwizdane;
    @FXML
    private ComboBox<String> searchcurrent;
    @FXML
    private TextField searchpesel;
    
    
    //dane
    @FXML
    private Label pacjentiimie;
    @FXML
    private Label pacjentnazwisko;
    @FXML
    private Label pacjentdataur;
    @FXML
    private Label pacjentpesel;
    
    //choryby
    @FXML
    private TableView<Karta> tablechor;
    @FXML
    private TableColumn<Karta, String> colchornazwa;
    @FXML
    private TableColumn<Karta, String> colchoropis;
    
    
    @FXML
    private TextField wpisnazadd;
    @FXML
    private TextArea wpisopisadd;
    @FXML
    private AnchorPane paneadd;
    @FXML
    private TextField choreditnaz;
    @FXML
    private TextArea choreditopis;
    @FXML
    private Label alertedit;
    @FXML
    private Label alertdel;
    @FXML
    private Label alertwpisadd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colwizdata.setCellValueFactory(new PropertyValueFactory<>("dataWiz"));
        colwizdane.setCellValueFactory(new PropertyValueFactory<>("pacjent"));
        
        searchcurrent.getItems().clear();
        searchcurrent.getItems().addAll("Wszystkie", "Nadchodzące");
        searchcurrent.getSelectionModel().select("Wszystkie");
        
        colchornazwa.setCellValueFactory(new PropertyValueFactory<>("choroba"));
        colchoropis.setCellValueFactory(new PropertyValueFactory<>("opis"));
        
        showwizyty();
    }  
    
    private void showwizyty(){
        String c = "W.dataWiz";
        String pesel = "";
        if(searchcurrent.getValue().equals("Nadchodzące"))
            c = "CURDATE()";
        if(!searchpesel.getText().isEmpty())
            pesel = searchpesel.getText();
        
        System.out.println(c + " " + pesel);
        tablewizytyshow.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        String hql = "SELECT W.id FROM Wizyty W WHERE W.pracownik = '" + idpra + "' AND W.dataWiz >= " + c;
//                + " AND W.pacjent.pesel = '%" + pesel + "%'";
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            wiz = (Wizyty) session.get(Wizyty.class, (Integer)query.list().get(i));
            //System.out.print(wiz.getPacjent());
            wiz.getPacjent();
            tablewizytyshow.getItems().add(wiz);
        }
        
        session.close();
    }

    @FXML
    private void searchwizyty(ActionEvent event) {
        showwizyty();
    }

    @FXML
    private void showpacjent(ActionEvent event) {
        if(tablewizytyshow.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            paneadd.setVisible(false);
            panewizytyshow.setVisible(false);
            panepacjent.setVisible(true);
            pa = tablewizytyshow.getSelectionModel().getSelectedItem().getPacjent();
            chorobyshow();
            pacjentiimie.setText(pa.getImie());
            pacjentnazwisko.setText(pa.getNazwisko());
            pacjentdataur.setText(String.valueOf(pa.getDataUr()));
            pacjentpesel.setText(String.valueOf(pa.getPesel()));
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
    private void chordel(ActionEvent event) {
        if(tablechor.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            System.out.println(tablechor.getSelectionModel().getSelectedItem());
            karmysql.delete(tablechor.getSelectionModel().getSelectedItem());
            chorobyshow();
            alert(alertdel);
        }
    }
    
    private void alert(Label a){
        a.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(
            event -> a.setVisible(false)
        );
        visiblePause.play();
    }
    
    private void chorobyshow(){
        tablechor.getItems().clear();
        session = HibernateUtil.getSessionFactory().openSession();
        System.out.println(pa.getIdPacjent());
        //String hql = "SELECT K.id FROM Karta K, Pacjent P WHERE P.id = '" + pa.getIdPacjent() + "'";
        
        String hql = "SELECT PK.karta.id FROM Pacjentkarta PK WHERE PK.pacjent.id = '" + pa.getIdPacjent() + "'";
        
        Query query = session.createQuery(hql);
        List results = query.list();
        
        for(int i = 0; i < results.size(); i++)
        {
            kar = (Karta) session.get(Karta.class, (Integer)query.list().get(i));
            tablechor.getItems().add(kar);
        }
        session.close();
    }

    @FXML
    private void tabchor(Event event) {
        chorobyshow();
    }

    @FXML
    private void showwiz(ActionEvent event) {
        panepacjent.setVisible(false);
        paneadd.setVisible(false);
        panewizytyshow.setVisible(true);
    }

    @FXML
    private void wpisadd(ActionEvent event) {
        if(wpisnazadd.getText().isEmpty() || wpisopisadd.getText().isEmpty())
        {
            
        }
        else
        {
            
            Pacjentkarta pk;
            PacjentkartaId pkid;
            kar = new Karta(wpisnazadd.getText(), wpisopisadd.getText());
            karmysql.insert(kar);
            System.out.println(kar.getIdKarta());
            pkid = new PacjentkartaId(pa.getIdPacjent(), kar.getIdKarta());
            pk = new Pacjentkarta(pkid, kar, pa);
            pkmysql.insert(pk);
            alert(alertwpisadd);
            wpisnazadd.clear();
            wpisopisadd.clear();
        }
    }

    @FXML
    private void choredit(ActionEvent event) {
        if(choreditnaz.getText().isEmpty() || choreditopis.getText().isEmpty())
        {
            
        }
        else
        {
            kar.setChoroba(choreditnaz.getText());
            kar.setOpis(choreditopis.getText());
            karmysql.update(kar);
            //alertedit.setVisible(true);
            alert(alertedit);
        }
    }

    @FXML
    private void choreditshow(ActionEvent event) {
        if(tablechor.getSelectionModel().isEmpty())
        {
            
        }
        else
        {
            
            panewizytyshow.setVisible(false);
            panepacjent.setVisible(false);
            paneadd.setVisible(true);
            choreditnaz.setText(tablechor.getSelectionModel().getSelectedItem().getChoroba());
            choreditopis.setText(tablechor.getSelectionModel().getSelectedItem().getOpis());
        }
    }

    @FXML
    private void chorback(ActionEvent event) {
        panewizytyshow.setVisible(false);
        paneadd.setVisible(false);
        panepacjent.setVisible(true);
        chorobyshow();
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
    
}
