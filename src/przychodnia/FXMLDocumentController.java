/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package przychodnia;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Soprano
 */
public class FXMLDocumentController implements Initializable {
    Pacjenci k = new Pacjenci();
    
    @FXML
    private TextField imieadd;
    @FXML
    private TextField nazwiskoadd;
    @FXML
    private TextField wiekadd;
    @FXML
    private TextArea pacjentdisp;
    @FXML
    private Button pacjentadd;
    
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
        k.addpacjenci(imieadd.getText(), nazwiskoadd.getText(), Integer.parseInt(wiekadd.getText()));
        //pacjentdisp.appendText(k.pacjenci.toString());
        pacjentdisp.setText(k.writepacjenci());
    }
}
