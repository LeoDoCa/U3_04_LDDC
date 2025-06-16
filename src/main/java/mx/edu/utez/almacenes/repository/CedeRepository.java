package mx.edu.utez.almacenes.repository;

import mx.edu.utez.almacenes.models.Cede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CedeRepository extends JpaRepository<Cede, Long> {

    Optional<Cede> findByKey(String key);

    @Query("SELECT c FROM Cede c WHERE LOWER(c.estado) LIKE LOWER(CONCAT('%', :state, '%'))")
    List<Cede> findByStateContainingIgnoreCase(@Param("state") String state);

    @Query("SELECT c FROM Cede c WHERE LOWER(c.municipio) LIKE LOWER(CONCAT('%', :city, '%'))")
    List<Cede> findByCityContainingIgnoreCase(@Param("city") String city);

    boolean existsByStateAndCity(String state, String city);
}
