package br.com.gravitech.condonews.mapper;

import br.com.gravitech.condonews.domain.Resident;
import br.com.gravitech.condonews.dto.ResidentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public abstract class ResidentMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "idQualifier")
    public abstract Resident toEntity(ResidentDto dto);

    public abstract ResidentDto toDto(Resident entity);
}