package pe.edu.upc.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Quejas;
import pe.edu.upc.ecotrack.entities.Vehiculos;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IVehiculosRepository extends JpaRepository<Vehiculos,Integer> {
    @Query(value = "Select \n" +
            "\tv.placa,\n" +
            "\tv.estado,\n" +
            "\tr.fecha_salida,\n" +
            "\tr.fecha_llegada,\n" +
            "\tCount(rs.id_rutas) as cantidad_rutas\n" +
            "From\n" +
            "\tvehiculos v\n" +
            "Inner Join \n" +
            "\trastreos r on v.id_vehiculos = r.id_vehiculos\n" +
            "Inner Join\n" +
            "\trutas rs on r.id_rastreos = rs.id_rastreos\n" +
            "Where\n" +
            "\tv.placa = :placa and r.fecha_llegada Between :fecha_inicio and :fecha_fin \n" +
            "Group by\n" +
            "\tv.placa, v.estado, r.fecha_salida, r.fecha_llegada;",nativeQuery = true)
    public List<String[]> reporteVehiculosPorFechasYCantidadRutas(@Param("placa") String placa,@Param("fecha_inicio") LocalDate fecha_inicio, @Param("fecha_fin")LocalDate fecha_fin);

    @Query(value="Select \n" +
            "\tplaca,\n" +
            "\tcapacidad_carga,\n" +
            "\testado\n" +
            "From\n" +
            "\tvehiculos\n" +
            "Where\n" +
            "\testado = 'Disponible'", nativeQuery = true)
    public List<String[]> reporteVehiculosDisponible();


    @Query(value = "select\n" +
            "\tv.id_vehiculos,\n" +
            "\tv.placa,\n" +
            "\tv.marca,\n" +
            "\tv.modelo,\n" +
            "\tv.capacidad_carga,\n" +
            "\tv.estado,\n" +
            "\tv.fecha_inscripcion,\n" +
            "\tv.id_usuarios\n" +
            "from\n" +
            "\tvehiculos v\n" +
            "inner join\n" +
            "\tusuarios u on v.id_usuarios = u.id_usuarios\n" +
            "where\n" +
            "\tu.username = :username",nativeQuery = true)
    public List<Vehiculos> listarVehiculosUsername(@Param("username") String username);


    @Query(value = "Select \n" +
            "placa,\n" +
            "capacidad_carga\n" +
            "From\n" +
            "vehiculos ", nativeQuery = true)
    public List<String[]>PlacaPorCantidadCargaDTO();


    @Query(value = "SELECT v.placa, COUNT(rs.id_rutas) AS total_rutas\n" +
            "FROM vehiculos v\n" +
            "INNER JOIN rastreos r ON v.id_vehiculos = r.id_vehiculos\n" +
            "INNER JOIN rutas rs ON r.id_rastreos = rs.id_rastreos\n" +
            "GROUP BY v.placa\n" +
            "ORDER BY total_rutas DESC ", nativeQuery = true)
    public List<String[]>VehiculosPorTotalRutasDTO();


}
