/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Cliente;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author jojofrei
 */
public class PantallaJuegoCursoController implements Initializable {
    
    @FXML
    private Label lbl_cat1;
        
    private int cantProductosNivel;
    
    private ArrayList<Producto> listaProductosCategoria1;
    private ArrayList<Producto> listaProductosCategoria2;
    private ArrayList<Producto> listaProductosClientes;
    private ArrayList<Producto> listaProductosCocinando;
    
    private int paciencia_cliente1;
    private int paciencia_cliente2;
    private int paciencia_cliente3;
    
    Timer timer_Cliente1;
    Timer timer_Cliente2;
    Timer timer_Cliente3;
    
    Cliente cliente1;
    Cliente cliente2;
    Cliente cliente3;
    ArrayList<Cliente> listaClientes;
    
    private String nombreJugador;
    private int nivelEnCurso;
    private double dineroAcumulado;
    private int clientesPerdidos;
        
    @FXML
    private HBox HBox_Categoria1_productos;
    @FXML
    private Label lbl_cat2;
    @FXML
    private HBox HBox_Categoria2_productos;    
    @FXML
    private Pane pane_cli2;
    @FXML
    private ImageView img_cli2;
    @FXML
    private Pane pane_cli1;
    @FXML
    private ImageView img_cli1;
    @FXML
    private Pane pane_cli3;
    @FXML
    private ImageView img_cli3;
    @FXML
    private HBox HBox_Cli2_productos;
    @FXML
    private HBox HBox_Cli1_productos;
    @FXML
    private HBox HBox_Cli3_productos;
    @FXML
    private Label Lbl_Paciencia_Cli2;
    @FXML
    private Label Lbl_Paciencia_Cli1;
    @FXML
    private Label Lbl_Paciencia_Cli3;
    @FXML
    private VBox VBox_Productos_Cocinando;
    @FXML
    private Button btn_Servir_Cli2;
    @FXML
    private Button btn_Servir_Cli1;
    @FXML
    private Button btn_Servir_Cli3;
    @FXML
    private Label Lbl_Nivel;
    @FXML
    private Label Lbl_DineroAcumulado;
    @FXML
    private Label Lbl_Cocinando;
    @FXML
    private Label Lbl_ClientesPerdidos;
    

  
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        nivelEnCurso = 1;
        cantProductosNivel = 4;
        clientesPerdidos = 0;
        VBox_Productos_Cocinando.setSpacing(15);        
        Lbl_ClientesPerdidos.setText("Perdidos: "+clientesPerdidos);
        
    } 
    
    public void cargarJuego(String Jugador,int nivel)
    {
        nombreJugador = Jugador;
        nivelEnCurso  = nivel;
    }
    
}
