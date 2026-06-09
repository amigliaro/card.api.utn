package com.card.api.controllers;

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
    public List<Card> getCards() {
        return cardService.getCard();
    }

    @GetMapping("/{idTarjeta}")
    public Card getCardById(@PathVariable Long idTarjeta) throws NotFoundException {
        return cardService.getCardById(idTarjeta);
    }

    @PostMapping
    public Card insertTarjeta(@RequestBody Card card) {
        return cardService.insertCard(card);
    }

    @PutMapping("/{idTarjeta}")
    public Card insertTarjeta(@PathVariable Long idTarjeta, @RequestBody Card card) {
        return cardService.updateCard(idTarjeta, card);
    }

    @DeleteMapping("/{idTarjeta}")
    public void deleteTarjeta(@PathVariable Long idTarjeta) {
        cardService.deleteCard(idTarjeta);
    }
}
