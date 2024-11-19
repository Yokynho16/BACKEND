package pe.edu.upc.ecotrack.dtos;



import pe.edu.upc.ecotrack.entities.Vehiculos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RastreosDTO {

    private int idRastreos;
    private LocalDate fecha_salida;
    private LocalDate fecha_llegada;
    private String estado;
    private String ubicacion_actual;
    private Vehiculos ve;


    public int getIdRastreos() {
        return idRastreos;
    }

    public void setIdRastreos(int idRastreos) {
        this.idRastreos = idRastreos;
    }

    public LocalDate getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public LocalDate getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(LocalDate fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion_actual() {
        return ubicacion_actual;
    }

    public void setUbicacion_actual(String ubicacion_actual) {
        this.ubicacion_actual = ubicacion_actual;
    }

    public Vehiculos getVe() {
        return ve;
    }

    public void setVe(Vehiculos ve) {
        this.ve = ve;
    }
}
