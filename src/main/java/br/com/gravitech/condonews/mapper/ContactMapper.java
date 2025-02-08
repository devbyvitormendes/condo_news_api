package br.com.gravitech.condonews.mapper;

import br.com.gravitech.condonews.domain.Contact;
import br.com.gravitech.condonews.domain.enums.ContactTypeEnum;
import br.com.gravitech.condonews.dto.ContactDto;
import org.mapstruct.*;


import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public abstract class ContactMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "idQualifier")
    @Mapping(target = "type", source = "type", qualifiedByName = "typeToEnumQualifier")
    public abstract Contact toEntity(ContactDto dto);

    @Mapping(target = "type", source = "type", qualifiedByName = "typeToStringQualifier")
    public abstract ContactDto toDto(Contact entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void merge(ContactDto dto, @MappingTarget Contact entity);

    public abstract List<ContactDto> toDtoList(List<Contact> entityList);

    @Named("typeToEnumQualifier")
    public ContactTypeEnum typeToEnumQualifier(String type) {
        return ContactTypeEnum.getByDescription(type);
    }

    @Named("typeToStringQualifier")
    public String typeToStringQualifier(ContactTypeEnum type) {
        return type.getDescription();
    }

}
