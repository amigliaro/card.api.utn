package com.card.api.mappers;

import com.card.api.dto.CardDTO;
import com.card.api.models.Card;

import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static CardDTO cardToDTO(Card card) {
        if (card == null) return null;
        return new CardDTO(card.getMarca(), card.getTipoTarjeta(), card.getNroTarjeta(), card.getFechaVencimiento(), card.getCVC(), card.getLimiteCredito());
    }

    public static List<CardDTO> cardToDTOList(List<Card> cards) {
        return cards.stream()
                .map(CardMapper::cardToDTO)
                .collect(Collectors.toList());
    }

}
