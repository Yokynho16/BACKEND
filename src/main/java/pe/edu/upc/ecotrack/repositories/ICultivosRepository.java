package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Cultivos;

import java.util.List;

@Repository
public interface ICultivosRepository extends JpaRepository<Cultivos, Integer> {
    @Query(value = "select \n" +
            "\tid_cultivos,\n" +
            "\ttipo \n" +
            "from cultivos cu\n" +
            "where cu.nombre= :nombre",nativeQuery = true)
    public List<String[]> buscarNombre(@Param("nombre")String nombre);


    @Query(value="SELECT \n" +
            "\tcu.id_cultivos,\n" +
            "\tcu.nombre,\n" +
            "\tcu.tipo,\n" +
            "\tcu.id_lotes\n" +
            "FROM\n" +
            "cultivos cu\n" +
            "inner join lotes l on cu.id_lotes=l.id_lotes\n" +
            "inner join usuarios u on l.id_usuarios = u.id_usuarios\n" +
            "where u.username = :username ", nativeQuery = true)
    public List<Cultivos> listarCultivosUsername(@Param("username")String username);

    @Query(value = "select\n" +
            "\ttipo,\n" +
            "\tcount(id_cultivos) as cantidad\n" +
            "from\n" +
            "\tcultivos\n" +
            "group by tipo ", nativeQuery = true)
    public List<String[]> CultivosPorTipo();
}
