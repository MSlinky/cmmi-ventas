package App.Controlador;

import javafx.scene.control.Button;

public class Facturas {
    private String id_factura;
    private String id_emisor;
    private String id_cliente;
    private String fecha;
    private String total;
    private String selloDigitalCFDI;
    private String SelloSAT;
    private String SerieCertificadoSAT;
    private String FechaHoraCertificacion;
    
    private Button detalles = null;

    public void setId_factura(String id_factura) {this.id_factura = id_factura;}
    public void setId_emisor(String id_emisor) {this.id_emisor = id_emisor;}
    public void setId_cliente(String id_cliente) {this.id_cliente = id_cliente;}
    public void setFecha(String fecha) {this.fecha = fecha;}
    public void setTotal(String total) {this.total = total;}
    public void setSelloDigitalCFDI(String selloDigitalCFDI) {this.selloDigitalCFDI = selloDigitalCFDI;}
    public void setSelloSAT(String SelloSAT) {this.SelloSAT = SelloSAT;}
    public void setSerieCertificadoSAT(String SerieCertificadoSAT) {this.SerieCertificadoSAT = SerieCertificadoSAT;}
    public void setFechaHoraCertificacion(String FechaHoraCertificacion) {this.FechaHoraCertificacion = FechaHoraCertificacion;}
    public void setDetalles(Button detalles) {this.detalles = detalles;}
    
    public String getId_factura() {                         return id_factura;}
    public String getId_emisor() {                          return id_emisor;}
    public String getId_cliente() {                         return id_cliente;}
    public String getFecha() {                              return fecha;}
    public String getTotal() {                              return total;}
    public String getSelloDigitalCFDI() {                   return selloDigitalCFDI;}
    public String getSelloSAT() {                           return SelloSAT;}
    public String getSerieCertificadoSAT() {                return SerieCertificadoSAT;}
    public String getFechaHoraCertificacion() {             return FechaHoraCertificacion;}
    public Button getDetalles() {                           return detalles;}
    
}
