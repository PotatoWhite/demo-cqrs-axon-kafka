package me.potato.cqrs.sampleaxonkafka.events;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.springframework.util.Assert;

@Getter
@Setter
@ToString
public class BaseEvents<T> {

    public BaseEvents(T id) {
        Assert.notNull(id, "Id must be not null");
        this.id = id;
    }

    @AggregateIdentifier
    private T id;

}
