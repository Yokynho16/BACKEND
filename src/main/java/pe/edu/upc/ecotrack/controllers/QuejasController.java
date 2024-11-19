package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.*;
import pe.edu.upc.ecotrack.entities.Pagos;
import pe.edu.upc.ecotrack.entities.Quejas;
import pe.edu.upc.ecotrack.serviceinterfaces.IQuejasService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quejas")
public class QuejasController {
    @Autowired
    private IQuejasService qS;
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR') ")
    @GetMapping
    public List<QuejasDTO> listar() {
        return qS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,QuejasDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR') ")
    @PostMapping
    public void insertar(@RequestBody QuejasDTO dto) {
        ModelMapper m=new ModelMapper();
        Quejas q=m.map(dto, Quejas.class);
        qS.insert(q);
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR') ")
    @GetMapping("/{id}")
    public QuejasDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        QuejasDTO dto = m.map(qS.listId(id), QuejasDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR') ")
    @PutMapping
    public void modificar(@RequestBody QuejasDTO dto) {
        ModelMapper m=new ModelMapper();
        Quejas q=m.map(dto,Quejas.class);
        qS.update(q);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable ("id") Integer id) { qS.delete(id);}

    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/misquejas")
    public List<QuejasDTO> listarQuejasPorUsuario(@RequestParam("username") String username) {
        List<Quejas> quejas = qS.listarQuejasUsername(username);
        ModelMapper m = new ModelMapper();
        return quejas.stream().map(queja -> m.map(queja, QuejasDTO.class)).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/quejasportipo")
    public List<QuejaPorTipoDTO> quejasportipo() {
        List<String[]> lista = qS.QuejaPorTipoDTO();
        List<QuejaPorTipoDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            QuejaPorTipoDTO dto = new QuejaPorTipoDTO();
            dto.setTipo(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
