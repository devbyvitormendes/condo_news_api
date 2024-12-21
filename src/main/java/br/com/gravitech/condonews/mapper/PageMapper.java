package br.com.gravitech.condonews.mapper;

import br.com.gravitech.condonews.dto.page.PageResponseDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public abstract class PageMapper {

    public abstract PageResponseDto toResponseDto(Page page);
}
