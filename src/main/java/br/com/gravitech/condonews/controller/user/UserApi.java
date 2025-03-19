package br.com.gravitech.condonews.controller.user;

import br.com.gravitech.condonews.dto.UserDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "User", description = "User related operations")
public interface UserApi {

    @Operation(summary = "Get Users", description = "Get a list of all Users.")
    PageResponseDto getUsers(Pageable pageable);

    @Operation(summary = "Get User by ID", description = "Get a User by UUID.")
    UserDto getUser(@Parameter(description = "User's UUID", required = true) @PathVariable UUID id);

    @Operation(summary = "Create User", description = "Create a new User in database.")
    UserDto createUser(@RequestBody UserDto user);

    @Operation(summary = "Update User", description = "Update User data in database.")
    UserDto updateUser(@Parameter(description = "User data", required = true) @RequestBody UserDto user);

    @Operation(summary = "Delete User", description = "Delete a User in database.")
    void deleteUser(@Parameter(description = "User's UUID", required = true) @PathVariable UUID id);
}
