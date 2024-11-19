package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.AgricultorLoteDTO;
import pe.edu.upc.ecotrack.dtos.LotesDTO;
import pe.edu.upc.ecotrack.dtos.LotesPorUsuarioDTO;
import pe.edu.upc.ecotrack.dtos.TotalCotizacionesPorUsuarioDTO;
import pe.edu.upc.ecotrack.entities.Controles;
import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.entities.Usuarios;
import pe.edu.upc.ecotrack.serviceinterfaces.ILotesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lotes")
@PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
public class LotesController {
    @Autowired
    private ILotesService lS;
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @GetMapping
    public List<LotesDTO> listar() {
        return lS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, LotesDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @PostMapping
    public void insertar(@RequestBody LotesDTO dto) {
        ModelMapper m = new ModelMapper();
        Lotes l = m.map(dto, Lotes.class);
        lS.insert(l);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/{id}")
    public LotesDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        LotesDTO dto = m.map(lS.listId(id), LotesDTO.class);
        return dto;
    }

    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @PutMapping
    public void modificar(@RequestBody LotesDTO dto) {
        ModelMapper m = new ModelMapper();
        Lotes l = m.map(dto, Lotes.class);
        lS.update(l);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        lS.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/lotesxagricultor")
    public List<AgricultorLoteDTO> lotesxAgricultor(@RequestParam String nombre) {
        List<String[]> lista = lS.buscarLotesPorNombreAgricultor(nombre);
        List<AgricultorLoteDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            AgricultorLoteDTO dto = new AgricultorLoteDTO();
            dto.setNombre(columna[0]);
            dto.setNombre_lote(columna[1]);
            dto.setTipo_cultivo(columna[2]);
            dto.setFecha_siembra(LocalDate.parse(columna[3]));
            dto.setEstado(columna[4]);
            dto.setCantidad(Integer.parseInt(columna[5]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }


    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @GetMapping("/mislotes")
    public List<LotesDTO> listarLotesPorUsuario(@RequestParam("username")String username) {
        List<Lotes> lotes = lS.listarLotesprueba(username);
        ModelMapper m = new ModelMapper();
        return lotes.stream().map(lote->m.map(lote, LotesDTO.class)).collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/lotesporusuario")
    public List<LotesPorUsuarioDTO> lotesporusuario() {
        List<String[]> lista = lS.LotesPorUsuarioDTO();
        List<LotesPorUsuarioDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            LotesPorUsuarioDTO dto = new LotesPorUsuarioDTO();
            dto.setNombre(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
