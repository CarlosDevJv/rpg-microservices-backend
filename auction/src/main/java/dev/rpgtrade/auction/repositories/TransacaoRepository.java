package dev.rpgtrade.auction.repositories;

import dev.rpgtrade.auction.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
