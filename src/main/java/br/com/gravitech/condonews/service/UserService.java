package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.dto.UserDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    PageResponseDto findAllUsers(Pageable pageable);

    UserDto findUserById(UUID id);

    User findByUsername(String username);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user);

    void deleteUser(UUID id);
}
