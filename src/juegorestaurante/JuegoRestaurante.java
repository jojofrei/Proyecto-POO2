package juegorestaurante;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JuegoRestaurante extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent p= FXMLLoader.load(getClass().getResource("/vista/PantallaPrincipal.fxml"));
            Scene sc = new Scene(p,335,380);            
            stage.setScene(sc);
            stage.setResizable(false);
            stage.setTitle("Juego Restaurante Cafeteria");
            stage.show();           
        }
        catch (Exception ex) {            
        }
    }
    
}