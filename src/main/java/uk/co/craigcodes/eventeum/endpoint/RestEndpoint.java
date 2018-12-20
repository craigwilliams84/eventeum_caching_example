package uk.co.craigcodes.eventeum.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.craigcodes.eventeum.model.Name;
import uk.co.craigcodes.eventeum.service.NamesRegistryService;

import java.util.List;

@RestController
@RequestMapping("name")
public class RestEndpoint {

    private NamesRegistryService namesRegistryService;

    @Autowired
    public RestEndpoint(NamesRegistryService namesRegistryService) {
        this.namesRegistryService = namesRegistryService;
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = "application/json")
    public List<Name> searchNames(@RequestParam(required = false) String firstNameStartsWith,
                                  @RequestParam(required = false) String surname) {
        if (firstNameStartsWith != null) {
            return namesRegistryService.searchByFirstNameStartingWith(firstNameStartsWith);
        }

        if (surname != null) {
            return namesRegistryService.searchBySurname(surname);
        }

        throw new IllegalArgumentException("Search parameter not set");
    }
}