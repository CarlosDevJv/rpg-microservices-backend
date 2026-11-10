package dev.rpgtrade.auction.model.dto;

import dev.rpgtrade.auction.model.state.TransacacaoState;

public record TransacaoDto(PlayerTransacaoRequest playerTransacaoRequest, InventoryTransacaoRequest inventoryTransacaoRequest, TransacacaoState state) {
}
