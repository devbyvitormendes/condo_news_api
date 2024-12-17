package br.com.gravitech.condonews.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class UtilMapper {

    @Named("idQualifier")
    public UUID idQualifier(UUID id) {
        if (Objects.nonNull(id)) {
            return id;
        } else {
            return UUID.randomUUID();
        }
    }
}
