package controlador;

import helper.Constantes;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import modelo.Producto;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Cliente;

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
    private boolean cliente1Perdido;
    private boolean cliente2Perdido;
    private boolean cliente3Perdido;
    
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
        
    }
    
    public void cargarJuego(String Jugador,int nivel)
    {
        nombreJugador = Jugador;
        if(nivel == 0)
            nivelEnCurso = 1;
        else
            nivelEnCurso = (nivel == 3) ? 1 : nivel;
        
        clientesPerdidos = 0;
        VBox_Productos_Cocinando.setSpacing(15);        
        Lbl_ClientesPerdidos.setText("Perdidos: "+clientesPerdidos);
        
        inicializarJuego();                                        
        cargarCategoriasProductos();
        
        cargarHBoxCategoria1(listaProductosCategoria1);        
        cargarHBoxCategoria2(listaProductosCategoria2);
        
        
        //Carga Inicial Clientes
        cargarClientes();
                
        mostrarClientesPerdidos();
        mostrarDineroAcumulado();        
    }
                                    
    @FXML
    private void servir_Cliente1(ActionEvent event) 
    {
        boolean estadoServicioCliente1 = false;
        if(listaProductosCocinando.isEmpty())
            mostrarAlerta("No puede servir al cliente, ya que no hay productos cocinandose");
        else
        {
            if(paciencia_cliente1 <= 0)
            {
                mostrarAlerta("Se acabo el tiempo, acabas de perder al cliente");
                mostrarClientesPerdidos();                
            }
            else
            {
                estadoServicioCliente1 = helper.HelperJuego.verificarEstadoOrdenServida(cliente1.getProductosOrdenados(), listaProductosCocinando);
                if(estadoServicioCliente1)
                {    
                    listaProductosCocinando.clear();
                    VBox_Productos_Cocinando.getChildren().clear();
                    
                    //removerProductosCocinandose(cliente1.getProductosOrdenados());                        
                    System.out.println("Cliente Servido correctamente");
                    dineroAcumulado += (paciencia_cliente1*10);
                    mostrarDineroAcumulado();
                    Lbl_Paciencia_Cli1.setText("Cliente ganado");
                    btn_Servir_Cli1.setDisable(true);
                    timer_Cliente1.cancel();
                    pane_cli1.setVisible(false);
                    mostrarProgresoNivel();                                                                                
                }
            }                            
        }
    }
    
    @FXML
    private void servir_Cliente2(ActionEvent event) 
    {
        boolean estadoServicioCliente2 = false;
        if(listaProductosCocinando.isEmpty())
            mostrarAlerta("No puede servir al cliente, ya que no hay productos cocinandose");
        else
        {
            if(paciencia_cliente2 <= 0)
            {
                mostrarAlerta("Se acabo el tiempo, acabas de perder al cliente");                
                mostrarClientesPerdidos();                
            }
            else
            {
                estadoServicioCliente2 = helper.HelperJuego.verificarEstadoOrdenServida(cliente2.getProductosOrdenados(), listaProductosCocinando);
                if(estadoServicioCliente2)
                {    
                    //removerProductosCocinandose(cliente2.getProductosOrdenados());                        
                    listaProductosCocinando.clear();
                    VBox_Productos_Cocinando.getChildren().clear();
                    
                    System.out.println("Cliente Servido correctamente");
                    dineroAcumulado += (paciencia_cliente2*10);
                    mostrarDineroAcumulado();
                    Lbl_Paciencia_Cli2.setText("Cliente ganado");
                    btn_Servir_Cli2.setDisable(true);
                    timer_Cliente2.cancel();
                    pane_cli2.setVisible(false);
                    mostrarProgresoNivel();                   
                }
            }                            
        }
            
    }
    
    @FXML
    private void servir_Cliente3(ActionEvent event) 
    {
        boolean estadoServicioCliente3 = false;
        if(listaProductosCocinando.isEmpty())
            mostrarAlerta("No puede servir al cliente, ya que no hay productos cocinandose");
        else
        {
            if(paciencia_cliente3 <= 0)
            {
                mostrarAlerta("Se acabo el tiempo, acabas de perder al cliente");                
                mostrarClientesPerdidos();                
            }
            else
            {
                estadoServicioCliente3 = helper.HelperJuego.verificarEstadoOrdenServida(cliente3.getProductosOrdenados(), listaProductosCocinando);
                if(estadoServicioCliente3)
                {    
                    listaProductosCocinando.clear();
                    VBox_Productos_Cocinando.getChildren().clear();
                    
                    //removerProductosCocinandose(cliente3.getProductosOrdenados());                        
                    System.out.println("Cliente Servido correctamente");
                    dineroAcumulado += (paciencia_cliente3*10);
                    mostrarDineroAcumulado();
                    Lbl_Paciencia_Cli3.setText("Cliente ganado");
                    btn_Servir_Cli3.setDisable(true);
                    timer_Cliente3.cancel();
                    pane_cli3.setVisible(false);
                    mostrarProgresoNivel();                    
                }
            }                            
        }
    }
                                    
    @FXML
    private void cargarSiguienteRonda(ActionEvent event) 
    {
        cargarSiguienteServida();
    }
    
