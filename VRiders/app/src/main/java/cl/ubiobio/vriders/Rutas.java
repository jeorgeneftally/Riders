package cl.ubiobio.vriders;

import java.util.Collection;

public class Rutas {
    private String nombre;
    private String HoraInicio;
    private String HoraReunion;
    private String Fecha;
    private String TipoRuta;
    private String Contrasena;
    private String id_Usuario;
    Collection<String> participantes;

    public String getDescripcion() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        nombre = descripcion;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraReunion() {
        return HoraReunion;
    }

    public void setHoraReunion(String horaReunion) {
        HoraReunion = horaReunion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getTipoRuta() {
        return TipoRuta;
    }

    public void setTipoRuta(String tipoRuta) {
        TipoRuta = tipoRuta;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public Rutas() {

    }

    public String getId_Usuario() {
        return id_Usuario;
    }

    public Collection<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Collection<String> participantes) {
        this.participantes = participantes;
    }

    public void setId_Usuario(String id_Usuario) {
        this.id_Usuario = id_Usuario;
    }


}


