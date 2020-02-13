package me.potato.cqrs.sampleaxonkafka.aggregates;


import lombok.extern.slf4j.Slf4j;
import me.potato.cqrs.sampleaxonkafka.aggregates.exceptions.InsufficientBalanceException;
import me.potato.cqrs.sampleaxonkafka.commands.AccountCreateCommand;
import me.potato.cqrs.sampleaxonkafka.commands.DepositMoneyCommand;
import me.potato.cqrs.sampleaxonkafka.commands.WithdrawMoneyCommand;
import me.potato.cqrs.sampleaxonkafka.events.AccountCreatedEvent;
import me.potato.cqrs.sampleaxonkafka.events.MoneyDepositedEvent;
import me.potato.cqrs.sampleaxonkafka.events.MoneyWithdrawnEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Slf4j
@Aggregate
public class AccountAggregate {
    private final EventSourcingRepository<AccountAggregate> repository;

    @AggregateIdentifier
    private String id;

    private BigDecimal balance;
    private String accountHolder;
    private String accHolderName;

    @CommandHandler
    public AccountAggregate(AccountCreateCommand cmd, EventSourcingRepository<AccountAggregate> repository) {
        this.repository = repository;

        Assert.hasLength(cmd.getAccountHolder(), "Account holder must have a name");
        Assert.hasLength(cmd.getId(), "Account id must have length greater then Zero");
        AggregateLifecycle.apply(new AccountCreatedEvent(cmd.getId(), cmd.getAccountHolder(), cmd.getAccountHolderName(), BigDecimal.ZERO));
    }

    @EventSourcingHandler
    public void handle(AccountCreatedEvent event) {
        log.info(event.toString());
        this.id = event.getId();
        this.balance = event.getBalance();
        this.accountHolder = event.getAccHolderName();
        this.accHolderName = event.getAccHolderName();
    }

    @CommandHandler
    public void on(DepositMoneyCommand command) {
        log.info(command.toString());

        Assert.isTrue(command.getAmount().compareTo(BigDecimal.ZERO) > 0, "Amount should be a positive number");
        AggregateLifecycle.apply(new MoneyDepositedEvent(command.getId(), command.getAccountHolder(), command.getAmount()));

    }

    @EventSourcingHandler
    public void on(MoneyDepositedEvent event) {
        log.info(event.toString());

        this.balance = this.balance.add(event.getAmount());
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command) {
        log.info(command.toString());

        Assert.isTrue(command.getAmount().compareTo(BigDecimal.ZERO) > 0, "Amount should be a positive number");

        if (this.balance.compareTo(command.getAmount()) < 0)
            throw new InsufficientBalanceException(String.format("Insufficient balance. Trying to withdraw : {}, but current balance si : {}", command.getAmount(), this.balance));

        AggregateLifecycle.apply(new MoneyWithdrawnEvent(command.getId(), command.getAccountHolder(), command.getAmount()));
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawnEvent event) {
        log.info(event.toString());

        this.balance = this.balance.subtract(event.getAmount());
    }



}
