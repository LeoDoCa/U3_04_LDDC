package mx.edu.utez.almacenes.dto;

import lombok.*;
import mx.edu.utez.almacenes.models.AlmacenSize;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenResponseDto {

    private Long id;
    private String key;
    private LocalDate registrationDate;
    private BigDecimal salePrice;
    private BigDecimal rentalPrice;
    private AlmacenSize size;
    private CedeResponseDto cede;
}
