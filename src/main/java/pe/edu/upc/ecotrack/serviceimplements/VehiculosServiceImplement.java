package pe.edu.upc.ecotrack.serviceimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.ecotrack.entities.Vehiculos;
import pe.edu.upc.ecotrack.repositories.IVehiculosRepository;
import pe.edu.upc.ecotrack.serviceinterfaces.IVehiculosService;

import java.time.LocalDate;
import java.util.List;
@Service
public class VehiculosServiceImplement implements IVehiculosService {
    @Autowired
    private IVehiculosRepository vR;
    @Override
    public List<Vehiculos> list() {
        return vR.findAll();
    }

    @Override
    public void insert(Vehiculos ve) {
        vR.save(ve);
    }

    @Override
    public Vehiculos listId(int id) {
        return vR.findById(id).orElse(new Vehiculos());
    }

    @Override
    public void update(Vehiculos ve) {
        vR.save(ve);
    }

    @Override
    public void delete(int id) {
        vR.deleteById(id);
    }

    @Override
    public List<String[]> reporteVehiculosPorFechasYCantidadRutas(String placa, LocalDate fecha_inicio, LocalDate fecha_fin) {
        return vR.reporteVehiculosPorFechasYCantidadRutas(placa, fecha_inicio, fecha_fin);
    }

    @Override
    public List<String[]> reporteVehiculosDisponible() {
        return vR.reporteVehiculosDisponible();
    }

    @Override
    public List<Vehiculos> listarVehiculosUsername(String username) {
        return vR.listarVehiculosUsername(username);
    }

    @Override
    public List<String[]> PlacaPorCantidadCargaDTO() {
        return vR.PlacaPorCantidadCargaDTO();
    }

    @Override
    public List<String[]> VehiculosPorTotalRutasDTO() {
        return vR.VehiculosPorTotalRutasDTO();
    }


}
