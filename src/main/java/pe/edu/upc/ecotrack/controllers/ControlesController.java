package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.AgricultorLoteDTO;
import pe.edu.upc.ecotrack.dtos.ControlesDTO;
import pe.edu.upc.ecotrack.dtos.LotesPorControlDTO;
import pe.edu.upc.ecotrack.entities.Controles;
import pe.edu.upc.ecotrack.serviceinterfaces.IControlesService;
import pe.edu.upc.ecotrack.serviceinterfaces.IRolesService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR')")
@RequestMapping("/controles")
public class ControlesController {
    @Autowired
    private IControlesService cS;

    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR')")
    @GetMapping
    public List<ControlesDTO> listar() {
        return cS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ControlesDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PostMapping
    public void insertar(@RequestBody ControlesDTO dto) {
        ModelMapper m = new ModelMapper();
        Controles c = m.map(dto, Controles.class);
        cS.insert(c);
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/{id}")
    public ControlesDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        ControlesDTO dto = m.map(cS.listId(id), ControlesDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PutMapping
    public void modificar(@RequestBody ControlesDTO dto) {
        ModelMapper m = new ModelMapper();
        Controles c = m.map(dto, Controles.class);
        cS.update(c);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        cS.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/listarlotesportipodecontrol")
    public List<LotesPorControlDTO> lotesxcontrol(@RequestParam String tipo_control) {
        List<String[]> lista = cS.listarlotesportipodecontrol(tipo_control);
        List<LotesPorControlDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            LotesPorControlDTO dto = new LotesPorControlDTO();
            dto.setTipo_control(columna[0]);
            dto.setNombre(columna[1]);
            dto.setTipo_cultivo(columna[2]);
            dto.setFecha_siembra(LocalDate.parse(columna[3]));
            dto.setEstado(columna[4]);
            dto.setCantidad(Integer.parseInt(columna[5]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

}
