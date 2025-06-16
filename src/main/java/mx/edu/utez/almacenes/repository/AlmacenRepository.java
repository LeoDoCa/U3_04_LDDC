package mx.edu.utez.almacenes.repository;


import mx.edu.utez.almacenes.models.Almacen;
import mx.edu.utez.almacenes.models.AlmacenSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

    Optional<Almacen> findByKey(String key);

    List<Almacen> findByCedeId(Long cedeId);

    List<Almacen> findBySize(AlmacenSize size);

    @Query("SELECT w FROM Almacen w WHERE w.precio_venta BETWEEN :minPrice AND :maxPrice")
    List<Almacen> findBySalePriceBetween(@Param("minPrice") BigDecimal minPrice,
                                           @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT w FROM Almacen w WHERE w.precio_renta BETWEEN :minPrice AND :maxPrice")
    List<Almacen> findByRentalPriceBetween(@Param("minPrice") BigDecimal minPrice,
                                             @Param("maxPrice") BigDecimal maxPrice);

    List<Almacen> findByRegistrationDateAfter(LocalDate date);

    @Query("SELECT COUNT(w) FROM Almacen w WHERE w.cede.id = :cedeId")
    long countByCedeId(@Param("cedeId") Long cedeId);
}
