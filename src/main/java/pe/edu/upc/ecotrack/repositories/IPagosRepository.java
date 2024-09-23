package pe.edu.upc.ecotrack.repositories;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.ecotrack.entities.Pagos;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPagosRepository extends JpaRepository<Pagos, Integer> {
    @Query(value="Select\n" +
            "\tid_pagos,\n" +
            "\tmonto,\n" +
            "\tfecha_vencimiento\n" +
            "From\n" +
            "\tpagos\n" +
            "Where\n" +
            "\testado = 'Pendiente'\n" +
            "Order By\n" +
            "\tmonto desc;",nativeQuery = true)
    public List<String[]> PagosPendientes();

    @Query(value="SELECT fecha_pago, \n" +
            "COUNT(id_pagos) AS cantidad_pagos\n" +
            "FROM pagos\n" +
            "WHERE fecha_pago BETWEEN :fecha_inicio AND :fecha_fin\n" +
            "GROUP BY fecha_pago\n" +
            "ORDER BY fecha_pago;",nativeQuery = true)
    public List<String[]>PagosEntreFechas (@Param("fecha_inicio") LocalDate fecha_inicio, @Param("fecha_fin") LocalDate fecha_fin);

}
