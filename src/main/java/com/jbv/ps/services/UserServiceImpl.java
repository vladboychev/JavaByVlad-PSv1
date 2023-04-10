package com.jbv.ps.services;

import com.jbv.ps.models.UserDto;
import com.jbv.ps.services.contracts.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final Map<UUID, UserDto> userMap;

    public UserServiceImpl() {

        this.userMap = new HashMap<>();

        UserDto userDtoOne = UserDto.builder()
                .id(UUID.randomUUID())
                .username("firstUser")
                .firstName("First")
                .lastName("User")
                .email("fu@ps.jbv")
                .password("firstPass")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UserDto userDtoTwo = UserDto.builder()
                .id(UUID.randomUUID())
                .username("secondUser")
                .firstName("Second")
                .lastName("User")
                .email("su@ps.jbv")
                .password("secondPass")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UserDto userDtoThree = UserDto.builder()
                .id(UUID.randomUUID())
                .username("thirdUser")
                .firstName("Third")
                .lastName("User")
                .email("tu@ps.jbv")
                .password("thirdPass")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userMap.put(userDtoOne.getId(), userDtoOne);
        userMap.put(userDtoTwo.getId(), userDtoTwo);
        userMap.put(userDtoThree.getId(), userDtoThree);
    }

    @Override
    public List<UserDto> listUsers() {
        log.debug("List Users in UserServiceImpl was called.");
        return new ArrayList<>(userMap.values());
    }

    @Override
    public Optional<UserDto> getUserById(UUID id) {
        log.debug("Get User by id in UserServiceImpl was called.");
        return Optional.of(userMap.get(id));
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {

        UserDto savedUserDto = UserDto.builder()
                .id(UUID.randomUUID())
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userMap.put(savedUserDto.getId(), savedUserDto);

        return savedUserDto;
    }

    @Override
    public void updateUserById(UUID id, UserDto userDto) {
        UserDto userDtoToUpdate = userMap.get(id);
        userDtoToUpdate.setUsername(userDto.getUsername());
        userDtoToUpdate.setEmail(userDto.getEmail());
        userDtoToUpdate.setFirstName(userDto.getFirstName());
        userDtoToUpdate.setLastName(userDto.getLastName());
        userDtoToUpdate.setPassword(userDto.getPassword());
        userDtoToUpdate.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    public void deleteUserById(UUID id) {
        userMap.remove(id);
    }

    @Override
    public void updateUserPatchById(UUID id, UserDto userDto) {
        UserDto userDtoToPatch = userMap.get(id);

        if (StringUtils.hasText(userDto.getUsername())) {
            userDtoToPatch.setUsername(userDto.getUsername());
        }
        if (StringUtils.hasText(userDto.getEmail())) {
            userDtoToPatch.setEmail(userDto.getEmail());
        }
        if (StringUtils.hasText(userDto.getFirstName())) {
            userDtoToPatch.setFirstName(userDto.getFirstName());
        }
        if (StringUtils.hasText(userDto.getLastName())) {
            userDtoToPatch.setLastName(userDto.getLastName());
        }
        if (StringUtils.hasText(userDto.getPassword())) {
            userDtoToPatch.setPassword(userDto.getPassword());
        }
    }
}
