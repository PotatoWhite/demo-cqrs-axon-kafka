package me.potato.cqrs.sampleaxonkafka.events;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoneyDepositedEvent extends BaseEvents<String>{
    private String accountHolder;
    private String amount;

    public MoneyDepositedEvent(String id, String accountHolder, String amount) {
        super(id);
        this.accountHolder = accountHolder;
        this.amount = amount;
    }
}
