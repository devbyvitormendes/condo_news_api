package br.com.gravitech.condonews.mapper;

import br.com.gravitech.condonews.domain.News;
import br.com.gravitech.condonews.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public abstract class NewsMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "idQualifier")
    public abstract News toEntity(NewsDto dto);

    public abstract NewsDto toDto(News entity);

    public abstract void merge(NewsDto dto, @MappingTarget News entity);

    public abstract List<NewsDto> toDtoList(List<News> entityList);
}
