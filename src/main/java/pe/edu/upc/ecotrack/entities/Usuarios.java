package pe.edu.upc.ecotrack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarios;
    @Column(name = "dni", nullable = false)
    private int dni;
    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;
    @Column(length = 30, unique = true)
    private String username;
    @Column(name = "password", nullable = false, length =300)
    private String password;
    private Boolean enabled;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fecha_registro;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuarios")
    private List<Roles> roles;


    public Usuarios() {
    }

    public Usuarios(int idUsuarios, int dni, String nombre, String username, String password, Boolean enabled, String direccion, String telefono, LocalDate fecha_registro, List<Roles> roles) {
        this.idUsuarios = idUsuarios;
        this.dni = dni;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_registro = fecha_registro;
        this.roles = roles;
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}
