package com.jbv.ps.services.contracts;

import com.jbv.ps.models.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    List<UserDto> listUsers();
    Optional<UserDto> getUserById(UUID id);

    UserDto saveNewUser(UserDto userDto);

    void updateUserById(UUID id, UserDto userDto);

    void deleteUserById(UUID id);

    void updateUserPatchById(UUID id, UserDto userDto);
}
