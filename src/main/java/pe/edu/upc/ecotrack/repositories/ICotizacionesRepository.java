package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Cotizaciones;
import pe.edu.upc.ecotrack.entities.Cultivos;

import java.util.List;

@Repository
public interface ICotizacionesRepository extends JpaRepository<Cotizaciones,Integer> {
    @Query(value = "select\n" +
            "\tco.id_cotizaciones,\n" +
            "\tco.precio,\n" +
            "\tco.fecha_cotizacion,\n" +
            "\tco.id_usuarios\n" +
            "from\n" +
            "\tcotizaciones co\n" +
            "inner join\n" +
            "\tusuarios u on co.id_usuarios = u.id_usuarios\n" +
            "where\n" +
            "\tu.username = :username ", nativeQuery = true)
    public List<Cotizaciones> listarCotizacionesUsername(@Param("username")String username);

    @Query(value = "SELECT\n" +
            "\tu.nombre,\n" +
            "\tco.precio\n" +
            "from\n" +
            "\tcotizaciones co\n" +
            "inner join usuarios u on co.id_usuarios = u.id_usuarios ", nativeQuery = true)
    public List<String[]> TotalCotizacionesPorUsuarioDTO();

}