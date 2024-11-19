package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.RastreosDTO;
import pe.edu.upc.ecotrack.dtos.VehiculosDTO;
import pe.edu.upc.ecotrack.entities.Rastreos;
import pe.edu.upc.ecotrack.entities.Vehiculos;
import pe.edu.upc.ecotrack.serviceinterfaces.IRastreosService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rastreos")
@PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
public class RastreosController {
    @Autowired
    private IRastreosService rS;
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') or hasAuthority('AGRICULTOR')")
    @GetMapping
    public List<RastreosDTO> listar(){

        return rS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,RastreosDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @PostMapping
    public void insertar(@RequestBody RastreosDTO dto){
        ModelMapper m=new ModelMapper();
        Rastreos r=m.map(dto,Rastreos.class);
        rS.insert(r);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @GetMapping("/{id}")
    public RastreosDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        RastreosDTO dto = m.map(rS.listId(id), RastreosDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @PutMapping
    public void modifcar(@RequestBody RastreosDTO dto){
        ModelMapper m=new ModelMapper();
        Rastreos r=m.map(dto,Rastreos.class);
        rS.update(r);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable ("id") Integer id) { rS.delete(id);}


    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/misrastreos")
    public List<RastreosDTO> listarRastreosPorUsuario(@RequestParam("username")String username) {
        List<Rastreos> rastreos = rS.listarRastreosUsername(username);
        ModelMapper m = new ModelMapper();
        return rastreos.stream().map(rastreo->m.map(rastreo, RastreosDTO.class)).collect(Collectors.toList());
    }
}
