package dev.rpgtrade.player.model;

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
    private Player sendPlayer;
    private Player receiverPlayer;
}
