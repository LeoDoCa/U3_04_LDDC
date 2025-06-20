package mx.edu.utez.almacenes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Table(name = "cedes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String clave;

    @NotBlank(message = "State is required")
    @Column(nullable = false)
    private String estado;

    @NotBlank(message = "City is required")
    @Column(nullable = false)
    private String municipio;

}

