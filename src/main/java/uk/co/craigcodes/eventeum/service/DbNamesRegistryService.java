package uk.co.craigcodes.eventeum.service;

import net.consensys.eventeum.dto.event.ContractEventDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.craigcodes.eventeum.converter.NameConverter;
import uk.co.craigcodes.eventeum.model.Name;
import uk.co.craigcodes.eventeum.repository.NamesRepository;

import java.util.List;

@Service
public class DbNamesRegistryService implements NamesRegistryService {

    private NamesRepository repository;
    private NameConverter<ContractEventDetails> converter;

    @Autowired
    public DbNamesRegistryService(NamesRepository repository,
                                  NameConverter<ContractEventDetails> converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public void storeFromContractEvent(ContractEventDetails contractEvent) {
        final Name namedAccount = converter.convert(contractEvent);

        repository.save(namedAccount);
    }

    @Override
    public List<Name> searchBySurname(String surname) {
        return repository.findBySurname(surname);
    }

    @Override
    public List<Name> searchByFirstNameStartingWith(String startsWith) {
        return repository.findByFirstNameStartingWith(startsWith);
    }
}
