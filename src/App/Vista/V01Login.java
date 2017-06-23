

package App.Vista;
import App.Controlador.Validar;
import App.Controlador.PeticionPost;


import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
public class V01Login implements Initializable {
    
    @FXML
    private JFXTextField txt_usuario;
    public JFXPasswordField txt_pass;
    public ImageView cross_correo, cross_pass;
    
    @FXML
    private boolean IniciarSesion(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, IOException, ParseException {
        /*Socket cli =  new Socket("127.0.0.1", 9090);
        DataOutputStream flujo = new DataOutputStream(cli.getOutputStream());
        flujo.writeUTF("hola mundo");
        cli.close();*/
        
        /*Validar objValidar = new Validar();
        //Validar datos para iniciar sesion
        if(!objValidar.checkEmail(txt_usuario.getText())){
            cross_correo.setVisible(true);
            return false;
        }
        if(!objValidar.checkPassword(txt_pass.getText())){
            cross_correo.setVisible(false);
            cross_pass.setVisible(true);
            return false;
        }
        cross_correo.setVisible(false);
        cross_pass.setVisible(false);
        
        //Realizar peticion al servidor, validar datos en la base de datos.
        PeticionPost post = new PeticionPost ();
        post.add("accion", "login");
        post.add("user", txt_usuario.getText());
        post.add("password", txt_pass.getText());
        
        //Convertir cadena que responde en servidor en un objeto
        String respuesta = post.getRespueta();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(respuesta);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray user = (JSONArray) jsonObject.get("login");
        Iterator<JSONObject> iterator = user.iterator();
        
        //Segunda ventana
        if(user.size() == 1){*/
            System.out.println("Iniciar");
            Parent inicio = FXMLLoader.load(getClass().getResource("02Inicio.fxml"));
            Scene inicio_scene =  new Scene(inicio);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(inicio_scene);
            app_stage.setTitle("Inicio");
            app_stage.setX(10);
            app_stage.setY(10);
            app_stage.show();
            
            /*FXMLLoader loader = new FXMLLoader(getClass().getResource("02Inicio.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene((Pane) loader.load()));
            V02Inicio controller = loader.<V02Inicio>getController();
            stage.show();*/
            
            app_stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                @Override 
                public void handle(WindowEvent event) {
                    System.exit(0);
                }
            });
        /*}else{
            System.out.println("No iniciar");
        }*/
        return true;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
