package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Roles;

import java.util.List;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Integer> {

    @Query(value = "SELECT r.tipo, COUNT(u.id_usuarios) AS cantidad\n" +
            "FROM roles r\n" +
            "INNER JOIN usuarios u ON r.id_usuarios = u.id_usuarios\n" +
            "GROUP BY r.tipo\n" +
            "ORDER BY cantidad DESC ", nativeQuery = true)
    public List<String[]>UsuariosPorRolDTO();
}
