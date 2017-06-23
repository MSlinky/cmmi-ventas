package App.Vista;

import App.Controlador.Cliente;
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

public class V07ModificarClienteController implements Initializable {
    
    public TableView<Cliente> table;
    public TableColumn colum_nombre;
    public TableColumn colum_apellido;
    public TableColumn colum_boton_eliminar;
    public TableColumn colum_boton_modifcar;
    public TableColumn colum_estado;
    public TableColumn colum_municipio;
    public TableColumn colum_colonia;
    public TableColumn colum_calle;
    public TableColumn colum_num_int;
    public TableColumn colum_num_ext;
    public TableColumn colum_cp;
    public TableColumn colum_rfc;
    public TableColumn colum_correo;
    
    static ObservableList<Cliente> data;
    
    private void eliminarProducto(String id) {
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getId().equals(id)){
                PeticionPost post = null;
                
                try {
                    post = new PeticionPost ();
                    post.add("accion", "EliminarCliente");
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
    
    private void modificarProducto(String id) throws IOException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("04RegistrarCliente.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane) loader.load()));
        V04RegistrarCliente controller = loader.<V04RegistrarCliente>getController();
        controller.initData(id);
        stage.show();
    }
    
    public void actializar(){
        data.clear();
        PeticionPost post = null;
        try {
            post = new PeticionPost ();
            post.add("accion", "AllClientes");
            String respuesta = post.getRespueta();
             
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respuesta);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray productos = (JSONArray) jsonObject.get("AllClientes");
            Iterator<JSONObject> iterator = productos.iterator();
            
            int i = 0;
            while(iterator.hasNext()){
                JSONObject item = iterator.next();
                
                Button eliminar = new Button("Eliminar");
                eliminar.setId( String.valueOf((String)item.get("id_cliente")) );
                
                Button modificar = new Button("Modificar");
                modificar.setId( String.valueOf((String)item.get("id_cliente")) );
                
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
                            Logger.getLogger(V07ModificarClienteController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(V07ModificarClienteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                Cliente p = new Cliente();
                p.setId((String)item.get("id_cliente"));
                p.setNombre((String)item.get("nombre"));
                p.setApellido((String)item.get("apellido"));
                p.setEstado((String)item.get("estado"));
                p.setMunicipio((String)item.get("municipio"));
                p.setColonia((String)item.get("colonia"));
                p.setCalle((String)item.get("calle"));
                p.setNum_int((String)item.get("num_int"));
                p.setNum_ext((String)item.get("num_ext"));
                p.setCodigo_postal((String)item.get("codigo_postal"));
                p.setRfc((String)item.get("rfc"));
                p.setCorreo_electronico((String)item.get("correo_electronico"));
                
                p.setModificar(modificar);
                p.setEliminar(eliminar);
                
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
        colum_nombre.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        
        colum_apellido.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_estado.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_municipio.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_colonia.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_calle.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_num_int.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_num_ext.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_cp.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_rfc.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        colum_correo.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        
        colum_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colum_boton_eliminar.setCellValueFactory(new PropertyValueFactory("eliminar"));
        colum_boton_modifcar.setCellValueFactory(new PropertyValueFactory("modificar"));
        colum_apellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        colum_estado.setCellValueFactory(new PropertyValueFactory("estado"));
        colum_municipio.setCellValueFactory(new PropertyValueFactory("municipio"));
        colum_colonia.setCellValueFactory(new PropertyValueFactory("colonia"));
        colum_calle.setCellValueFactory(new PropertyValueFactory("calle"));
        colum_num_int.setCellValueFactory(new PropertyValueFactory("num_int"));
        colum_num_ext.setCellValueFactory(new PropertyValueFactory("num_ext"));
        colum_cp.setCellValueFactory(new PropertyValueFactory("codigo_postal"));
        colum_rfc.setCellValueFactory(new PropertyValueFactory("rfc"));
        colum_correo.setCellValueFactory(new PropertyValueFactory("correo_electronico"));
        
        data = FXCollections.observableArrayList();
        table.setItems(data);
        
        actializar();
    }    
}
