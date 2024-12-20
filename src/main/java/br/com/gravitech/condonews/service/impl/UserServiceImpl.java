package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.dto.UserDto;
import br.com.gravitech.condonews.exception.user.UserNotFoundException;
import br.com.gravitech.condonews.mapper.UserMapper;
import br.com.gravitech.condonews.repository.UserRepository;
import br.com.gravitech.condonews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Override
    public List<UserDto> findAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserDto findUserById(UUID id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto createUser(UserDto user) {
        User savedUser = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User entity = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        userMapper.merge(user, entity);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public void deleteUser(UUID id) {
        UserDto user = findUserById(id);
        userRepository.deleteById(user.getId());
    }
}
