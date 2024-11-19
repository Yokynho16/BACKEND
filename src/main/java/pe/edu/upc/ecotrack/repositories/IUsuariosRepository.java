package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.ecotrack.entities.Usuarios;

import java.util.List;

@Repository
public interface IUsuariosRepository extends JpaRepository<Usuarios, Integer> {
    @Query(value="Select \n" +
            "\tu.nombre,\n" +
            "\tpa.id_pagos,\n" +
            "\tpa.monto,\n" +
            "\tpa.fecha_pago,\n" +
            "\tpa.estado\n" +
            "From\n" +
            "\tusuarios u\n" +
            "Inner Join\n" +
            "\tcotizaciones co on u.id_usuarios = co.id_usuarios\n" +
            "Inner Join \n" +
            "\tpagos pa on co.id_cotizaciones = pa.id_cotizaciones\n" +
            "Where\n" +
            "\tu.nombre = :nombre\n" +
            "Order by\n" +
            "\tpa.monto;",nativeQuery = true)
    public List<String[]> reporteAgricultorVerPagos(@Param("nombre") String nombre);

    @Query(value = "" +
            "SELECT u.nombre,COUNT(q.id_quejas)\n " +
            "FROM Usuarios u " +
            "INNER JOIN Quejas q ON u.id_usuarios = q.id_usuarios\n " +
            "GROUP BY u.nombre",
            nativeQuery = true)
    List<String[]> quejasporUsuarios();

    public Usuarios findOneByUsername(String username);

    //BUSCAR POR NOMBRE
    @Query("select count(u.username) from Usuarios u where u.username =:username")
    public int buscarUsername(@Param("username") String nombre);

    //INSERTAR ROLES
    @Transactional
    @Modifying
    @Query(value = "insert into roles (tipo, id_usuarios) VALUES (:rol, :id_usuarios)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("id_usuarios") Long id_usuarios);


    @Query(value = "select\n" +
            "\tid_usuarios\n" +
            "from\n" +
            "\tusuarios\n" +
            "where\n" +
            "\tusername = :username",nativeQuery = true)
    public int obtenerId(@Param("username") String username);
}
