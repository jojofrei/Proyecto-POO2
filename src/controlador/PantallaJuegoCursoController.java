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
import javafx.event.ActionEvent;
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
    
    @FXML
    private void servir_Cliente1(ActionEvent event) 
    {
        boolean estadoServicioCliente1 = false;
        if(listaProductosCocinando.isEmpty()){
            //mostrarAlerta("No puede servir al cliente, ya que no hay productos cocinandose");
        } 
        else
        {
            if(paciencia_cliente1 <= 0)
            {
                //mostrarAlerta("Se acabo el tiempo, acabas de perder al cliente");
                //mostrarClientesPerdidos();
            }
            else
            {
                //estadoServicioCliente1 = helper.HelperJuego.verificarEstadoOrdenServida(cliente1.getProductosOrdenados(), listaProductosCocinando);
                if(estadoServicioCliente1)
                {    
                    listaProductosCocinando.clear();
                    VBox_Productos_Cocinando.getChildren().clear();
                    
                    //removerProductosCocinandose(cliente1.getProductosOrdenados());                        
                    System.out.println("Cliente Servido correctamente");
                    dineroAcumulado += (paciencia_cliente1*10);
                    //mostrarDineroAcumulado();
                    Lbl_Paciencia_Cli1.setText("Cliente ganado");
                    btn_Servir_Cli1.setDisable(true);
                    timer_Cliente1.cancel();
                    pane_cli1.setVisible(false);
                    //cargarHBoxCategoria1(listaProductosCategoria1);
                    //cargarHBoxCategoria2(listaProductosCategoria2);        
                }
            }                            
        }
    }
    
    @FXML
    private void servir_Cliente2(ActionEvent event) 
    {
        boolean estadoServicioCliente2 = false;
        if(listaProductosCocinando.isEmpty()){
            //mostrarAlerta("No puede servir al cliente, ya que no hay productos cocinandose");
        }
        else
        {
            if(paciencia_cliente2 <= 0)
            {
                //mostrarAlerta("Se acabo el tiempo, acabas de perder al cliente");                
                //mostrarClientesPerdidos();
            }
            else
            {
                //estadoServicioCliente2 = helper.HelperJuego.verificarEstadoOrdenServida(cliente2.getProductosOrdenados(), listaProductosCocinando);
                if(estadoServicioCliente2)
                {    
                    //removerProductosCocinandose(cliente2.getProductosOrdenados());                        
                    listaProductosCocinando.clear();
                    VBox_Productos_Cocinando.getChildren().clear();
                    
                    System.out.println("Cliente Servido correctamente");
                    dineroAcumulado += (paciencia_cliente2*10);
                    //mostrarDineroAcumulado();
                    Lbl_Paciencia_Cli2.setText("Cliente ganado");
                    btn_Servir_Cli2.setDisable(true);
                    timer_Cliente2.cancel();
                    pane_cli2.setVisible(false);
                    //cargarHBoxCategoria1(listaProductosCategoria1);
                    //cargarHBoxCategoria2(listaProductosCategoria2);        
                }
            }                            
        }
            
    }
    
    @FXML
    private void servir_Cliente3(ActionEvent event) 
    {
        boolean estadoServicioCliente3 = false;
        if(listaProductosCocinando.isEmpty()){
            //mostrarAlerta("No puede servir al cliente, ya que no hay productos cocinandose");
        }
        else
        {
            if(paciencia_cliente3 <= 0)
            {
                //mostrarAlerta("Se acabo el tiempo, acabas de perder al cliente");                
                //mostrarClientesPerdidos();
            }
            else
            {
                //estadoServicioCliente3 = helper.HelperJuego.verificarEstadoOrdenServida(cliente3.getProductosOrdenados(), listaProductosCocinando);
                if(estadoServicioCliente3)
                {    
                    listaProductosCocinando.clear();
                    VBox_Productos_Cocinando.getChildren().clear();
                    
                    //removerProductosCocinandose(cliente3.getProductosOrdenados());                        
                    System.out.println("Cliente Servido correctamente");
                    dineroAcumulado += (paciencia_cliente3*10);
                    //mostrarDineroAcumulado();
                    Lbl_Paciencia_Cli3.setText("Cliente ganado");
                    btn_Servir_Cli3.setDisable(true);
                    timer_Cliente3.cancel();
                    pane_cli3.setVisible(false);
                    //cargarHBoxCategoria1(listaProductosCategoria1);
                    //cargarHBoxCategoria2(listaProductosCategoria2);        
                }
            }                            
        }
    }
    
    private void limpiarHBoxVBox()
    {
        HBox_Categoria1_productos.getChildren().clear();
        HBox_Categoria2_productos.getChildren().clear();
        HBox_Cli1_productos.getChildren().clear();
        HBox_Cli2_productos.getChildren().clear();
        HBox_Cli3_productos.getChildren().clear();
        
        VBox_Productos_Cocinando.getChildren().clear();
    }
    
}
