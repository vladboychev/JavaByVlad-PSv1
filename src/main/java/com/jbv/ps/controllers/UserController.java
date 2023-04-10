package com.jbv.ps.controllers;

import com.jbv.ps.exceptions.NotFoundException;
import com.jbv.ps.models.UserDto;
import com.jbv.ps.services.contracts.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    public static final String PATH_TO_USERS = "/api/v1/users";
    public static final String PATH_TO_USERS_ID = PATH_TO_USERS + "/{id}";

    private final UserService userService;

    @GetMapping(PATH_TO_USERS)
    public List<UserDto> listUsers() {

        log.debug("List Users in UserController was called.");

        return userService.listUsers();
    }

    @GetMapping(PATH_TO_USERS_ID)
    public UserDto getUserById(@PathVariable UUID id) {

        log.debug("Get User by id in UserController was called. Id: " + id.toString());

        return userService.getUserById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(PATH_TO_USERS)
    public ResponseEntity handlePost(@RequestBody UserDto userDto) {

        UserDto savedUserDto = userService.saveNewUser(userDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location","/api/v1/users/" + savedUserDto.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(PATH_TO_USERS_ID)
    public ResponseEntity updateUserById(@PathVariable UUID id, @RequestBody UserDto userDto) {

        userService.updateUserById(id, userDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(PATH_TO_USERS_ID)
    public ResponseEntity deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(PATH_TO_USERS_ID)
    public ResponseEntity updateUserPatchById(@PathVariable("id") UUID id, @RequestBody UserDto userDto) {
        userService.updateUserPatchById(id, userDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
