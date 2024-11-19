package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Cotizaciones;
import pe.edu.upc.ecotrack.repositories.ICotizacionesRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.ICotizacionesService;

import java.util.List;
@Service
public class CotizacionesServiceImplements implements ICotizacionesService {
    @Autowired
    private ICotizacionesRepository cR;
    @Override
    public List<Cotizaciones> list() {
        return cR.findAll();
    }

    @Override
    public void insert(Cotizaciones co) {
        cR.save(co);
    }

    @Override
    public Cotizaciones listId(int id) {
        return cR.findById(id).orElse(new Cotizaciones());
    }

    @Override
    public void update(Cotizaciones cotizaciones) {
        cR.save(cotizaciones);
    }

    @Override
    public void delete(int id) {
        cR.deleteById(id);
    }

    @Override
    public List<Cotizaciones> listarCotizacionesUsername(String username) {
        return cR.listarCotizacionesUsername(username);
    }

    @Override
    public List<String[]> TotalCotizacionesPorUsuarioDTO() {
        return cR.TotalCotizacionesPorUsuarioDTO();
    }
}
