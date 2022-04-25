package com.mercadolibre.grupo1.projetointegrador.controller;

import com.mercadolibre.grupo1.projetointegrador.dtos.ProductDTO;
import com.mercadolibre.grupo1.projetointegrador.dtos.PurchaseOrderStatusDTO;
import com.mercadolibre.grupo1.projetointegrador.entities.PurchaseOrder;
import com.mercadolibre.grupo1.projetointegrador.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Adicionados os EndPoints para realizacao do crud (exceto delete) da API
 *
 * @author  Jefferson Botelho
 * @since   2022-03-22
 *
 */

@RestController
@RequestMapping("/api/v1/fresh-products/")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAllProduct() {
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> listProductForCategory(@RequestParam(required = false, name = "status") PurchaseOrder orderStatus) {
        return null;
    }

    @PostMapping("/orders")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder,
                                                             UriComponentsBuilder uriBuilder) {
        //...

        URI uri =  uriBuilder
                .path("/{idOrder}")
                .buildAndExpand(purchaseOrder.getId())
                .toUri();

        //...
        return null;
    }

    @GetMapping("/orders/{idOrder}")
    public ResponseEntity<PurchaseOrder> showProductsOrder(@PathVariable("idOrder") Long idOrder) {

        return null;
    }

    @PutMapping("/orders/{idOrder}")
    public ResponseEntity<PurchaseOrder> modifyOrderStatusByOpenedOrClosed(@PathVariable Long idOrder,
                                                                           @RequestBody PurchaseOrderStatusDTO statusOrder) {

        PurchaseOrder purchaseOrder = purchaseOrderService.editExistentOrder(idOrder, statusOrder);

        return ResponseEntity.ok(purchaseOrder);
    }

}