/******************************************************************************/    
    //Metodos de Timers de los clientes
    //Permite iniciar el conteo regresivo de la paciencia del cliente
    private void iniciarConteoRegresivoCliente1()
    {
        timer_Cliente1 = new Timer();
        timer_Cliente1.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(paciencia_cliente1 > 0)
                {
                    Platform.runLater(() -> mostrarPacienciaRegresivaCliente1(paciencia_cliente1));                    
                    paciencia_cliente1--;
                }
                else
                {
                    timer_Cliente1.cancel();
                    clientesPerdidos++;
                    Platform.runLater(() -> cargarClientePerdido(1));
                }                    
            }
        }, 1000,1000);
    }
    
    //Permite iniciar el conteo regresivo de la paciencia del cliente
    private void iniciarConteoRegresivoCliente2()
    {   
        timer_Cliente2 = new Timer();
        timer_Cliente2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(paciencia_cliente2 > 0)
                {
                    Platform.runLater(() -> mostrarPacienciaRegresivaCliente2(paciencia_cliente2));                    
                    paciencia_cliente2--;
                }
                else
                {
                    timer_Cliente2.cancel();
                    clientesPerdidos++;
                    Platform.runLater(() -> cargarClientePerdido(2));
                }                    
            }
        }, 1000,1000);
    }
    
    //Permite iniciar el conteo regresivo de la paciencia del cliente
    private void iniciarConteoRegresivoCliente3()
    {
        timer_Cliente3 = new Timer();
        timer_Cliente3.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(paciencia_cliente3 > 0)
                {
                    Platform.runLater(() -> mostrarPacienciaRegresivaCliente3(paciencia_cliente3));                    
                    paciencia_cliente3--;
                }
                else
                {
                    timer_Cliente3.cancel();
                    clientesPerdidos++;
                    Platform.runLater(() -> cargarClientePerdido(3));
                }                    
            }
        }, 1000,1000);
    }
    
    private void mostrarPacienciaRegresivaCliente1(int paciencia)
    {
        Lbl_Paciencia_Cli1.setText("Paciencia: "+String.valueOf(paciencia));
    }
    
    private void mostrarPacienciaRegresivaCliente2(int paciencia)
    {
        Lbl_Paciencia_Cli2.setText("Paciencia: "+String.valueOf(paciencia));
    }
    
    private void mostrarPacienciaRegresivaCliente3(int paciencia)
    {
        Lbl_Paciencia_Cli3.setText("Paciencia: "+String.valueOf(paciencia));
    }
    
    //Mostrar alertas
    private void mostrarAlerta(String mensaje)
    {               
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Mensaje de Sistema");
        alert.setHeaderText(mensaje);        
        alert.showAndWait();
    }
    
    private void mostrarAlertaJuegoGanado(String mensaje)
    {               
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Mensaje de Sistema");
        alert.setHeaderText(mensaje);        
        ButtonType botonSi = new ButtonType("Si");                    
        alert.getButtonTypes().setAll(botonSi);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == botonSi)
        {
            timer_Cliente1.cancel();
            timer_Cliente2.cancel();
            timer_Cliente3.cancel();            
            reactivarJuego();
        }
    }
    
    private void mostrarAlertaJuegoPerdido(String mensaje)
    {    
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mensaje de Sistema");
        alert.setHeaderText(mensaje);
        ButtonType botonSi = new ButtonType("Si");                    
        alert.getButtonTypes().setAll(botonSi);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == botonSi)            
            reactivarJuego();                                                
    }
    
    private void mostrarAlertaPasasteNivel(String mensaje)
    {    
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Mensaje de Sistema");
        alert.setHeaderText(mensaje);
        ButtonType botonSi = new ButtonType("Si");                    
        alert.getButtonTypes().setAll(botonSi);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == botonSi) 
        {
            helper.HelperJuego.actualizarRegistroUsuario(nombreJugador, nivelEnCurso, dineroAcumulado);        
            cargarSiguienteServida();
        }
    }
    
    private void mostrarProgresoNivel()
    {
        int nivelActual = nivelEnCurso;
        nivelEnCurso = helper.HelperJuego.chequearSiEsSiguienteNivel(dineroAcumulado, nivelEnCurso);
        if(nivelEnCurso > 3)
            mostrarAlertaJuegoGanado("Has ganado el juego, eres campeon...");
        else if(nivelEnCurso > nivelActual)                    
            mostrarAlertaPasasteNivel("Has pasado de nivel");
        else
            if(!pane_cli1.isVisible() && !pane_cli2.isVisible() && !pane_cli3.isVisible())
                cargarSiguienteServida();
        Lbl_Nivel.setText("Nivel: "+nivelEnCurso);
    }    
    
    //Cargar clientes
    public void cargarClientes()
    {
        btn_Servir_Cli1.setDisable(false);
        btn_Servir_Cli2.setDisable(false);
        btn_Servir_Cli3.setDisable(false);
        listaClientes = helper.HelperJuego.getListaRandomClientes(listaProductosClientes);
        pane_cli1.setVisible(false);
        pane_cli2.setVisible(false);
        pane_cli3.setVisible(false);
        
        timer_Cliente1.cancel();
        timer_Cliente2.cancel();
        timer_Cliente3.cancel();
                
        for(Cliente cliente : listaClientes)
        {
            switch(cliente.getNumeroCliente())
            {
                case 1:
                    cargarCliente1(cliente);
                    break;
                
                case 2:                    
                    cargarCliente2(cliente);
                    break;
                
                case 3:
                    cargarCliente3(cliente);
                    break;
            }            
        }
    }
        
    //Cargar clientes
    private void cargarCliente1(Cliente cliente)
    {
        cliente1 = cliente;
        pane_cli1.setVisible(true);
        img_cli1.setImage(helper.HelperJuego.getImagenCliente(cliente.getNombreImagen()));
        cargarProductosCliente1(cliente);
        paciencia_cliente1 = cliente.getPaciencia();
        iniciarConteoRegresivoCliente1();        
    }
    
    private void cargarCliente2(Cliente cliente)
    {
        cliente2 = cliente;
        pane_cli2.setVisible(true);
        img_cli2.setImage(helper.HelperJuego.getImagenCliente(cliente.getNombreImagen()));
        cargarProductosCliente2(cliente);
        paciencia_cliente2 = cliente.getPaciencia();
        iniciarConteoRegresivoCliente2();        
    }
    
    private void cargarCliente3(Cliente cliente)
    {
        cliente3 = cliente;
        pane_cli3.setVisible(true);
        img_cli3.setImage(helper.HelperJuego.getImagenCliente(cliente.getNombreImagen()));
        cargarProductosCliente3(cliente);
        paciencia_cliente3 = cliente.getPaciencia();
        iniciarConteoRegresivoCliente3();
    }
    
    //Cargar productos Clientes    
    private void cargarProductosCliente1(Cliente cliente)
    {
        HBox_Cli1_productos.getChildren().clear();
        for(Producto producto : cliente.getProductosOrdenados())
        {
            ImageView Img_Cliente1_prod = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
            Img_Cliente1_prod.setFitWidth(45);
            Img_Cliente1_prod.setFitHeight(45);            
            Img_Cliente1_prod.setCursor(Cursor.HAND);            
            HBox_Cli1_productos.getChildren().add(Img_Cliente1_prod);
        }        
    }
    
    private void cargarProductosCliente2(Cliente cliente)
    {   
        HBox_Cli2_productos.getChildren().clear();
        for(Producto producto : cliente.getProductosOrdenados())
        {
            ImageView Img_Cliente2_prod = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
            Img_Cliente2_prod.setFitWidth(45);
            Img_Cliente2_prod.setFitHeight(45);            
            Img_Cliente2_prod.setCursor(Cursor.HAND);            
            HBox_Cli2_productos.getChildren().add(Img_Cliente2_prod);
        }        
    }
    
    private void cargarProductosCliente3(Cliente cliente)
    {
        HBox_Cli3_productos.getChildren().clear();
        for(Producto producto : cliente.getProductosOrdenados())
        {
            ImageView Img_Cliente3_prod = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
            Img_Cliente3_prod.setFitWidth(45);
            Img_Cliente3_prod.setFitHeight(45);            
            Img_Cliente3_prod.setCursor(Cursor.HAND);            
            HBox_Cli3_productos.getChildren().add(Img_Cliente3_prod);
        }        
    }
    
    //Cargar productos
    private void cargarCategoriasProductos()
    {        
        ArrayList<String> listaCategorias = helper.HelperJuego.getCategoriasProducto();           
        cantProductosNivel = helper.HelperJuego.chequearNumProductosPorNivel(nivelEnCurso);
        System.out.println("nivelEnCurso="+nivelEnCurso+","+cantProductosNivel);
        
        //Mostrar categorias
        lbl_cat1.setText(listaCategorias.get(0));        
        lbl_cat2.setText(listaCategorias.get(1));
        
        //Verificar la cantidad de productos a mostrar por lista 
        
        
        //Mostrar listas de productos por cada categoria
        listaProductosCategoria1 = helper.HelperJuego.getListaProductosCategoria(listaCategorias.get(0), cantProductosNivel);        
        listaProductosCategoria2 = helper.HelperJuego.getListaProductosCategoria(listaCategorias.get(1), cantProductosNivel);        
        
        for(Producto producto: listaProductosCategoria1)                   
            listaProductosClientes.add(producto);        
        for(Producto producto: listaProductosCategoria2)                    
            listaProductosClientes.add(producto);                
    }    
    
    private void cargarHBoxCategoria1(ArrayList<Producto> listaProductos)
    {       
        ImageView Img_Categoria1_prod;
        for(Producto producto : listaProductos)
        {
            System.out.println("1 producto="+producto.getNombre()+","+producto.getCategoria());
            Img_Categoria1_prod = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
            Img_Categoria1_prod.setFitWidth(90);
            Img_Categoria1_prod.setFitHeight(90);            
            Img_Categoria1_prod.setCursor(Cursor.HAND);
            Img_Categoria1_prod.setOnMouseClicked((e) -> {     
                    if(!listaProductosCocinando.contains(producto))
                    {    
                        listaProductosCocinando.add(producto);
                        ImageView Img_Categoria1_prod_temp = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
                        Img_Categoria1_prod_temp.setFitWidth(60);
                        Img_Categoria1_prod_temp.setFitHeight(60);                        
                        VBox_Productos_Cocinando.getChildren().add(Img_Categoria1_prod_temp);
                    }
            });
            HBox_Categoria1_productos.getChildren().add(Img_Categoria1_prod);
        }
        if(VBox_Productos_Cocinando.getChildren().isEmpty())
            Lbl_Cocinando.setVisible(false);
        else
            Lbl_Cocinando.setVisible(true);
            
    }
    
    private void cargarHBoxCategoria2(ArrayList<Producto> listaProductos)
    {
        for(Producto producto : listaProductos)
        {
            System.out.println("2 producto="+producto.getNombre()+","+producto.getCategoria());
            ImageView Img_Categoria2_prod = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
            Img_Categoria2_prod.setFitWidth(90);
            Img_Categoria2_prod.setFitHeight(90);            
            Img_Categoria2_prod.setCursor(Cursor.HAND);
            Img_Categoria2_prod.setOnMouseClicked((e) -> {                    
                    if(!listaProductosCocinando.contains(producto))
                    {    
                        listaProductosCocinando.add(producto);
                        ImageView Img_Categoria2_prod_temp = new ImageView(helper.HelperJuego.getImagenProducto(producto.getNombreImagen()));
                        Img_Categoria2_prod_temp.setFitWidth(60);
                        Img_Categoria2_prod_temp.setFitHeight(60);                        
                        VBox_Productos_Cocinando.getChildren().add(Img_Categoria2_prod_temp);
                    }
            });
            HBox_Categoria2_productos.getChildren().add(Img_Categoria2_prod);
        }
        if(VBox_Productos_Cocinando.getChildren().isEmpty())
            Lbl_Cocinando.setVisible(false);
        else
            Lbl_Cocinando.setVisible(true);
    }
    
    //Mostrar informacion de clientes
    private void cargarClientePerdido(int cliente)
    {        
        switch(cliente)
        {
            case 1:
                btn_Servir_Cli1.setDisable(true);
                Lbl_Paciencia_Cli1.setText("Cliente perdido");                                
                Lbl_ClientesPerdidos.setText("Perdidos: "+clientesPerdidos);
                pane_cli1.setVisible(false);
                mostrarClientesPerdidos();
                break;
                
            case 2:
                btn_Servir_Cli2.setDisable(true);
                Lbl_Paciencia_Cli2.setText("Cliente perdido");                                
                Lbl_ClientesPerdidos.setText("Perdidos: "+clientesPerdidos);
                pane_cli2.setVisible(false);
                mostrarClientesPerdidos();
                break;
                
            case 3:
                btn_Servir_Cli3.setDisable(true);
                Lbl_Paciencia_Cli3.setText("Cliente perdido");              
                Lbl_ClientesPerdidos.setText("Perdidos: "+clientesPerdidos);
                pane_cli3.setVisible(false);
                mostrarClientesPerdidos();
                break;
        }        
    }
    
    private void removerProductosCocinandose(ArrayList<Producto> listaProductosCliente)
    {
        String nombre;
        String url;
        ArrayList<Node> removalCandidates = new ArrayList<>();
        for(Producto producto: listaProductosCliente)
        {
            System.out.println("nombre producto="+producto.getNombreImagen());
            for(Node nodo : VBox_Productos_Cocinando.getChildren())
            {
                ImageView imagenProducto = (ImageView) nodo;
                url = imagenProducto.getImage().getUrl();
                nombre = url.substring(url.lastIndexOf('/')+1, url.length());
                if(producto.getNombreImagen().equalsIgnoreCase(nombre))                
                {
                    System.out.println("Imagen producto="+nombre);
                    removalCandidates.add(nodo);                    
                }
            }
        }
        VBox_Productos_Cocinando.getChildren().removeAll(removalCandidates);
    }
    
    private void mostrarDineroAcumulado()
    {
        int nivelActual = nivelEnCurso;
        System.out.println("Dinero acumulado="+dineroAcumulado);
        Lbl_DineroAcumulado.setText("$ Acumulado: "+dineroAcumulado);        
    }
    
    private void mostrarClientesPerdidos()
    {                
        if(clientesPerdidos >= Constantes.MAXIMA_CANTIDAD_CLIENTES_PERDIDOS_JUEGO_ACABADO)        
            mostrarAlertaJuegoPerdido("Has perdido demasiados clientes, Juego terminado...");
        else if(!pane_cli1.isVisible() && !pane_cli2.isVisible() && !pane_cli3.isVisible())
            cargarSiguienteServida();
    }        
    
    //Reiniciar el juego
    private void reactivarJuego()
    {
        //Apagar timers de clientes
        if(timer_Cliente1 != null)timer_Cliente1.cancel();
        if(timer_Cliente2 != null)timer_Cliente2.cancel();
        if(timer_Cliente3 != null)timer_Cliente3.cancel();
        
        //Resetear listas 
        listaClientes.clear();
        listaProductosCategoria1.clear();
        listaProductosCategoria2.clear();
        listaProductosClientes.clear();
        listaProductosCocinando.clear();
        
        //Resetear HBox
        HBox_Categoria1_productos.getChildren().clear();
        HBox_Categoria2_productos.getChildren().clear();
        HBox_Cli1_productos.getChildren().clear();
        HBox_Cli2_productos.getChildren().clear();
        HBox_Cli3_productos.getChildren().clear();

        //Resetear VBox
        VBox_Productos_Cocinando.getChildren().clear();
        
        clientesPerdidos = 0;
        dineroAcumulado = 0;
        nivelEnCurso = 1;
        mostrarClientesPerdidos();
        mostrarDineroAcumulado();
        
        cargarSiguienteServida();
    }
    
    //Inicializar el juego
    private void inicializarJuego()
    {
        //Instanciar los timers
        if(timer_Cliente1 == null)timer_Cliente1 = new Timer();
        if(timer_Cliente2 == null)timer_Cliente2 = new Timer();
        if(timer_Cliente3 == null)timer_Cliente3 = new Timer();
        
        //Instanciar las listas
        if(listaClientes == null)listaClientes = new ArrayList<>();                
        if(listaProductosCategoria1 == null)listaProductosCategoria1 = new ArrayList<>();        
        if(listaProductosCategoria2 == null)listaProductosCategoria2 = new ArrayList<>();
        if(listaProductosClientes == null)listaProductosClientes = new ArrayList<>();
        if(listaProductosCocinando == null)listaProductosCocinando = new ArrayList<>();
        
        //Resetear paciencias de clientes
        paciencia_cliente1 = 0;
        paciencia_cliente2 = 0;
        paciencia_cliente3 = 0;
        
        //Resetear contadores
        clientesPerdidos = 0;     
    
        //Instanciar los clientes
        if(cliente1 == null) cliente1 = new Cliente();
        if(cliente2 == null) cliente2 = new Cliente();
        if(cliente3 == null) cliente3 = new Cliente();
    
        //Resetear acumulador
        dineroAcumulado = 0;                    
        
        //Resetear estado de los clientes
        cliente1Perdido = false;
        cliente2Perdido = false;
        cliente3Perdido = false;
    }
    
    private void cargarSiguienteServida()
    {
        //Verificar numero Elementos a cargar en la lista
        
        limpiarListasSiguienteServida();
        limpiarHBoxVBox();
        Lbl_ClientesPerdidos.setText("Perdidos: "+clientesPerdidos);
        cargarCategoriasProductos();
        cargarHBoxCategoria1(listaProductosCategoria1);
        cargarHBoxCategoria2(listaProductosCategoria2);        
        cargarClientes();
    }        
    
    private void limpiarListasSiguienteServida()
    {
        listaClientes.clear();
        listaProductosCategoria1.clear();
        listaProductosCategoria2.clear();
        listaProductosClientes.clear();
        listaProductosCocinando.clear();
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
