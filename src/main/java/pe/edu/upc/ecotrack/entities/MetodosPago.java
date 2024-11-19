package pe.edu.upc.ecotrack.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "MetodosPago")
public class MetodosPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMetodosPago;
    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false,length = 254)
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "idPagos")
    private Pagos pagos;

    @ManyToOne
    @JoinColumn(name = "idUsuarios")
    private Usuarios usuario;

    public MetodosPago() {
    }

    public MetodosPago(int idMetodosPago, String nombre, String descripcion, Pagos pagos, Usuarios usuario) {
        this.idMetodosPago = idMetodosPago;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.pagos = pagos;
        this.usuario = usuario;
    }

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
