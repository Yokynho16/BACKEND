package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.entities.Quejas;
import pe.edu.upc.ecotrack.repositories.IQuejasRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.IQuejasService;

import java.util.List;
@Service
public class QuejasServiceImplement implements IQuejasService {
    @Autowired
    private IQuejasRepository qR;
    @Override
    public List<Quejas> list() {
        return qR.findAll();
    }

    @Override
    public void insert(Quejas quejas) {
        qR.save(quejas);
    }

    @Override
    public Quejas listId(int id) {
        return qR.findById(id).orElse(new Quejas());
    }

    @Override
    public void update(Quejas quejas) {
        qR.save(quejas);
    }

    @Override
    public void delete(int id) {
        qR.deleteById(id);
    }

    @Override
    public List<Quejas> listarQuejasUsername(String username) {
        return qR.listarQuejasUsername(username);
    }

    @Override
    public List<String[]> QuejaPorTipoDTO() {
        return qR.QuejaPorTipoDTO();
    }
}
