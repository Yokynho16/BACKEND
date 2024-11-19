package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Pagos;
import pe.edu.upc.ecotrack.entities.Quejas;

import java.util.List;

@Repository
public interface IQuejasRepository extends JpaRepository<Quejas,Integer> {
    @Query(value="SELECT\n" +
            "\tq.id_quejas,\n" +
            "\tq.titulo,\n" +
            "\tq.descripcion,\n" +
            "\tq.tipo, \n" +
            "\tq.respuesta,\n" +
            "\tq.fecha_creacion,\n" +
            "\tq.id_usuarios\n" +
            "FROM\n" +
            "\tquejas q\n" +
            "inner join \n" +
            "\tusuarios u on q.id_usuarios = u.id_usuarios\n" +
            "where\n" +
            "\tu.username= :username",nativeQuery = true)
    public List<Quejas> listarQuejasUsername(@Param("username") String username);

    @Query(value = "SELECT q.tipo, COUNT(q.id_quejas) AS cantidad\n" +
            "FROM quejas q\n" +
            "GROUP BY q.tipo\n" +
            "ORDER BY cantidad DESC ",nativeQuery = true)
    public List<String[]>QuejaPorTipoDTO();
}
