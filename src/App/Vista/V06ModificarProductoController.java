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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class V06ModificarProductoController implements Initializable {
    public TableView<Producto> table;
    public TableColumn colum_nombre;
    public TableColumn colum_boton_eliminar;
    public TableColumn colum_boton_modificar;
    public TableColumn colum_inventario;
    public TableColumn colum_precio;
    
    static ObservableList<Producto> data;
    
    private void eliminarProducto(String id){
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).id.equals(id)){
                PeticionPost post = null;
                
                try {
                    post = new PeticionPost ();
                    post.add("accion", "EliminarProducto");
                    post.add("id", id);
                    String respuesta = post.getRespueta();

                    System.out.println(respuesta);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
                data.remove(i);
            }
        }
    }
    
    private void modificarProducto(String id) throws IOException, ParseException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("05RegistrarProducto.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane) loader.load()));
        V05RegistrarProducto controller = loader.<V05RegistrarProducto>getController();
        controller.initData(id);
        stage.show();
    }
    
    public void actializar(){
        data.clear();
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
                
                Button eliminar = new Button("Eliminar");
                eliminar.setId( String.valueOf((String)item.get("id_producto")) );
                
                Button modificar = new Button("Modificar");
                modificar.setId( String.valueOf((String)item.get("id_producto")) );
                
                eliminar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        eliminarProducto(((Control)e.getSource()).getId());
                    }
                });
                
                modificar.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        try {
                            modificarProducto(((Control)e.getSource()).getId());
                        } catch (IOException ex) {
                            Logger.getLogger(V06ModificarProductoController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(V06ModificarProductoController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                Producto p = new Producto(  (String)item.get("id_producto"),
                                            (String)item.get("nombre"),
                                            new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.parseInt((String) item.get("cantidadInventario")), 1)),
                                            (String) item.get("cantidadInventario"),
                                            Double.parseDouble((String) item.get("precio")), 
                                            null);
                
                p.setEliminar(eliminar);
                p.setModificar(modificar);
                
                data.add(p);
                i++;
            }
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colum_nombre.prefWidthProperty().bind(table.widthProperty().multiply(0.58));
        
        colum_inventario.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_precio.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        
        colum_nombre.setCellValueFactory(new PropertyValueFactory("producto"));
        colum_boton_eliminar.setCellValueFactory(new PropertyValueFactory("eliminar"));
        colum_boton_modificar.setCellValueFactory(new PropertyValueFactory("modificar"));
        colum_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        colum_inventario.setCellValueFactory(new PropertyValueFactory("inventario"));
        
        data = FXCollections.observableArrayList();
        table.setItems(data);
        actializar();
    }    
}
