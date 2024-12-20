package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDto> findAllUsers();

    UserDto findUserById(UUID id);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user);

    void deleteUser(UUID id);
}
