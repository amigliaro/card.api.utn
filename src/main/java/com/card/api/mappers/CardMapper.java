package com.card.api.mappers;

import com.card.api.dto.CardDTO;
import com.card.api.models.Card;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static CardDTO cardToDTO(Card card) {
        if (card == null) return null;
        return new CardDTO(card.getMarca(), card.getTipoTarjeta(), card.getNroTarjeta(), card.getFechaVencimiento(), card.getCVC(), card.getLimiteCredito(), null);
    }

    public static List<CardDTO> cardToDTOList(List<Card> cards) {
        return cards.stream()
                .map(CardMapper::cardToDTO)
                .collect(Collectors.toList());
    }

    public static Card DTOtoCard(CardDTO cardDTO) {
        if (cardDTO == null) return null;
        return new Card(cardDTO.getMarca(), cardDTO.getTipoTarjeta(), cardDTO.getNroTarjeta(), cardDTO.getFechaVencimiento(), cardDTO.getCVC(), cardDTO.getLimiteCredito(), true, LocalDate.now(), LocalDate.now(), cardDTO.getIdCliente());
    }
}
