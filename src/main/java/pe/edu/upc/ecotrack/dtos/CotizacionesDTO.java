package pe.edu.upc.ecotrack.dtos;

import pe.edu.upc.ecotrack.entities.Usuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CotizacionesDTO {
    private int idCotizaciones;
    private double precio;
    private LocalDate fecha_cotizacion;
    private Usuarios usuario;

    public int getIdCotizaciones() {
        return idCotizaciones;
    }

    public void setIdCotizaciones(int idCotizaciones) {
        this.idCotizaciones = idCotizaciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFecha_cotizacion() {
        return fecha_cotizacion;
    }

    public void setFecha_cotizacion(LocalDate fecha_cotizacion) {
        this.fecha_cotizacion = fecha_cotizacion;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
