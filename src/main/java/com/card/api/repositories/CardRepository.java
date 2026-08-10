package com.card.api.repositories;

import com.card.api.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByClienteId(Long idCliente);
}
