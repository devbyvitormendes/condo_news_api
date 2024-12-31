package br.com.gravitech.condonews.mapper;

import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "id", source = "id", qualifiedByName = "idQualifier")
    @Mapping(target = "password", source = "password", qualifiedByName = "passwordQualifier")
    public abstract User toEntity(UserDto dto);

    public abstract UserDto toDto(User entity);

    public abstract void merge(UserDto user, @MappingTarget User entity);

    public abstract List<UserDto> toDtoList(List<User> entityList);

    @Named("passwordQualifier")
    public String passwordQualifier(String password) {
        return passwordEncoder.encode(password);
    }
}
