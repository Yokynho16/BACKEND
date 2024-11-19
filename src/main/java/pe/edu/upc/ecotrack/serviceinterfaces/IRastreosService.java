package pe.edu.upc.ecotrack.serviceinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecotrack.entities.Rastreos;

import java.util.List;

public interface IRastreosService {
    public List<Rastreos> list();
    public  void insert(Rastreos ra);
    public Rastreos listId(int id);
    public void update(Rastreos r);
    public void  delete(int id);
    public List<Rastreos> listarRastreosUsername(@Param("username") String username);

}
