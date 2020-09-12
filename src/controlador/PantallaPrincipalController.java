/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jojofrei
 */
public class PantallaPrincipalController implements Initializable {

     @FXML
    private Button btn_ComenzarJuego;
    @FXML
    private Button btn_VerTablaPuntaje;
    @FXML
    private Button btn_SalirJuego;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void comenzarJuego(ActionEvent event) 
    {
        try 
        {
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/PantallaJuegoCurso.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/PantallaJuego.fxml"));            
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            //stage.setTitle("Juego Restaurante");            
            stage.setTitle("Selección de usuario");            
            stage.setScene(new Scene(root));
            //stage.setWidth(1060);
            stage.setWidth(400);
            //stage.setResizable(false);
            //stage.setHeight(750);
            stage.setHeight(200);
            stage.show();            
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void verTablaPuntajes(ActionEvent event) 
    {
        try 
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/TablaPuntajes.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Tabla de Puntajes");
            stage.setScene(new Scene(root));  
            stage.show();            
        } 
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void salirJuego(ActionEvent event) 
    {
        Stage stage = (Stage) btn_SalirJuego.getScene().getWindow();        
        stage.close();
    }
    
}
    

