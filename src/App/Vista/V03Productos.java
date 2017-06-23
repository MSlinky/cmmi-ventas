
package App.Vista;

import App.Controlador.PeticionPost;
import App.Controlador.Producto;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class V03Productos implements Initializable {
    
    public TableView<Producto> table;
    public TableColumn colum_nombre;
    public TableColumn colum_boton;
    public TableColumn colum_cantidad;
    public TableColumn colum_precio;
    public TableColumn colum_inventario;
    
    static ObservableList<Producto> data;
    
    V02Inicio carrito;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carrito = new V02Inicio();
        
        colum_nombre.prefWidthProperty().bind(table.widthProperty().multiply(0.59));
        colum_boton.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_cantidad.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_precio.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_inventario.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        
        colum_nombre.setCellValueFactory(new PropertyValueFactory("producto"));
        colum_boton.setCellValueFactory(new PropertyValueFactory("button"));
        colum_cantidad.setCellValueFactory(new PropertyValueFactory("cantidad"));
        colum_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        colum_inventario.setCellValueFactory(new PropertyValueFactory("inventario"));
        
        data = FXCollections.observableArrayList();
        
        PeticionPost post = null;
        try {
            post = new PeticionPost ();
            post.add("accion", "AllProductos");
            String respuesta = post.getRespueta();
             
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respuesta);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray productos = (JSONArray) jsonObject.get("AllProductos");
            Iterator<JSONObject> iterator = productos.iterator();
            
            int i = 0;
            while(iterator.hasNext()){
                JSONObject item = iterator.next();                
                Button boton1 = new Button("Agregar");                              
                boton1.setId(String.valueOf(i));
                boton1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {                        
                        carrito.cargarCarro(data.get(Integer.parseInt(((Control)e.getSource()).getId())));
                    }
                });
                i++;
                
                data.add(new Producto(  (String)    item.get("id_producto"),
                                        (String)    item.get("nombre"),
                                            new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.parseInt((String) item.get("cantidadInventario")), 1)),
                                            (String) item.get("cantidadInventario"),
                                            Double.parseDouble((String) item.get("precio")), 
                                            boton1));
            
            }
            table.setItems(data);
        } catch (MalformedURLException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
