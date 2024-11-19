package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.AgricultorPagoDTO;
import pe.edu.upc.ecotrack.dtos.QuejasPorUsuarioDTO;
import pe.edu.upc.ecotrack.dtos.UsuariosDTO;
import pe.edu.upc.ecotrack.entities.Usuarios;
import pe.edu.upc.ecotrack.serviceinterfaces.IUsuariosService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR') ")
public class UsuariosController {
    @Autowired
    private IUsuariosService uS;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR') ")
    @GetMapping
    public List<UsuariosDTO> listar() {
        return uS.list().stream().map(x->{
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(x,UsuariosDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PostMapping
    public void insertar(@RequestBody UsuariosDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Usuarios u = modelMapper.map(dto, Usuarios.class);
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        uS.insert(u);
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/{id}")
    public UsuariosDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        UsuariosDTO dto = m.map(uS.listId(id), UsuariosDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @PutMapping
    public void modificar(@RequestBody UsuariosDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuarios u = m.map(dto, Usuarios.class);
        uS.update(u);
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        uS.delete(id);
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/pagosagricultor")
    public List<AgricultorPagoDTO> pagosxNombre(@RequestParam String nombre) {
        List<String[]> lista = uS.reporteAgricultorVerPagos(nombre);
        List<AgricultorPagoDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            AgricultorPagoDTO dto = new AgricultorPagoDTO();
            dto.setNombre(columna[0]);
            dto.setId_pagos(Integer.parseInt(columna[1]));
            dto.setMonto(Integer.parseInt(columna[2]));
            dto.setFecha_pago(LocalDate.parse(columna[3]));
            dto.setEstado(columna[4]);
            listaDTO.add(dto);
        }
        return listaDTO;
    }
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/quejasporusuario")
    public List<QuejasPorUsuarioDTO> quejasPorUsuario() {
        List<String[]> lista = uS.quejasporUsuarios();
        List<QuejasPorUsuarioDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            QuejasPorUsuarioDTO dto = new QuejasPorUsuarioDTO();
            dto.setNombre(columna[0]);
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR') or hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/obtenerid")
    public int obtenerIdPorUsername(@RequestParam String username) {
        return uS.obtenerId(username);
    }

}
