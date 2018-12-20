package uk.co.craigcodes.eventeum.converter;

import net.consensys.eventeum.dto.event.ContractEventDetails;
import net.consensys.eventeum.dto.event.parameter.EventParameter;
import org.springframework.stereotype.Component;
import uk.co.craigcodes.eventeum.model.Name;

import java.math.BigInteger;
import java.util.List;

@Component
public class ContractEventToNamedAccountConverter implements NameConverter<ContractEventDetails> {

    @Override
    public Name convert(ContractEventDetails input) {
        final Name namedAccount = new Name();

        final List<EventParameter> eventParameters = input.getNonIndexedParameters();

        namedAccount.setId(new BigInteger(eventParameters.get(0).getValueString()));
        namedAccount.setFirstName(eventParameters.get(1).getValueString());
        namedAccount.setSurname(eventParameters.get(2).getValueString());

        return namedAccount;
    }
}
