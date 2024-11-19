package pe.edu.upc.ecotrack.serviceinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecotrack.entities.Vehiculos;

import java.time.LocalDate;
import java.util.List;

public interface IVehiculosService {
    public List<Vehiculos> list();
    public void insert(Vehiculos ve);
    public Vehiculos listId(int id);
    public void update(Vehiculos ve);
    public void delete(int id);
    public List<String[]> reporteVehiculosPorFechasYCantidadRutas(@Param("placa") String placa, @Param("fecha_inicio") LocalDate fecha_inicio, @Param("fecha_fin")LocalDate fecha_fin);
    public List<String[]> reporteVehiculosDisponible();
    public List<Vehiculos> listarVehiculosUsername(@Param("username") String username);
    public List<String[]>PlacaPorCantidadCargaDTO();
    public List<String[]>VehiculosPorTotalRutasDTO();

}
