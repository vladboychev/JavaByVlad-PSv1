package com.jbv.ps.controllers;

import com.jbv.ps.exceptions.NotFoundException;
import com.jbv.ps.models.MemberDto;
import com.jbv.ps.services.contracts.MemberService;
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
public class MemberController {

    public static final String PATH_TO_USERS = "/api/v1/users";
    public static final String PATH_TO_USERS_ID = PATH_TO_USERS + "/{id}";

    private final MemberService memberService;

    @GetMapping(PATH_TO_USERS)
    public List<MemberDto> listUsers() {

        log.debug("List Users in MemberController was called.");

        return memberService.listUsers();
    }

    @GetMapping(PATH_TO_USERS_ID)
    public MemberDto getUserById(@PathVariable UUID id) {

        log.debug("Get Member by id in MemberController was called. Id: " + id.toString());

        return memberService.getUserById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping(PATH_TO_USERS)
    public ResponseEntity handlePost(@RequestBody MemberDto memberDto) {

        MemberDto savedMemberDto = memberService.saveNewUser(memberDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location","/api/v1/users/" + savedMemberDto.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(PATH_TO_USERS_ID)
    public ResponseEntity updateUserById(@PathVariable UUID id, @RequestBody MemberDto memberDto) {

        memberService.updateUserById(id, memberDto);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(PATH_TO_USERS_ID)
    public ResponseEntity deleteUserById(@PathVariable("id") UUID id) {
        memberService.deleteUserById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(PATH_TO_USERS_ID)
    public ResponseEntity updateUserPatchById(@PathVariable("id") UUID id, @RequestBody MemberDto memberDto) {
        memberService.updateUserPatchById(id, memberDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
