package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.*;
import pe.edu.upc.ecotrack.entities.Cultivos;
import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.serviceinterfaces.ICultivosService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cultivos")
@PreAuthorize("hasAuthority('AGRICULTOR')")
public class CultivosController {
    @Autowired
    private ICultivosService cS;
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @GetMapping
    public List<CultivosDTO> listar(){
        return cS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, CultivosDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @PostMapping
    public void insertar(@RequestBody CultivosDTO dto) {
        ModelMapper m = new ModelMapper();
        Cultivos c = m.map(dto, Cultivos.class);
        cS.insert(c);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @GetMapping("/{id}")
    public CultivosDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        CultivosDTO dto = m.map(cS.listId(id), CultivosDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @PutMapping
    public void modificar(@RequestBody CultivosDTO dto) {
        ModelMapper m = new ModelMapper();
        Cultivos c = m.map(dto, Cultivos.class);
        cS.update(c);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/buscarCultivos")
    public List<BuscarCultivosDTO>buscarCultivos(@RequestParam String nombre) {
        List<String[]> lista = cS.buscarNombre(nombre);
        List<BuscarCultivosDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            BuscarCultivosDTO dto = new BuscarCultivosDTO();
            dto.setIdCultivo(Integer.parseInt(columna[0]));
            dto.setTipoCultivo(columna[1]);
            listaDTO.add(dto);
        }
        return listaDTO;
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @GetMapping("/miscultivos")
    public List<CultivosDTO> listarCultivosPorUsuario(@RequestParam("username")String username) {
        List<Cultivos> cultivos = cS.listarCultivosUsername(username);
        ModelMapper m = new ModelMapper();
        return cultivos.stream().map(cultivo->m.map(cultivo, CultivosDTO.class)).collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/cultivosportipo")
    public List<CultivosPorTipoDTO> cultivosportipo() {
        List<String[]> lista = cS.CultivosPorTipo();
        List<CultivosPorTipoDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            CultivosPorTipoDTO dto = new CultivosPorTipoDTO();
            dto.setTipo(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
