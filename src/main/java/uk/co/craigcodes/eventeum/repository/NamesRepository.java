package uk.co.craigcodes.eventeum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import uk.co.craigcodes.eventeum.model.Name;

import java.util.List;

@Repository
public interface NamesRepository extends MongoRepository<Name, String> {

    List<Name> findByFirstNameStartingWith(String startingWith);

    List<Name> findBySurname(String surname);
}
