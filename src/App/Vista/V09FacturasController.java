package App.Vista;

import App.Controlador.PeticionPost;
import App.Controlador.Producto;
import static App.Vista.V02Inicio.data;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class V09FacturasController implements Initializable {
    
    public JFXTextField cliNombre,cliApellido, cliEstado, cliMunicipio, cliColonia, 
                cliCalle, cliExt, cliInt, cliPostal, cliRFC, cliCorreo;
    
    @FXML public TableView<Producto> table;
    @FXML public TableColumn colum_carrito;
    @FXML public TableColumn colum_cantidad;
    @FXML public TableColumn colum_precio;
    
    @FXML public Label lbl_total;
    
    public String accion = "verFacturas";
    
    Button buscarCliente;
    
    String id_fact; 

    void initData(String id) throws ParseException {
        id_fact = id;
        PeticionPost post = null;
        try {
            post = new PeticionPost ();
            post.add("accion", "OnlyFactura");
            post.add("id", id_fact);
            String respuesta = post.getRespueta();
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respuesta);         
            JSONArray jsonObject = (JSONArray) obj;
            
            Object objS = parser.parse(jsonObject.get(0).toString());  
            JSONObject Object = (JSONObject) objS;
            
            JSONArray productos = (JSONArray) Object.get("OnlyFactura");
            Iterator<JSONObject> iterator = productos.iterator();
            
            while(iterator.hasNext()){
                JSONObject item = iterator.next();

                cliNombre.setText((String)item.get("nombre"));
                cliApellido.setText((String)item.get("apellido"));
                cliEstado.setText((String)item.get("estado"));
                cliMunicipio.setText((String)item.get("municipio"));
                cliColonia.setText((String)item.get("colonia"));
                cliCalle.setText((String)item.get("calle"));
                cliExt.setText((String)item.get("num_ext"));
                cliInt.setText((String)item.get("num_int"));
                cliPostal.setText((String)item.get("codigo_postal"));
                cliRFC.setText((String)item.get("rfc"));
                cliCorreo.setText((String)item.get("correo_electronico"));
                
            }
            
            Object objSs = parser.parse(jsonObject.get(1).toString());
            System.out.println(jsonObject.get(1).toString());
            JSONObject Objects = (JSONObject) objSs;
            
            JSONArray productoss = (JSONArray) Objects.get("OnlyFactura");
            Iterator<JSONObject> iterators = productoss.iterator();
            
            while(iterators.hasNext()){
                JSONObject item = iterators.next();
                
                Producto p1 = new Producto((String)item.get("id_articulo"), 
                                            (String)item.get("nombre"), 
                                            null, 
                                            "0", 
                                            12.20, 
                                            null);
                p1.setcantidad((String)item.get("cantidad"));
                System.out.println((String)item.get("cantidad"));
                data.add(p1);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colum_carrito.prefWidthProperty().bind(table.widthProperty().multiply(0.79));
        colum_cantidad.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_precio.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        
        colum_carrito.setCellValueFactory(new PropertyValueFactory("producto"));
        colum_cantidad.setCellValueFactory(new PropertyValueFactory("cantidadFactura"));
        colum_precio.setCellValueFactory(new PropertyValueFactory("precio"));
        
        data = FXCollections.observableArrayList();
        table.setItems(data);
    } 
}
