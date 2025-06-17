package mx.edu.utez.almacenes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionResponseDto {
    private AlmacenResponseDto almacen;
    private ClienteResponseDto cliente;
    private Boolean esVenta;
    private LocalDateTime fechaTransaccion;
    private BigDecimal montoTransaccion;
    private String tipoTransaccion;
}
