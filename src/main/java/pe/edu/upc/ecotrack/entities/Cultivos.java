package pe.edu.upc.ecotrack.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Cultivos")
public class Cultivos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCultivos;
    @Column(name="nombre",nullable = false)
    private String nombre;
    @Column(name="tipo",nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name="idLotes")
    private Lotes lotes;

    public Cultivos() {
    }

    public Cultivos(int idCultivos, String tipo, String nombre, Lotes lotes) {
        this.idCultivos = idCultivos;
        this.tipo = tipo;
        this.nombre = nombre;
        this.lotes = lotes;
    }

    public int getIdCultivos() {
        return idCultivos;
    }

    public void setIdCultivos(int idCultivos) {
        this.idCultivos = idCultivos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lotes getLotes() {
        return lotes;
    }

    public void setLotes(Lotes lotes) {
        this.lotes = lotes;
    }
}
