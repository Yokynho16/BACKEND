package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Rastreos;
import pe.edu.upc.ecotrack.repositories.IRastreosRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.IRastreosService;

import java.util.List;
@Service
public class RastreosServiceImplement implements IRastreosService {
    @Autowired
    private IRastreosRepository rR;

    @Override
    public List<Rastreos> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Rastreos ra) {
        rR.save(ra);
    }

    @Override
    public Rastreos listId(int id) {
        return rR.findById(id).orElse(new Rastreos());
    }

    @Override
    public void update(Rastreos r) {
        rR.save(r);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public List<Rastreos> listarRastreosUsername(String username) {
        return rR.listarRastreosUsername(username);
    }

}
