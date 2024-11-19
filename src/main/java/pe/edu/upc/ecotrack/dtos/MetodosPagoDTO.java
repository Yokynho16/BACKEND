package pe.edu.upc.ecotrack.dtos;

import pe.edu.upc.ecotrack.entities.Pagos;
import pe.edu.upc.ecotrack.entities.Usuarios;

import java.time.LocalDate;

public class MetodosPagoDTO {
    private int idMetodosPago;
    private String nombre;
    private String descripcion;
    private Pagos pagos;
    private Usuarios usuario;

    public int getIdMetodosPago() {
        return idMetodosPago;
    }

    public void setIdMetodosPago(int idMetodosPago) {
        this.idMetodosPago = idMetodosPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
