package mx.edu.utez.almacenes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "almacenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String clave;

    @NotNull(message = "Registration date is required")
    @Column(nullable = false)
    private LocalDate fecha_registro;

    @NotNull(message = "Sale price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Sale price must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_venta;

    @NotNull(message = "Rental price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rental price must be greater than 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_renta;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Size is required")
    @Column(nullable = false)
    private AlmacenSize size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cede_id", nullable = false)
    @NotNull(message = "Cede is required")
    private Cede cede;

    @PrePersist
    @PreUpdate
    public void generateKey() {
        if (this.clave == null && this.cede != null) {
            this.clave = String.format("%s-A%d",
                    this.cede.getClave(),
                    this.id != null ? this.id : 0);
        }
    }
}