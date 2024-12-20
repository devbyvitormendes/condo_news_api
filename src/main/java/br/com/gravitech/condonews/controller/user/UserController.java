package br.com.gravitech.condonews.controller.user;

import br.com.gravitech.condonews.dto.UserDto;
import br.com.gravitech.condonews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserApi {

   private final UserService userService;

    @Override
    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable UUID id) {
        return userService.findUserById(id);
    }

    @Override
    @PostMapping
    public UserDto createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @Override
    @PutMapping
    public UserDto updateUser(@RequestBody UserDto user) {
        return userService.updateUser(user);
    }

    @Override
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}

