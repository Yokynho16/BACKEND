package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Lotes;

import java.util.List;

@Repository
public interface ILotesRepository extends JpaRepository<Lotes,Integer> {
    @Query(value = "Select \n" +
            "\tu.nombre,\n" +
            "\tl.nombre as nombre_lote,\n" +
            "\tl.tipo_cultivo,\n" +
            "\tl.fecha_siembra,\n" +
            "\tl.estado,\n" +
            "\tl.cantidad\n" +
            "From\n" +
            "\tlotes l\n" +
            "Inner Join\n" +
            "\tusuarios u on l.id_usuarios = u.id_usuarios\n" +
            "Inner Join\n" +
            "\troles r on u.id_usuarios = r.id_usuarios \n" +
            "Where\n" +
            "\tr.tipo = 'Agricultor' and u.nombre = :nombre",nativeQuery = true)
    public List<String[]> buscarLotesPorNombreAgricultor(@Param("nombre") String nombre);


    @Query(value="SELECT \n" +
            "\tl.id_lotes,\n" +
            "\tl.cantidad,\n" +
            "\tl.fecha_siembra,\n" +
            "\tl.nombre,\n" +
            "\tl.tipo_cultivo,\n" +
            "\tl.estado,\n" +
            "\tl.id_usuarios,\n" +
            "\tl.id_controles\n" +
            "FROM lotes l\n" +
            "inner join usuarios u on l.id_usuarios=u.id_usuarios\n" +
            "where u.username = :username ", nativeQuery = true)
    public List<Lotes> listarLotesprueba(@Param("username") String username);

    @Query(value = "SELECT u.nombre, COUNT(l.id_lotes) AS cantidad_lotes\n" +
            "FROM usuarios u\n" +
            "INNER JOIN lotes l ON u.id_usuarios = l.id_usuarios\n" +
            "GROUP BY u.nombre\n" +
            "ORDER BY cantidad_lotes DESC ", nativeQuery = true)
    public List<String[]> LotesPorUsuarioDTO();

}
