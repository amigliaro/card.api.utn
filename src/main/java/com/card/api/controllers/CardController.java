package com.card.api.controllers;

import com.card.api.dto.CardDTO;
import com.card.api.exceptions.NotFoundException;
import com.card.api.models.Card;
import com.card.api.services.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/tarjetas")
    public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardDTO> getCards() {
        return cardService.getCard();
    }

    @GetMapping("/{idTarjeta}")
    public CardDTO getCardById(@PathVariable Long idTarjeta) throws NotFoundException {
        return cardService.getCardById(idTarjeta);
    }

    @PostMapping
    public CardDTO insertTarjeta(@RequestBody CardDTO card) {
        return cardService.insertCard(card);
    }

    @PutMapping("/{idTarjeta}")
    public CardDTO insertTarjeta(@PathVariable Long idTarjeta, @RequestBody CardDTO card) {
        return cardService.updateCard(idTarjeta, card);
    }

    @DeleteMapping("/{idTarjeta}")
    public void deleteTarjeta(@PathVariable Long idTarjeta) {
        cardService.deleteCard(idTarjeta);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<CardDTO> getCardByCliente(@PathVariable Long idCliente) {
        return cardService.getCardByCliente(idCliente);
    }

}
