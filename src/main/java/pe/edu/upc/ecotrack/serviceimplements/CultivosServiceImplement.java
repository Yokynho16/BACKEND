package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Cultivos;
import pe.edu.upc.ecotrack.repositories.ICultivosRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.ICultivosService;

import java.util.List;
@Service
public class CultivosServiceImplement implements ICultivosService {
    @Autowired
    private ICultivosRepository cR;
    @Override
    public List<Cultivos> list() {
        return cR.findAll();
    }

    @Override
    public void insert(Cultivos cu) {
        cR.save(cu);
    }

    @Override
    public Cultivos listId(int id) {
        return cR.findById(id).orElse(new Cultivos());
    }

    @Override
    public void update(Cultivos cu) {
        cR.save(cu);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public List<String[]> buscarNombre(String nombre) {
        return cR.buscarNombre(nombre);
    }

    @Override
    public List<Cultivos> listarCultivosUsername(String username) {
        return cR.listarCultivosUsername(username);
    }

    @Override
    public List<String[]> CultivosPorTipo() {
        return cR.CultivosPorTipo();
    }
}
