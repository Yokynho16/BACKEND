package pe.edu.upc.ecotrack.serviceinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecotrack.entities.Usuarios;

import java.util.List;

public interface IUsuariosService {
    public List<Usuarios>list();
    public  void insert(Usuarios u);
    public Usuarios listId(int id);
    public void update(Usuarios u);
    public void  delete(int id);
    public List<String[]> reporteAgricultorVerPagos(@Param("nombre") String nombre);
    List<String[]> quejasporUsuarios();


    public int obtenerId(@Param("username") String username);
}
