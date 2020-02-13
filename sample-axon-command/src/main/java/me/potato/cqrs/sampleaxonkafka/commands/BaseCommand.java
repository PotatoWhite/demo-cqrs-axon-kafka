package me.potato.cqrs.sampleaxonkafka.commands;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.springframework.util.Assert;

@Getter
@Setter
@ToString
public class BaseCommand<T> {
    @TargetAggregateIdentifier
    private String id;

    public BaseCommand(String id) {
        Assert.notNull(id, "Id must be not null");
        this.id = id;
    }
}
