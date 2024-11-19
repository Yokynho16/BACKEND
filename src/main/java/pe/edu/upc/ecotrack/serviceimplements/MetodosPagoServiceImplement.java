package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.MetodosPago;
import pe.edu.upc.ecotrack.repositories.IMetodosPagoRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.IMetodosPagoService;

import java.util.List;

@Service
public class MetodosPagoServiceImplement implements IMetodosPagoService {
    @Autowired
    private IMetodosPagoRepository mR;

    @Override
    public List<MetodosPago> list() {
        return mR.findAll();
    }

    @Override
    public void insert(MetodosPago m) {
        mR.save(m);
    }

    @Override
    public MetodosPago listId(int id) {
        return mR.findById(id).orElse(new MetodosPago());
    }

    @Override
    public void update(MetodosPago m) {
        mR.save(m);
    }

    @Override
    public void delete(int id) {
        mR.deleteById(id);
    }

    @Override
    public List<MetodosPago> listarMetodosPagoUsername(String username) {
        return mR.listarMetodosPagoUsername(username);
    }

    @Override
    public List<String[]> SumaMontoPorMetodoPagoDTO() {
        return mR.SumaMontoPorMetodoPagoDTO();
    }
}
