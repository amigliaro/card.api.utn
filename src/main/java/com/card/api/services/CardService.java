package com.card.api.services;

import com.card.api.exceptions.InternalServerErrorException;
import com.card.api.exceptions.NotFoundException;
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


    public List<Card> getCard() {
        try {

            return cardRepository.findAll();
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al listar las tarjetas: " + ex.getMessage());
        }
    }

    public Card getCardById(Long id) throws NotFoundException {
        Optional<Card> auxCliente = cardRepository.findById(id);
        if (auxCliente.isPresent()) {
            return auxCliente.get();
        } else {
            throw new NotFoundException("No se encontró la tarjeta solicitada");
        }
    }

    public Card insertCard(Card card) {
        try {
            return cardRepository.save(card);
        } catch (InternalServerErrorException ex) {
            throw new InternalServerErrorException("Error al insertar una tarjeta: " + ex.getMessage());
        }
    }

    public Card updateCard(Long idCard, Card card) {
        Optional<Card> auxCard = cardRepository.findById(idCard);

        if (auxCard.isPresent()) {
            if (card.getMarca() != null) auxCard.get().setMarca(card.getMarca());
            if (card.getTipoTarjeta() != null) auxCard.get().setTipoTarjeta(card.getTipoTarjeta());
            if (card.getNroTarjeta() != null) auxCard.get().setNroTarjeta(card.getNroTarjeta());
            if (card.getFechaVencimiento() != null) auxCard.get().setFechaVencimiento(card.getFechaVencimiento());
            if (card.getCVC() != null) auxCard.get().setCVC(card.getCVC());
            if (card.getLimiteCredito() != null) auxCard.get().setLimiteCredito(card.getLimiteCredito());
            card.setFechaModificacion(LocalDate.now());

        } else {
            throw new NotFoundException("No se encontró información para la tarjeta ingresada.");
        }
        try {
            return cardRepository.save(auxCard.get());
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
}
