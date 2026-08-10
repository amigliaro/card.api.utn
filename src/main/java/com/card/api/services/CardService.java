package com.card.api.services;

import com.card.api.dto.CardDTO;
import com.card.api.exceptions.InternalServerErrorException;
import com.card.api.exceptions.NotFoundException;
import com.card.api.mappers.CardMapper;
import com.card.api.models.Card;
import com.card.api.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public List<CardDTO> getCard() {
        try {
            return CardMapper.cardToDTOList(cardRepository.findAll());
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al listar las tarjetas: " + ex.getMessage());
        }
    }

    public CardDTO getCardById(Long id) throws NotFoundException {
        Optional<Card> auxCard = cardRepository.findById(id);
        if (auxCard.isPresent()) {
            return CardMapper.cardToDTO(auxCard.get());
        } else {
            throw new NotFoundException("No se encontró el cliente solicitado");
        }
    }

    public CardDTO insertCard(CardDTO card) {
        try {
            return CardMapper.cardToDTO(cardRepository.save(CardMapper.DTOtoCard(card)));
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al insertar una tarjeta: " + ex.getMessage());
        }
    }

    public CardDTO updateCard(Long idCard, CardDTO card) {
        Optional<Card> auxCard = cardRepository.findById(idCard);

        if (auxCard.isPresent()) {
            if (card.getMarca() != null) auxCard.get().setMarca(card.getMarca());
            if (card.getTipoTarjeta() != null) auxCard.get().setTipoTarjeta(card.getTipoTarjeta());
            if (card.getNroTarjeta() != null) auxCard.get().setNroTarjeta(card.getNroTarjeta());
            if (card.getFechaVencimiento() != null) auxCard.get().setFechaVencimiento(card.getFechaVencimiento());
            if (card.getCVC() != null) auxCard.get().setCVC(card.getCVC());
            if (card.getLimiteCredito() != null) auxCard.get().setLimiteCredito(card.getLimiteCredito());
            auxCard.get().setFechaModificacion(LocalDate.now());
            auxCard.get().setClienteId(auxCard.get().getClienteId());

        } else {
            throw new NotFoundException("No se encontró información para la tarjeta ingresada.");
        }
        try {
            return CardMapper.cardToDTO(cardRepository.save(auxCard.get()));
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al modificar una tarjeta: " + ex.getMessage());
        }
    }

    public void deleteCard(Long idCard) {
        Optional<Card> auxCard = cardRepository.findById(idCard);
        if (auxCard.isPresent()) {
            try {
                cardRepository.deleteById(idCard);
            } catch (InternalServerErrorException ex) {
                throw new InternalServerErrorException("Error al eliminar una tarjeta: " + ex.getMessage());
            }
        } else {
            throw new NotFoundException("No se encontró información para la tarjeta ingresada.");
        }
    }

    public List<CardDTO> getCardByCliente(Long idCliente) {
        return CardMapper.cardToDTOList(cardRepository.findByClienteId(idCliente));

    }
}
