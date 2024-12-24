package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.dto.UserDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.exception.user.UserNotFoundException;
import br.com.gravitech.condonews.mapper.PageMapper;
import br.com.gravitech.condonews.mapper.UserMapper;
import br.com.gravitech.condonews.repository.UserRepository;
import br.com.gravitech.condonews.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PageMapper pageMapper;
    private final UserRepository userRepository;


    @Override
    public PageResponseDto findAllUsers(Pageable pageable) {
        log.info("Starting findAllUsers method {}", pageable);
        return pageMapper.toResponseDto(userRepository.findAll(pageable));
    }

    @Override
    public UserDto findUserById(UUID id) {
        log.info("Starting findUserById method {}", id);
        return userMapper.toDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDto createUser(UserDto user) {
        log.info("Starting createUser method {}", user);
        User savedUser = userRepository.save(userMapper.toEntity(user));
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user) {
        log.info("Starting updateUser method {}", user);
        User entity = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException::new);
        userMapper.merge(user, entity);
        return userMapper.toDto(userRepository.save(entity));
    }

    @Override
    public void deleteUser(UUID id) {
        log.info("Starting deleteUser method {}", id);
        UserDto user = findUserById(id);
        userRepository.deleteById(user.getId());
    }
}
