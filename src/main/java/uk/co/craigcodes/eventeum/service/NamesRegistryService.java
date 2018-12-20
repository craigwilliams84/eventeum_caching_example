package uk.co.craigcodes.eventeum.service;

import net.consensys.eventeum.dto.event.ContractEventDetails;
import uk.co.craigcodes.eventeum.model.Name;

import java.util.List;

public interface NamesRegistryService {

    void storeFromContractEvent(ContractEventDetails contractEvent);

    List<Name> searchBySurname(String surname);

    List<Name> searchByFirstNameStartingWith(String startsWith);
}
