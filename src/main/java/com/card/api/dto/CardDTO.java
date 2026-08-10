package com.card.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private String marca;
    private String tipoTarjeta;
    private String nroTarjeta;
    private String fechaVencimiento;
    private String CVC;
    private Double limiteCredito;
}
