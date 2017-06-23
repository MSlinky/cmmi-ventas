package App.Vista;

import App.Controlador.Facturas;
import App.Controlador.PeticionPost;
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

public class V08HistorialVentasController implements Initializable {
    
    public TableView<Facturas> table;
    public TableColumn colum_nombre;
    public TableColumn colum_boton_detalles;
    public TableColumn colum_fecha;
    public TableColumn colum_cantidad;
    
    static ObservableList<Facturas> data;
    
    public void detallesFactura(String id) throws IOException, ParseException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("09Facturas.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane) loader.load()));
        V09FacturasController controller = loader.<V09FacturasController>getController();
        controller.initData(id);
        stage.show();
    }
     
    public void actializar(){
        data.clear();
        PeticionPost post = null;
        try {
            post = new PeticionPost ();
            post.add("accion", "AllFacturas");
            String respuesta = post.getRespueta();
             
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respuesta);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray productos = (JSONArray) jsonObject.get("AllFacturas");
            Iterator<JSONObject> iterator = productos.iterator();
            
            int i = 0;
            while(iterator.hasNext()){
                JSONObject item = iterator.next();
                
                Button detalles = new Button("Detalles");
                detalles.setId( String.valueOf((String)item.get("id_factura")) );
                
                detalles.setOnAction(new EventHandler<ActionEvent>() {
                    @Override 
                    public void handle(ActionEvent e) {
                        try {
                            detallesFactura(((Control)e.getSource()).getId());
                        } catch (IOException ex) {
                            Logger.getLogger(V08HistorialVentasController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(V08HistorialVentasController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                Facturas f = new Facturas();
                
                f.setId_factura( String.valueOf((String)item.get("id_factura")) + "_" +  String.valueOf((String)item.get("id_emisor")) + "_" +  String.valueOf((String)item.get("id_cliente")) );
                f.setDetalles(detalles);
                f.setFecha(String.valueOf((String)item.get("fecha")));
                f.setTotal( "$ "+String.valueOf((String)item.get("total")) );
                
                f.setDetalles(detalles);
                
                data.add(f);
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
        colum_nombre.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_boton_detalles.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        colum_fecha.prefWidthProperty().bind(table.widthProperty().multiply(0.20));
        colum_cantidad.prefWidthProperty().bind(table.widthProperty().multiply(0.48));
        
        colum_nombre.setCellValueFactory(new PropertyValueFactory("id_factura"));
        colum_boton_detalles.setCellValueFactory(new PropertyValueFactory("detalles"));
        colum_fecha.setCellValueFactory(new PropertyValueFactory("fecha"));
        colum_cantidad.setCellValueFactory(new PropertyValueFactory("total"));
        
        data = FXCollections.observableArrayList();
        table.setItems(data);
        
        actializar();
    }        
}
