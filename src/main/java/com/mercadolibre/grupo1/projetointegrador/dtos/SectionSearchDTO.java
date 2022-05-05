package com.mercadolibre.grupo1.projetointegrador.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nayara Coca
 * dto para calculo do espaço disponível na sessão atualmente
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionSearchDTO {
    private Long sectionCode;
    private Double originalCapacity;
    private Double currentCapacity;
}
