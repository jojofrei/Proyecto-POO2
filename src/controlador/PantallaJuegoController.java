/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jojofrei
 */
public class PantallaJuegoController implements Initializable {

    @FXML
    private Button btn_Jugar;

    //Variables
    boolean usuarioExisteJuego;
    @FXML
    private TextField txt_usuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }       
    
}
