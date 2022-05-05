package com.mercadolibre.grupo1.projetointegrador.unit;

import com.mercadolibre.grupo1.projetointegrador.dtos.SectionDTO;
import com.mercadolibre.grupo1.projetointegrador.dtos.SectionSearchDTO;
import com.mercadolibre.grupo1.projetointegrador.entities.BatchStock;
import com.mercadolibre.grupo1.projetointegrador.entities.Product;
import com.mercadolibre.grupo1.projetointegrador.entities.Section;
import com.mercadolibre.grupo1.projetointegrador.entities.Warehouse;
import com.mercadolibre.grupo1.projetointegrador.entities.enums.ProductCategory;
import com.mercadolibre.grupo1.projetointegrador.exceptions.EntityNotFoundException;
import com.mercadolibre.grupo1.projetointegrador.exceptions.ListIsEmptyException;
import com.mercadolibre.grupo1.projetointegrador.exceptions.NotFoundException;
import com.mercadolibre.grupo1.projetointegrador.repositories.SectionRepository;
import com.mercadolibre.grupo1.projetointegrador.services.SectionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários da classe SectionService
 *
 * @author Weverton Bruno
 */
@ExtendWith(MockitoExtension.class)
class SectionServiceTest {

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private SectionService sectionService;

    @Test
    @DisplayName("Testa se uma excecao é lancada quando uma sessao não é encontrada")
    public void itShouldReturnASectionNotFoundException() {
        SectionDTO sectionDTO = createFakeSectionDTO();
        when(sectionRepository.findByIdAndWarehouse_Id(anyLong(), anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            sectionService.findBySectionDto(sectionDTO);
        });

        assertEquals("Sessão e/ou Armazem não encontrado na base de dados", exception.getMessage());
    }

    private SectionDTO createFakeSectionDTO() {
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setSectionCode(1L);
        sectionDTO.setWarehouseCode(1L);
        return sectionDTO;
    }

    /**
     * Testes unitários, descrição em DisplayName
     * @author Nayara Coca
     */
    @Test
    @DisplayName("Testa se a query retorna o espaço disponível por sessão no armazém")
    public void itShouldReturnTheSectionAvailableSpace() {
        List<SectionSearchDTO> section = createSectionSearch();
        Mockito.when(sectionRepository.findByWarehouseId(1L)).thenReturn(section);
        List<SectionSearchDTO> section1 = sectionService.findSection(1L);
        Mockito.verify(sectionRepository).findByWarehouseId(Mockito.anyLong());
    }

    @Test
    @DisplayName("Testa se o armazém não existe")
    public void itShoultReturnAnEmptyWarehouse() {
        Mockito.when(sectionRepository.findByWarehouseId(Mockito.anyLong())).thenReturn(new ArrayList<>());
        Throwable listIsEmptyException = assertThrows(NotFoundException.class,
                () -> sectionService.findSection(2L));
        Assertions.assertEquals(listIsEmptyException.getMessage(),"ARMAZÉM NÃO ENCONTRADO");
    }

    public List<SectionSearchDTO> createSectionSearch() {
        SectionSearchDTO createSectionSearch = new SectionSearchDTO();
        Product productVolume = new Product();
        BatchStock productQuantity = new BatchStock();
        Warehouse warehouseId = new Warehouse();

        warehouseId.setId(1L);
        createSectionSearch.setSectionCode(1L);
        createSectionSearch.setOriginalCapacity(50003D);
        productQuantity.setCurrentQuantity(5);
        productVolume.setVolume(345D);
        createSectionSearch.getCurrentCapacity();
        return Arrays.asList(createSectionSearch);
    }

}