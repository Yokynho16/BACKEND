package pe.edu.upc.ecotrack.serviceinterfaces;

import org.springframework.data.repository.query.Param;
import pe.edu.upc.ecotrack.entities.Pagos;

import java.time.LocalDate;
import java.util.List;

public interface IPagosService {
    public List<Pagos> list();
    public void insert(Pagos p);
    public Pagos listId(int id);
    public void update(Pagos p);
    public void delete(int id);
    public List<String[]> PagosPendientes();
    public List<String[]>PagosEntreFechas (@Param("fecha_inicio") LocalDate fecha_inicio, @Param("fecha_fin") LocalDate fecha_fin);
    public List<Pagos> listarPagosUsername(@Param("username") String username);
    public List<String[]>PagosPorFechaDTO();

}
