package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Rutas;
import pe.edu.upc.ecotrack.repositories.IRastreosRepository;
import pe.edu.upc.ecotrack.repositories.IRutasRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.IRutasService;

import java.util.List;
@Service
public class RutasServiceImplement implements IRutasService {
    @Autowired
    private IRutasRepository rR;
    @Override
    public List<Rutas> list() {
        return rR.findAll();
    }

    @Override
    public void insert(Rutas ru) {
        rR.save(ru);
    }

    @Override
    public Rutas listId(int id) {
        return rR.findById(id).orElse(new Rutas());
    }

    @Override
    public void update(Rutas rutas) {
        rR.save(rutas);
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public List<Rutas> listarRutasUsername(String username) {
        return rR.listarRutasUsername(username);
    }
}
