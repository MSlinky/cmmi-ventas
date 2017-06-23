package App.Controlador;

import javafx.scene.control.Button;

public class Cliente {
    
    private String id;
    private String nombre;
    private String apellido;
    private String estado;
    private String municipio;
    private String colonia;
    private String calle;
    private String num_int;
    private String num_ext;
    private String codigo_postal;
    private String rfc;
    private String correo_electronico;
    
    private Button modificar = null;
    private Button eliminar = null;

    public Cliente() {
    }

    public void setId(String id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setEstado(String estado) {this.estado = estado;}
    public void setMunicipio(String municipio) {this.municipio = municipio;}
    public void setColonia(String colonia) {this.colonia = colonia;}
    public void setCalle(String calle) {this.calle = calle;}
    public void setNum_int(String num_int) {this.num_int = num_int;}
    public void setNum_ext(String num_ext) {this.num_ext = num_ext;}
    public void setCodigo_postal(String codigo_postal) {this.codigo_postal = codigo_postal;}
    public void setRfc(String rfc) {this.rfc = rfc;}
    public void setCorreo_electronico(String correo_electronico) {this.correo_electronico = correo_electronico;}

    public void setModificar(Button modificar) {this.modificar = modificar;}
    public void setEliminar(Button eliminar) {this.eliminar = eliminar;}
    
    public String getId() {return id;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getEstado() {return estado;}
    public String getMunicipio() {return municipio;}
    public String getColonia() {return colonia;}
    public String getCalle() {return calle;}
    public String getNum_int() {return num_int;}
    public String getNum_ext() {return num_ext;}
    public String getCodigo_postal() {return codigo_postal;}
    public String getRfc() {return rfc;}
    public String getCorreo_electronico() {return correo_electronico;}
    public Button getModificar() {return modificar;}
    public Button getEliminar() {return eliminar;}
    
}
