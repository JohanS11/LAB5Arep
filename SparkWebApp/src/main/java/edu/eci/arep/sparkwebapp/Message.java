package edu.eci.arep.sparkwebapp;

public class Message {

    private String mensaje;
    private String fecha;

    public Message(String mensaje, String fecha) {
        this.mensaje = mensaje;
        this.fecha = fecha;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String toString(){
        return "date " + getFecha() + "message " + getMensaje();
    }
}
