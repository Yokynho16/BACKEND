package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.QuejaPorTipoDTO;
import pe.edu.upc.ecotrack.dtos.RolesDTO;
import pe.edu.upc.ecotrack.dtos.UsuariosPorRolDTO;
import pe.edu.upc.ecotrack.entities.Roles;
import pe.edu.upc.ecotrack.serviceinterfaces.IRolesService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class RolesController {
    @Autowired
    private IRolesService rS;

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping
    public List<RolesDTO> listar() {
        return rS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, RolesDTO.class);
        }).collect(Collectors.toList());
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PostMapping
    public void insertar(@RequestBody RolesDTO dto) {
        ModelMapper m = new ModelMapper();
        Roles r = m.map(dto, Roles.class);
        rS.insert(r);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/{id}")
    public RolesDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        RolesDTO dto = m.map(rS.listId(id), RolesDTO.class);
        return dto;
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PutMapping
    public void modificar(@RequestBody RolesDTO dto) {
        ModelMapper m = new ModelMapper();
        Roles r = m.map(dto, Roles.class);
        rS.update(r);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        rS.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/usuarioporrol")
    public List<UsuariosPorRolDTO> usuarioporrol() {
        List<String[]> lista = rS.UsuariosPorRolDTO();
        List<UsuariosPorRolDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            UsuariosPorRolDTO dto = new UsuariosPorRolDTO();
            dto.setTipo(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
