package pe.edu.upc.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.ecotrack.dtos.*;
import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.entities.MetodosPago;
import pe.edu.upc.ecotrack.entities.Pagos;
import pe.edu.upc.ecotrack.serviceinterfaces.IMetodosPagoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metodospago")
public class MetodosPagoController {
    @Autowired
    private IMetodosPagoService mS;
    @GetMapping
    public List<MetodosPagoDTO> Listar() {
        return mS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x, MetodosPagoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping
    public void insertar(@RequestBody MetodosPagoDTO dto) {
        ModelMapper m = new ModelMapper();
        MetodosPago p = m.map(dto, MetodosPago.class);
        mS.insert(p);
    }
    @GetMapping("/{id}")
    public MetodosPagoDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        MetodosPagoDTO dto = m.map(mS.listId(id), MetodosPagoDTO.class);
        return dto;
    }
    @PutMapping
    public void modificar(@RequestBody MetodosPagoDTO dto) {
        ModelMapper m=new ModelMapper();
        MetodosPago p=m.map(dto,MetodosPago.class);
        mS.update(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable ("id") Integer id) { mS.delete(id);}


    @PreAuthorize("hasAuthority('AGRICULTOR')")
    @GetMapping("/mismetodospago")
    public List<MetodosPagoDTO> listarMetodosPagoPorUsuario(@RequestParam("username")String username) {
        List<MetodosPago> mpagos = mS.listarMetodosPagoUsername(username);
        ModelMapper m = new ModelMapper();
        return mpagos.stream().map(mpago->m.map(mpago, MetodosPagoDTO.class)).collect(Collectors.toList());
    }


    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    @GetMapping("/montopormetodo")
    public List<SumaMontoPorMetodoPagoDTO> montopormetodo() {
        List<String[]> lista = mS.SumaMontoPorMetodoPagoDTO();
        List<SumaMontoPorMetodoPagoDTO> listaDTO = new ArrayList<>();
        for (String[] columna : lista) {
            SumaMontoPorMetodoPagoDTO dto = new SumaMontoPorMetodoPagoDTO();
            dto.setMetodo_pago(columna[0]);
            dto.setTotal_pagos(Double.parseDouble(columna[1]));
            listaDTO.add(dto);
        }
        return listaDTO;
    }
}
