package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Rastreos;
import pe.edu.upc.ecotrack.entities.Vehiculos;

import java.util.List;

@Repository
public interface IRastreosRepository extends JpaRepository<Rastreos,Integer> {
    @Query(value="select\n" +
            "\tr.id_rastreos,\n" +
            "\tr.fecha_salida,\n" +
            "\tr.fecha_llegada,\n" +
            "\tr.estado,\n" +
            "\tr.ubicacion_actual,\n" +
            "\tr.id_vehiculos\n" +
            "from\n" +
            "\trastreos r\n" +
            "inner join\n" +
            "\tvehiculos v on r.id_vehiculos=v.id_vehiculos\n" +
            "inner join\n" +
            "\tusuarios u on v.id_usuarios = u.id_usuarios\n" +
            "where\n" +
            "\tu.username= :username", nativeQuery = true)
    public List<Rastreos> listarRastreosUsername(@Param("username") String username);

}
