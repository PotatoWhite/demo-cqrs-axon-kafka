package me.potato.cqrs.sampleaxonkafka.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class MoneyDepositedEvent extends BaseEvents<String>{
    private String accountHolder;
    private BigDecimal amount;

    public MoneyDepositedEvent(String id, String accountHolder, BigDecimal amount) {
        super(id);
        this.accountHolder = accountHolder;
        this.amount = amount;
    }
}
