package dev.rpgtrade.auction.service;

import dev.rpgtrade.auction.model.Transacao;
import dev.rpgtrade.auction.model.dto.TransacaoDto;
import dev.rpgtrade.auction.model.events.TransacacaoEvents;
import dev.rpgtrade.auction.model.state.TransacacaoState;
import dev.rpgtrade.auction.repositories.TransacaoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired StateMachineFactory<TransacacaoState, TransacacaoEvents> stateMachineFactory;
    @Autowired StateMachine<TransacacaoState, TransacacaoEvents> stateMachine;
    @Autowired TransacaoRepository transacaoRepository;
    @Autowired RabbitTemplate rabbitTemplate;

    public void processEventTransacao(TransacaoDto transacaoDto, TransacacaoEvents events){
        // CRIA A MÁQUINA DE ESTADO
        Transacao transacao = new Transacao(transacaoDto.playerTransacaoRequest().sendPlayer(), transacaoDto.playerTransacaoRequest().receiverPlayer(),
                transacaoDto.inventoryTransacaoRequest().itemId(), transacaoDto.playerTransacaoRequest().coins(), transacaoDto.state());
        stateMachine = stateMachineFactory.getStateMachine(transacao.getUuid());

        // USADO PARA SINCRONIZAR OS ESTADOS DA MÁQUINA COM A ENTIDADE
        if (stateMachine.getState() == null){
            stateMachine.getStateMachineAccessor().doWithAllRegions(accessor ->{
                accessor.resetStateMachineReactively(new DefaultStateMachineContext<>(transacao.getState(), null, null, null)).subscribe();
            });

            stateMachine.startReactively().subscribe();
        }
    }



}
