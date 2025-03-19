package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.User;
import br.com.gravitech.condonews.domain.utils.StringConstants;
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
        log.info(StringConstants.Log.STARTING_METHOD, "findAllUsers");
        var result = pageMapper.toResponseDto(userRepository.findAll(pageable));
        log.info(StringConstants.Log.ENDING_METHOD, "findAllUsers");
        return result;
    }

    @Override
    public UserDto findUserById(UUID id) {
        log.info(StringConstants.Log.User.FINDING_USER, id);
        var result = userMapper.toDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
        log.info(StringConstants.Log.ENDING_METHOD, "findUserById");
        return result;
    }

    @Override
    public User findByUsername(String username) {
        log.info(StringConstants.Log.User.FINDING_USER, username);
        var result = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        log.info(StringConstants.Log.ENDING_METHOD, "findByUsername");
        return result;
    }

    @Override
    public UserDto createUser(UserDto user) {
        log.info(StringConstants.Log.User.CREATING_USER, user.username());
        User savedUser = userRepository.save(userMapper.toEntity(user));
        var result = userMapper.toDto(savedUser);
        log.info(StringConstants.Log.ENDING_METHOD, "createUser");
        return result;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        log.info(StringConstants.Log.User.UPDATING_USER, user.id());
        User entity = userRepository.findById(user.id()).orElseThrow(UserNotFoundException::new);
        userMapper.merge(user, entity);
        var result = userMapper.toDto(userRepository.save(entity));
        log.info(StringConstants.Log.ENDING_METHOD, "updateUser");
        return result;
    }

    @Override
    public void deleteUser(UUID id) {
        log.info(StringConstants.Log.User.DELETING_USER, id);
        UserDto user = findUserById(id);
        userRepository.deleteById(user.id());
        log.info(StringConstants.Log.ENDING_METHOD, "deleteUser");
    }
}
