package pe.edu.upc.ecotrack.controllers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.RutasDTO;
import pe.edu.upc.ecotrack.dtos.VehiculosDTO;
import pe.edu.upc.ecotrack.entities.Rutas;
import pe.edu.upc.ecotrack.entities.Vehiculos;
import pe.edu.upc.ecotrack.serviceinterfaces.IRutasService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rutas")
@PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
public class RutasController {
    @Autowired
    private IRutasService rS;
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @GetMapping
    public List<RutasDTO> listar(){

        return rS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x,RutasDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @PostMapping
    public void insertar(@RequestBody RutasDTO dto){
        ModelMapper m=new ModelMapper();
        Rutas r=m.map(dto,Rutas.class);
        rS.insert(r);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @GetMapping("/{id}")
    public RutasDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        RutasDTO dto = m.map(rS.listId(id), RutasDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @PutMapping
    public void modificar(@RequestBody RutasDTO dto) {
        ModelMapper m = new ModelMapper();
        Rutas r = m.map(dto, Rutas.class);
        rS.update(r);
    }
    @PreAuthorize("hasAuthority('DISTRIBUIDOR') ")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        rS.delete(id);
    }

    @PreAuthorize("hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/misrutas")
    public List<RutasDTO> listarRutasPorUsuario(@RequestParam("username")String username) {
        List<Rutas> rutas = rS.listarRutasUsername(username);
        ModelMapper m = new ModelMapper();
        return rutas.stream().map(ruta->m.map(ruta, RutasDTO.class)).collect(Collectors.toList());
    }
}
