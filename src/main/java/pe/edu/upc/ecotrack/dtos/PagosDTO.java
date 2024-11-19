package pe.edu.upc.ecotrack.dtos;

import pe.edu.upc.ecotrack.entities.Cotizaciones;

import java.time.LocalDate;

public class PagosDTO {
    private int idPagos;
    private int monto;
    private LocalDate fecha_pago;
    private String estado;
    private Cotizaciones cotizacion;

    public int getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(int idPagos) {
        this.idPagos = idPagos;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cotizaciones getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizaciones cotizacion) {
        this.cotizacion = cotizacion;
    }

}
