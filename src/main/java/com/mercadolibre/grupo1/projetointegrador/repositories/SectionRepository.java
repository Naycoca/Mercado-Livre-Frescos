package com.mercadolibre.grupo1.projetointegrador.repositories;

import com.mercadolibre.grupo1.projetointegrador.dtos.SectionSearchDTO;
import com.mercadolibre.grupo1.projetointegrador.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Ederson Rodrigues Araujo
 * Entidade responsável pela Section
 */

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByWarehouse(Long id);

    /**
     * mostra o espaço disponível em determinada sessão
     * @author Nayara Coca
     */

    Optional<Section> findByIdAndWarehouse_Id(Long sectionId, Long warehouseId);

    @Query(value =
            "select new com.mercadolibre.grupo1.projetointegrador.dtos.SectionSearchDTO" +
                    "(s.id, s.capacity, cast(s.capacity-(sum(b.currentQuantity * p.volume)) as double)) " +
                    "from Warehouse w inner join Section s on w.id = s.warehouse.id " +
                    "inner join InboundOrder i on s.id = i.section.id " +
                    "inner join BatchStock b on i.id = b.inboundOrder.id " +
                    "inner join Product p on p.id = b.product.id " +
                    "where w.id = :warehousesId" +
                    " group by s")
    List<SectionSearchDTO> findByWarehouseId(Long warehousesId);

    Optional<Section> findById(Long id);
}
