package br.com.gravitech.condonews.mapper;

import br.com.gravitech.condonews.domain.Condo;
import br.com.gravitech.condonews.dto.CondoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public abstract class CondoMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "idQualifier")
    public abstract Condo toEntity(CondoDto dto);

    public abstract CondoDto toDto(Condo entity);

    public abstract List<CondoDto> toDtoList(List<Condo> entityList);
}
