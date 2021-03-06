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
    @FXML
    private void consultarPuntajes(ActionEvent event) 
    {
        mostrarListadoPuntajes(txt_jugador.getText().trim());
    }
     private void mostrarListadoPuntajes(String jugador)
    {
        if(jugador.trim().isEmpty())
            mostrarAlertaErrorIngreso("Debe regisrar el nombre del jugador");
        else
        {
            lbl_Sinpuntajes.setVisible(false);        
            lst_puntajes.getItems().clear();
            ArrayList<Puntaje> listaPuntaje = helper.HelperJuego.getListadoPuntajes(jugador);
            if(listaPuntaje.isEmpty())
                lbl_Sinpuntajes.setVisible(true);
            else
            {
                lbl_lista_puntajes.setVisible(true);
                for(Puntaje puntaje: listaPuntaje)
                    listaPuntajes.add(puntaje.getJugador()+"-"+puntaje.getNivel()+"-"+puntaje.getPuntaje());                           
                lst_puntajes.setItems(listaPuntajes);
            }
        }
    }
      private void mostrarAlertaErrorIngreso(String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensaje de Sistema");
        alert.setHeaderText(mensaje);        
        alert.showAndWait();    
    }
    
}



