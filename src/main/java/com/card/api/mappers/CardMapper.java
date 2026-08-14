package com.card.api.mappers;

import com.card.api.dto.CardDTO;
import com.card.api.models.Card;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static CardDTO cardToDTO(Card card) {
        if (card == null) return null;
        return CardDTO.builder()
                .marca(card.getMarca())
                .tipoTarjeta(card.getTipoTarjeta())
                .nroTarjeta(card.getNroTarjeta())
                .fechaVencimiento(card.getFechaVencimiento())
                .CVC(card.getCVC())
                .limiteCredito(card.getLimiteCredito())
                .build();
    }

    public static List<CardDTO> cardToDTOList(List<Card> cards) {
        return cards.stream()
                .map(CardMapper::cardToDTO)
                .collect(Collectors.toList());
    }

    public static Card DTOtoCard(CardDTO cardDTO) {
        if (cardDTO == null) return null;
        return Card.builder().marca(cardDTO.getMarca())
                .tipoTarjeta(cardDTO.getTipoTarjeta())
                .nroTarjeta(cardDTO.getNroTarjeta())
                .fechaVencimiento(cardDTO.getFechaVencimiento())
                .CVC(cardDTO.getCVC())
                .limiteCredito(cardDTO.getLimiteCredito())
                .activa(true)
                .fechaCreacion(LocalDate.now())
                .fechaModificacion(LocalDate.now())
                .clienteId(cardDTO.getIdCliente()).build();
    }
}
