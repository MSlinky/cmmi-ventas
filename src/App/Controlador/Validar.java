package App.Controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {
    public Validar() {
    }
    
    public Boolean checkEmail(String email){
        Pattern p = Pattern.compile("^([\\da-zA-Z_\\.-]+)@([\\da-zA-Z\\.-]+)\\.([a-zA-Z\\.]{2,6})+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    
    public Boolean checkPassword(String password){
        Pattern p = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])([\\da-zA-Z_\\.-]+){6,20}$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public Boolean checkText(String password){
        Pattern p = Pattern.compile("^([a-zA-Z0-9áéíóúñ\\(\\)\\-\\/\\.\\,\\-\\&\\r\\n: ]{1,10000})$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public Boolean checkName(String password){
        Pattern p = Pattern.compile("^([a-zA-Z0-9áéíóúñ\\(\\)\\-\\/ ]{1,60})$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public Boolean checkCant(String password){
        Pattern p = Pattern.compile("^[0-9]+([.][0-9]{0,2})?$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public Boolean checkNum(String password){
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(password);
        return m.matches();
    }
}
