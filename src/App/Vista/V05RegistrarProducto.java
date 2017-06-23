package App.Vista;

import App.Controlador.Validar;
import App.Controlador.PeticionPost;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import org.json.simple.parser.ParseException;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class V05RegistrarProducto implements Initializable {
    
    public String accion = "RegProducto";
    private String id_pro = null;
    V06ModificarProductoController ModProducto;
    
    void initData(String id) throws ParseException {
        id_pro = id;
        PeticionPost post = null;
        try {
            post = new PeticionPost ();
            post.add("accion", "OnlyProducto");
            post.add("id", id_pro);
            String respuesta = post.getRespueta();
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respuesta);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray productos = (JSONArray) jsonObject.get("OnlyProducto");
            Iterator<JSONObject> iterator = productos.iterator();
            
            while(iterator.hasNext()){
                JSONObject item = iterator.next();
                
                
                txt_nombrePro.setText(String.valueOf((String)item.get("nombre")));
                txt_descripcionPro.setText(String.valueOf((String)item.get("descripcion")));
                txt_codigoPro.setText(String.valueOf((String)item.get("codigo")));
                txt_precioPro.setText(String.valueOf((String)item.get("precio")));
                txt_cantidadPro.setText(String.valueOf((String)item.get("cantidadInventario")));
                
                accion = "ModProducto";
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(V03Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private JFXTextField txt_nombrePro, txt_descripcionPro, txt_codigoPro, txt_precioPro, txt_cantidadPro;

    @FXML
    private boolean RegistrarProducto(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
        
        Validar objValidar = new Validar();
        boolean aceptar = true;
        
        txt_nombrePro.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_descripcionPro.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_codigoPro.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_precioPro.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_cantidadPro.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        
        if(!objValidar.checkText(txt_nombrePro.getText())){
            txt_nombrePro.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_descripcionPro.getText())){
            txt_descripcionPro.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkCant(txt_precioPro.getText())){
            txt_precioPro.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkNum(txt_cantidadPro.getText())){
            txt_cantidadPro.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }
        
        if(aceptar == false){
            return aceptar;
        }
        
        //Realizar peticion al servidor, validar datos en la base de datos.
        PeticionPost post = new PeticionPost ();
        
        if(accion == "ModProducto"){
            post.add("id", id_pro);
        }
        post.add("accion", accion);
        post.add("nombrePro", txt_nombrePro.getText());        
        post.add("descripcionPro", txt_descripcionPro.getText());
        post.add("codigoPro", txt_codigoPro.getText());
        post.add("precioPro", txt_precioPro.getText());
        post.add("cantidadPro", txt_cantidadPro.getText());
        
        //Convertir cadena que responde en servidor en un objeto
        String respuesta = post.getRespueta();
        if(respuesta.equals("1")){
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            
            ModProducto = new V06ModificarProductoController();
            ModProducto.actializar();
        }else{
            System.out.println("No guardar");
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
