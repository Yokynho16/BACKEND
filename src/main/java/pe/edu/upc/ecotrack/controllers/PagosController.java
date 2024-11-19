package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.*;
import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.entities.Pagos;
import pe.edu.upc.ecotrack.serviceinterfaces.IPagosService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/pagos")
@PreAuthorize("hasAuthority('AGRICULTOR')")
public class PagosController {
    @Autowired
    private IPagosService pS;
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('ADMINISTRADOR') or hasAuthority('DISTRIBUIDOR') ")
    @GetMapping
    public List<PagosDTO> listar(){
        return pS.list().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, PagosDTO.class);
        }).collect(Collectors.toList());
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @PostMapping
    public void insertar(@RequestBody PagosDTO dto) {
        ModelMapper m = new ModelMapper();
        Pagos p = m.map(dto, Pagos.class);
        pS.insert(p);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @GetMapping("/{id}")
    public PagosDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        PagosDTO dto = m.map(pS.listId(id), PagosDTO.class);
        return dto;
    }
    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @PutMapping
    public void modificar(@RequestBody PagosDTO dto) {
        ModelMapper m = new ModelMapper();
        Pagos p = m.map(dto, Pagos.class);
        pS.update(p);
    }
    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        pS.delete(id);
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/pagospendientes")
    public List<PagosPendientesDTO> pagosPendientes() {
        List<String[]> lista = pS.PagosPendientes();
        List<PagosPendientesDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            PagosPendientesDTO dto = new PagosPendientesDTO();
            dto.setId_pagos(Integer.parseInt(columna[0]));
            dto.setMonto(Double.parseDouble(columna[1]));
            dto.setFecha_vencimiento(LocalDate.parse(columna[2]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/pagosentrefechas")
    public List<PagosEntreFechasDTO> pagosEntreFechas(@RequestParam("fecha_inicio") LocalDate f_i,@RequestParam("fecha_fin") LocalDate f_f) {
        List<String[]> lista = pS.PagosEntreFechas(f_i, f_f);
        List<PagosEntreFechasDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            PagosEntreFechasDTO dto = new PagosEntreFechasDTO();
            dto.setFecha_pago(LocalDate.parse(columna[0]));
            dto.setCantidad_pagos(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @PreAuthorize("hasAuthority('AGRICULTOR') or hasAuthority('DISTRIBUIDOR')")
    @GetMapping("/mispagos")
    public List<PagosDTO> listarPagosPorUsuario(@RequestParam("username")String username) {
        List<Pagos> pagos = pS.listarPagosUsername(username);
        ModelMapper m = new ModelMapper();
        return pagos.stream().map(pago->m.map(pago, PagosDTO.class)).collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/pagoporfecha")
    public List<PagosPorFechaDTO> pagoporfecha() {
        List<String[]> lista = pS.PagosPorFechaDTO();
        List<PagosPorFechaDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            PagosPorFechaDTO dto = new PagosPorFechaDTO();
            dto.setFecha_pago(LocalDate.parse(columna[0]));
            dto.setCantidad(Integer.parseInt(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
