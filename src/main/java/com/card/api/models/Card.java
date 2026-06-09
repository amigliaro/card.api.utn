package com.card.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String marca;
    private String tipoTarjeta;
    private String nroTarjeta;
    private String fechaVencimiento;
    private String CVC;
    private Double limiteCredito;
    private Boolean activa;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}
