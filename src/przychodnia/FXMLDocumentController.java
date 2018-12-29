/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodnia;

import hibernate.*;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Soprano
 */
public class FXMLDocumentController implements Initializable {
    Pacjent p;
    PacjentMySQL pmysql;
    
    @FXML
    private TextField imieadd;
    @FXML
    private TextField nazwiskoadd;
    @FXML
    private DatePicker wiekadd;
    @FXML
    private TextArea pacjentdisp;
    @FXML
    private Button pacjentadd;
    @FXML
    private Button pacjentdel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void pacjentadd(ActionEvent event) {
        if(imieadd.getText() == null || nazwiskoadd.getText() == null || wiekadd.getValue() == null)
        {
            System.out.println("wypełnij wszystkie pola");
        }
        else
        {
            p = new Pacjent(imieadd.getText(), nazwiskoadd.getText(), Date.valueOf(wiekadd.getValue()));
            pmysql.insert(p);
        }
        //pacjentdisp.appendText(k.pacjenci.toString());
        //pacjentdisp.setText(k.writepacjenci());
    }
}
