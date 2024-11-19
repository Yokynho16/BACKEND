package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Rutas;
import pe.edu.upc.ecotrack.entities.Vehiculos;

import java.util.List;

@Repository
public interface IRutasRepository extends JpaRepository<Rutas,Integer> {
    @Query(value = "select\n" +
            "\tr.id_rutas,\n" +
            "\tr.origen,\n" +
            "\tr.destino,\n" +
            "\tr.duracion_estimada,\n" +
            "\tr.id_rastreos\n" +
            "from\n" +
            "\trutas r\n" +
            "inner join\n" +
            "\trastreos ra on r.id_rastreos = ra.id_rastreos\n" +
            "inner join\n" +
            "\tvehiculos v on ra.id_vehiculos = v.id_vehiculos\n" +
            "inner join\n" +
            "\tusuarios u on v.id_usuarios = u.id_usuarios\n" +
            "where\n" +
            "\tu.username = :username ",nativeQuery = true)
    public List<Rutas> listarRutasUsername(@Param("username") String username);

}
