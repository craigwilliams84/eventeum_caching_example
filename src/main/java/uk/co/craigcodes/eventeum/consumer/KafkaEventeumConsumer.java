package uk.co.craigcodes.eventeum.consumer;

import net.consensys.eventeum.dto.event.ContractEventDetails;
import net.consensys.eventeum.dto.message.EventeumMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import uk.co.craigcodes.eventeum.service.NamesRegistryService;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Component
public class KafkaEventeumConsumer {

    private static final String NAME_ADDED_EVENT = "NameAdded";

    private NamesRegistryService namesRegistryService;

    private Map<String, Consumer<ContractEventDetails>> consumers;

    @Autowired
    public KafkaEventeumConsumer(NamesRegistryService namesRegistryService) {
        this.namesRegistryService = namesRegistryService;

        consumers = new HashMap<>();

        consumers.put(NAME_ADDED_EVENT, (contractEventDetails -> {
            namesRegistryService.storeFromContractEvent(contractEventDetails);
        }));
    }

    @KafkaListener(topics = "contract-events", groupId = "eventeumExample")
    public void consumeContractEvent(EventeumMessage<ContractEventDetails> message) {
        final ContractEventDetails contractEventDetails = message.getDetails();
        final String eventName = contractEventDetails.getName();

        if (consumers.containsKey(eventName)) {
            consumers.get(eventName).accept(contractEventDetails);
        }
    }
}
