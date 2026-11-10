package dev.rpgtrade.auction.model;

import dev.rpgtrade.auction.model.state.TransacacaoState;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transacao {
    @GeneratedValue(strategy = GenerationType.UUID) @Id
    private UUID uuid;
    private String sendPlayer;
    private String receiverPlayer;
    private String itemId;
    private Double coins;
    private TransacacaoState state;


    public Transacao(String sendPlayer, String receiverPlayer, String itemId, Double coins, TransacacaoState state) {
        this.sendPlayer = sendPlayer;
        this.receiverPlayer = receiverPlayer;
        this.itemId = itemId;
        this.coins = coins;
        this.state = state;
    }
}
