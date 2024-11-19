package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.CotizacionesDTO;
import pe.edu.upc.ecotrack.dtos.PagosEntreFechasDTO;
import pe.edu.upc.ecotrack.dtos.TotalCotizacionesPorUsuarioDTO;
import pe.edu.upc.ecotrack.dtos.VehiculosDTO;
import pe.edu.upc.ecotrack.entities.Cotizaciones;
import pe.edu.upc.ecotrack.entities.Vehiculos;
import pe.edu.upc.ecotrack.serviceinterfaces.ICotizacionesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cotizaciones")
@PreAuthorize("hasAuthority('DISTRIBUIDOR') or hasAuthority('AGRICULTOR')")
public class CotizacionesController {
    @Autowired
    private ICotizacionesService cS;
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') or hasAuthority('AGRICULTOR')")
    @GetMapping
    public List<CotizacionesDTO> listar() {
        return cS.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x, CotizacionesDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') or hasAuthority('AGRICULTOR')")
    @PostMapping
    public void insertar(@RequestBody CotizacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Cotizaciones c = m.map(dto, Cotizaciones.class);
        cS.insert(c);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') or hasAuthority('AGRICULTOR')")
    @GetMapping("/{id}")
    public CotizacionesDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        CotizacionesDTO dto = m.map(cS.listId(id), CotizacionesDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') or hasAuthority('AGRICULTOR')")
    @PutMapping
    public void modificar(@RequestBody CotizacionesDTO dto) {
        ModelMapper m = new ModelMapper();
        Cotizaciones c = m.map(dto, Cotizaciones.class);
        cS.update(c);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }


    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/miscotizaciones")
    public List<CotizacionesDTO> listarCotizacionesPorUsuario(@RequestParam("username")String username) {
        List<Cotizaciones> cotizaciones = cS.listarCotizacionesUsername(username);
        ModelMapper m = new ModelMapper();
        return cotizaciones.stream().map(cotizacion->m.map(cotizacion, CotizacionesDTO.class)).collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/totalcotizacionesporusuario")
    public List<TotalCotizacionesPorUsuarioDTO> totalcotizacionesporusuario() {
        List<String[]> lista = cS.TotalCotizacionesPorUsuarioDTO();
        List<TotalCotizacionesPorUsuarioDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            TotalCotizacionesPorUsuarioDTO dto = new TotalCotizacionesPorUsuarioDTO();
            dto.setNombre(columna[0]);
            dto.setPrecio(Double.parseDouble(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

}
