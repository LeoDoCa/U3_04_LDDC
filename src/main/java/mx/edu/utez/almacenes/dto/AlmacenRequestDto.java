package mx.edu.utez.almacenes.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mx.edu.utez.almacenes.models.AlmacenSize;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlmacenRequestDto {

    @NotNull(message = "Registration date is required")
    private LocalDate registrationDate;

    @NotNull(message = "Sale price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Sale price must be greater than 0")
    private BigDecimal salePrice;

    @NotNull(message = "Rental price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Rental price must be greater than 0")
    private BigDecimal rentalPrice;

    @NotNull(message = "Size is required")
    private AlmacenSize size;

    @NotNull(message = "Cede ID is required")
    private Long cedeId;
}