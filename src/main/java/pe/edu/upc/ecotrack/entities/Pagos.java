package pe.edu.upc.ecotrack.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPagos;
    @Column(name = "monto",nullable = false)
    private double monto;
    @Column(name = "fecha_pago",nullable = false)
    private LocalDate fecha_pago;
    @Column(name="estado",nullable = false,length = 20)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "idCotizaciones")
    private Cotizaciones cotizacion;

    public Pagos() {
    }

    public Pagos(int idPagos, double monto, LocalDate fecha_pago, String estado, Cotizaciones cotizacion) {
        this.idPagos = idPagos;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.estado = estado;
        this.cotizacion = cotizacion;
    }

    public int getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(int idPagos) {
        this.idPagos = idPagos;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
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
