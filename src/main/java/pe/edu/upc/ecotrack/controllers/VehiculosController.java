package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.*;
import pe.edu.upc.ecotrack.entities.Quejas;
import pe.edu.upc.ecotrack.entities.Vehiculos;
import pe.edu.upc.ecotrack.serviceinterfaces.IVehiculosService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vehiculos")
@PreAuthorize("hasAuthority('DISTRIBUIDOR')")
public class VehiculosController {
    @Autowired
    private IVehiculosService vS;
    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @GetMapping
    public List<VehiculosDTO> listar(){
        return vS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,VehiculosDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @PostMapping
    public void insertar(@RequestBody VehiculosDTO dto){
        ModelMapper m=new ModelMapper();
        Vehiculos v=m.map(dto, Vehiculos.class);
        vS.insert(v);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/{id}")
    public VehiculosDTO listarId(@PathVariable ("id") Integer id){
        ModelMapper m=new ModelMapper();
        VehiculosDTO dto=m.map(vS.listId(id),VehiculosDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @PutMapping
    public void modificar(@RequestBody VehiculosDTO dto) {
        ModelMapper m = new ModelMapper();
        Vehiculos ve = m.map(dto, Vehiculos.class);
        vS.update(ve);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id")Integer id){vS.delete(id);}

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/rastreoxruta")
    public List<VehiculoRastreoRutaDTO> rastreoXRuta(@RequestParam String pl,@RequestParam LocalDate f_i,@RequestParam LocalDate f_f) {
        List<String[]> lista = vS.reporteVehiculosPorFechasYCantidadRutas(pl,f_i,f_f);
        List<VehiculoRastreoRutaDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            VehiculoRastreoRutaDTO dto = new VehiculoRastreoRutaDTO();
            dto.setPlaca(columna[0]);
            dto.setEstado(columna[1]);
            dto.setFecha_salida(LocalDate.parse(columna[2]));
            dto.setFecha_llegada(LocalDate.parse(columna[3]));
            dto.setCantidad_rutas(Integer.parseInt(columna[4]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/vehiculosdisponibles")
    public List<VehiculosDisponibleDTO> vehiculosDisponibles() {
        List<String[]> lista = vS.reporteVehiculosDisponible();
        List<VehiculosDisponibleDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            VehiculosDisponibleDTO dto = new VehiculosDisponibleDTO();
            dto.setPlaca(columna[0]);
            dto.setCapacidad_carga(Integer.parseInt(columna[1]));
            dto.setEstado(columna[2]);
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/misvehiculos")
    public List<VehiculosDTO> listarVehiculosPorUsuario(@RequestParam("username")String username) {
        List<Vehiculos> vehiculos = vS.listarVehiculosUsername(username);
        ModelMapper m = new ModelMapper();
        return vehiculos.stream().map(vehiculo->m.map(vehiculo, VehiculosDTO.class)).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/placaporcantidadcarga")
    public List<PlacaPorCantidadCargaDTO> placaporcantidadcarga() {
        List<String[]> lista = vS.PlacaPorCantidadCargaDTO();
        List<PlacaPorCantidadCargaDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            PlacaPorCantidadCargaDTO dto = new PlacaPorCantidadCargaDTO();
            dto.setPlaca(columna[0]);
            dto.setCapacidad_carga(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/vehiculosportotalrutas")
    public List<VehiculosPorTotalRutasDTO> vehiculosportotalrutas() {
        List<String[]> lista = vS.VehiculosPorTotalRutasDTO();
        List<VehiculosPorTotalRutasDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            VehiculosPorTotalRutasDTO dto = new VehiculosPorTotalRutasDTO();
            dto.setPlaca(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
