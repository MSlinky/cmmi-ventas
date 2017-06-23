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

public class V04RegistrarCliente implements Initializable {
    
    @FXML
    private JFXTextField txt_nombre, txt_apellido, txt_calle, txt_numExt, txt_numInt, txt_estado, txt_municipio, txt_colonia, txt_CP, txt_email, txt_RFC;
    
    public String accion = "RegCliente";
    private String id_cli = null;
    V07ModificarClienteController ModCliente;
    
    void initData(String id) throws ParseException {
        id_cli = id;
        PeticionPost post = null;
        try {
            post = new PeticionPost ();
            post.add("accion", "OnlyCliente");
            post.add("id", id_cli);
            String respuesta = post.getRespueta();
            
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(respuesta);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray productos = (JSONArray) jsonObject.get("OnlyCliente");
            Iterator<JSONObject> iterator = productos.iterator();
            
            while(iterator.hasNext()){
                JSONObject item = iterator.next();

                txt_nombre.setText((String)item.get("nombre"));
                txt_apellido.setText((String)item.get("apellido"));
                txt_calle.setText((String)item.get("calle"));
                txt_numExt.setText((String)item.get("num_ext"));
                txt_numInt.setText((String)item.get("num_int"));
                txt_estado.setText((String)item.get("estado"));
                txt_municipio.setText((String)item.get("municipio"));
                txt_colonia.setText((String)item.get("colonia"));
                txt_CP.setText((String)item.get("codigo_postal"));
                txt_email.setText((String)item.get("correo_electronico"));
                txt_RFC.setText((String)item.get("rfc"));
                
                accion = "ModCliente";
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
    private boolean RegistrarCliente(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
        
        Validar objValidar = new Validar();
        boolean aceptar = true;
        
        txt_nombre.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_apellido.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_calle.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_numExt.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_estado.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_municipio.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_colonia.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_CP.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        txt_RFC.setStyle("-jfx-unfocus-color: #4d4d4d; -jfx-focus-color: #4059a9; -fx-text-fill: white");
        
        if(!objValidar.checkText(txt_nombre.getText())){
            txt_nombre.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_apellido.getText())){
            txt_apellido.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_calle.getText())){
            txt_calle.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_numExt.getText())){
            txt_numExt.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_municipio.getText())){
            txt_municipio.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_colonia.getText())){
            txt_colonia.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_CP.getText())){
            txt_CP.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_estado.getText())){
            txt_estado.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }else if(!objValidar.checkText(txt_RFC.getText())){
            txt_RFC.setStyle("-jfx-unfocus-color: red; -jfx-focus-color: red; -fx-text-fill: white");
            aceptar = false;
        }
        
        if(aceptar == false){
            return aceptar;
        }
        
        PeticionPost post = new PeticionPost ();
        
        if(accion == "ModCliente"){
            post.add("id", id_cli);
        }
        
        //Realizar peticion al servidor, validar datos en la base de datos.
        
        post.add("accion", accion);
        post.add("nombre", txt_nombre.getText());        
        post.add("apellido", txt_apellido.getText());
        post.add("calle", txt_calle.getText());
        post.add("numExt", txt_numExt.getText());
        post.add("numInt", txt_numInt.getText());
        post.add("estado", txt_estado.getText());
        post.add("municipio", txt_municipio.getText());
        post.add("colonia", txt_colonia.getText());
        post.add("CP", txt_CP.getText());
        post.add("email", txt_email.getText());
        post.add("RFC", txt_RFC.getText());
        
        //Convertir cadena que responde en servidor en un objeto
        String respuesta = post.getRespueta();
        System.out.println(respuesta);
        if(respuesta.equals("1")){
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            
            ModCliente = new V07ModificarClienteController();
            ModCliente.actializar();
        }else{
            System.out.println("No guardar");
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
