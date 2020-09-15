/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import helper.HelperJuego;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Juego;

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
    
    @FXML
    private void comenzarJuego(ActionEvent event) 
    {
        try 
        {            
            Juego juegoActual = HelperJuego.validarIngresoJugador(txt_usuario.getText().trim());            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/PantallaJuegoCurso.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Juego comenzado: "+juegoActual.getNombreUsuario());
            stage.setScene(new Scene(root));  
            stage.setWidth(1060);            
            stage.setResizable(false);
            stage.setHeight(750);
            PantallaJuegoCursoController controlador = fxmlLoader.<PantallaJuegoCursoController> getController();
            controlador.cargarJuego(juegoActual.getNombreUsuario(),juegoActual.getNivelEnCurso());
            stage.show();            
            Stage stageActual = (Stage) btn_Jugar.getScene().getWindow();            
            stageActual.close();
            
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }    
    
}
