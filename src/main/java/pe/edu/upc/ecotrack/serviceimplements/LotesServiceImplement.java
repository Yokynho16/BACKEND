package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.repositories.ILotesRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.ILotesService;

import java.util.List;
@Service
public class LotesServiceImplement implements ILotesService {
    @Autowired
    private ILotesRepository lR;
    @Override
    public List<Lotes> list() {
        return lR.findAll();
    }

    @Override
    public void insert(Lotes lote) {
        lR.save(lote);
    }

    @Override
    public Lotes listId(int id) {
        return lR.findById(id).orElse(new Lotes());
    }

    @Override
    public void update(Lotes lote) {
        lR.save(lote);
    }

    @Override
    public void delete(int id) {
        lR.deleteById(id);
    }

    @Override
    public List<String[]> buscarLotesPorNombreAgricultor(String nombre) {
        return lR.buscarLotesPorNombreAgricultor(nombre);
    }



    @Override
    public List<Lotes> listarLotesprueba(String username) {
        return lR.listarLotesprueba(username);
    }

    @Override
    public List<String[]> LotesPorUsuarioDTO() {
        return lR.LotesPorUsuarioDTO();
    }


}
