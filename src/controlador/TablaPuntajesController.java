/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import helper.HelperJuego;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Puntaje;

/**
 * FXML Controller class
 *
 * @author jojofrei
 */
public class TablaPuntajesController implements Initializable {

    @FXML
    private TextField txt_jugador;
    @FXML
    private ListView<String> lst_puntajes;
    @FXML
    private Label lbl_lista_puntajes;
    @FXML
    private Label lbl_Sinpuntajes;
    
    private ObservableList<String> listaPuntajes = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_Sinpuntajes.setVisible(true);
        lbl_lista_puntajes.setVisible(false);
    }    
