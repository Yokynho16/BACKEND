package pe.edu.upc.ecotrack.serviceinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecotrack.entities.Rastreos;
import pe.edu.upc.ecotrack.entities.Rutas;

import java.util.List;

public interface IRutasService {
    public List<Rutas> list();
    public  void insert(Rutas ru);
    public Rutas listId(int id);

    public void update(Rutas rutas);

    public void delete(int id);
    public List<Rutas> listarRutasUsername(@Param("username") String username);

}
