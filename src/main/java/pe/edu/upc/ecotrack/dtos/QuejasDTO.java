package pe.edu.upc.ecotrack.dtos;

import pe.edu.upc.ecotrack.entities.Usuarios;

import java.time.LocalDate;

public class QuejasDTO {
    private int idQuejas;
    private String titulo;
    private String descripcion;
    private LocalDate fecha_creacion;
    private String tipo;
    private String respuesta;
    private Usuarios usuario;

    public int getIdQuejas() {
        return idQuejas;
    }

    public void setIdQuejas(int idQuejas) {
        this.idQuejas = idQuejas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
