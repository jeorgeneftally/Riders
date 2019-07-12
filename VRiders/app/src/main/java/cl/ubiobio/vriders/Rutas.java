package cl.ubiobio.vriders;

import java.util.Collection;

public class Rutas {
    private String nombre;
    private String HoraInicio;
    private String HoraReunion;
    private String Fecha;
    private String id_Usuario;
    private String latitude;
    private String longitud;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


    Collection<String> participantes;

    public Rutas(String nombre, String horaInicio, String horaReunion, String fecha, String id_Usuario, Collection<String> participantes) {
        this.nombre = nombre;
        HoraInicio = horaInicio;
        HoraReunion = horaReunion;
        Fecha = fecha;
        this.id_Usuario = id_Usuario;
        this.participantes = participantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String descripcion) {
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


