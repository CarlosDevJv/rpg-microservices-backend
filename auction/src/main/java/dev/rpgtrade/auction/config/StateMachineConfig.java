package dev.rpgtrade.auction.config;

import dev.rpgtrade.auction.model.events.TransacacaoEvents;
import dev.rpgtrade.auction.model.state.TransacacaoState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<TransacacaoState, TransacacaoEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<TransacacaoState, TransacacaoEvents> states) throws Exception{
        states
                .withStates()
                .initial(TransacacaoState.NEW)
                .states(EnumSet.allOf(TransacacaoState.class))
                .end(TransacacaoState.COMPLETED)
                .end(TransacacaoState.CANCELLED);
    }


    @Override
    public void configure(StateMachineTransitionConfigurer<TransacacaoState, TransacacaoEvents> transitionConfigurer) throws Exception {
        transitionConfigurer
                .withExternal()
                .source(TransacacaoState.NEW).target(TransacacaoState.VALIDATED).event(TransacacaoEvents.VALIDATE)
                .and()
                .withExternal()
                .source(TransacacaoState.VALIDATED).target(TransacacaoState.PAID).event(TransacacaoEvents.PAY)
                .and()
                .withExternal()
                .source(TransacacaoState.PAID).target(TransacacaoState.COMPLETED).event(TransacacaoEvents.COMPLETE)
                .and()
                .withExternal()
                .source(TransacacaoState.NEW).target(TransacacaoState.CANCELLED).event(TransacacaoEvents.CANCEL);
    }



}
