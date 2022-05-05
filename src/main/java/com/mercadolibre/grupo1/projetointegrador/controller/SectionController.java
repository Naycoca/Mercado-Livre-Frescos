package com.mercadolibre.grupo1.projetointegrador.controller;

import com.mercadolibre.grupo1.projetointegrador.dtos.SectionSearchDTO;
import com.mercadolibre.grupo1.projetointegrador.entities.Section;
import com.mercadolibre.grupo1.projetointegrador.services.SectionService;
import com.mercadolibre.grupo1.projetointegrador.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nayara Coca
 * controlador que mostra o espaço disponível nas diferentes sessões
 */
@RestController
@RequestMapping("/api/v1/fresh-products/section")
public class SectionController {
@Autowired
    private SectionService sectionService;

    @GetMapping
    public ResponseEntity<List<SectionSearchDTO>> listSectionSearch(@RequestParam(required = false, name =
            "warehouseCode") Long id) {
        List<SectionSearchDTO> listSectionsByWarehouse = sectionService.findSection(id);
        return ResponseEntity.ok().body(listSectionsByWarehouse);
    }


}
