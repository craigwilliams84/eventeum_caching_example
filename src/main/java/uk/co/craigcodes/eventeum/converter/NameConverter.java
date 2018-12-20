package uk.co.craigcodes.eventeum.converter;

import uk.co.craigcodes.eventeum.model.Name;

public interface NameConverter<T> {

    Name convert(T input);
}
