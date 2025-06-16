package mx.edu.utez.almacenes.repository;

import mx.edu.utez.almacenes.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByPhoneNumber(String phoneNumber);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre_completo) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Cliente> findByFullNameContainingIgnoreCase(@Param("name") String name);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre_completo) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Cliente> findByFullNameStartingWithIgnoreCase(@Param("prefix") String prefix);
}
