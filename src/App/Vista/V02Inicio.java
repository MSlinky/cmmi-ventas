package App.Vista;

import App.Controlador.Producto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class V02Inicio implements Initializable {
    @FXML public TableView<Producto> table;
    @FXML public TableColumn colum_carrito;
    @FXML public TableColumn colum_boton;
    @FXML public TableColumn colum_cantidad;
    @FXML public TableColumn colum_precio;
    @FXML public TableColumn colum_inventario;
    
    @FXML public Label lbl_total;
    
    public  GridPane greadpane;
    public Label label = new Label("Address Book");
    
    static ObservableList<Producto> data;
    
    @FXML
    public boolean cargarCarro(Producto add){         
        int id = Integer.parseInt(add.getId());    
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).producto.equals(add.producto)){
                int newCant = (Integer)data.get(i).getCantidad().getValue() +  (Integer)add.getCantidad().getValue();
                data.get(i).getCantidad().getValueFactory().setValue(newCant);
                return true;
            }
        }        
        Button boton1 = new Button("Eliminar");        
        boton1.setId(String.valueOf((id)));        
        boton1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) { 
                        System.out.println("PResionado botón eliminar" + ((Control)e.getSource()).getId());
                        for (int i = 0; i < data.size(); i++){
                             if(data.get(i).id.equals(((Control)e.getSource()).getId()))
                             {
                                data.remove(i);   
                             }
                        }
                    }
                });        
        Producto p1 = new Producto(add.id, add.producto, new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, add.getInventario(), (Integer) add.getCantidad().getValue())), String.valueOf(add.getInventario()), add.precio, boton1);
        data.add(p1);    
        return true; 
    }
    
    public boolean abrirVentana(String nameFile, String name) throws IOException{
        Parent inicio = FXMLLoader.load(getClass().getResource(nameFile));
        Stage stage = new Stage();
        Scene scene = new Scene(inicio);

        stage.setScene(scene);
        stage.setTitle(name);
        stage.setX(50);
        stage.setY(50);
        stage.show();
        
        return true;
    }
    
    @FXML
    private boolean NewClient(ActionEvent event) throws IOException{
        abrirVentana("04RegistrarCliente.fxml", "Registrar cliente");
        return false;
    }
    
    @FXML
    private boolean NewProductos(ActionEvent event) throws IOException {
        abrirVentana("05RegistrarProducto.fxml", "Registrar producto");
        return true;
    }
    
    @FXML
    private boolean ModificarProducto(ActionEvent event) throws IOException {
        abrirVentana("06ModificarProducto.fxml", "Modificar producto");
        return true;
    }
    
    @FXML
    private boolean ModificarCliente(ActionEvent event) throws IOException {
        abrirVentana("07ModificarCliente.fxml", "Modificar cliente");
        return true;
    }
    
    @FXML
    private boolean SelectProductos(ActionEvent event) throws IOException {
        abrirVentana("03Productos.fxml", "Productos");
        return true;
    }
    
    @FXML
    private boolean HistorialVentas(ActionEvent event) throws IOException {
        abrirVentana("08HistorialVentas.fxml", "Historial de ventas");
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colum_carrito.prefWidthProperty().bind(table.widthProperty().multiply(0.59));
        colum_boton.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_cantidad.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_precio.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_inventario.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        
        colum_carrito.setCellValueFactory(new PropertyValueFactory("producto"));
        colum_boton.setCellValueFactory(new PropertyValueFactory("button"));
        colum_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        colum_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        colum_inventario.setCellValueFactory(new PropertyValueFactory("inventario"));
        
        data = FXCollections.observableArrayList();
        table.setItems(data);
    }    

    void initData(String hola) {
        lbl_total.setText("hlola");
    }
}
