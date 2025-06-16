package mx.edu.utez.almacenes.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDto {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
}