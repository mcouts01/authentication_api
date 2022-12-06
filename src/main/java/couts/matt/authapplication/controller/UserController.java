package couts.matt.authapplication.controller;

import couts.matt.authapplication.data.dto.UserDTO;
import couts.matt.authapplication.entity.User;
import couts.matt.authapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserDTO newUser) {
        log.info("Saving user");
        String authID = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User createdUser = userService.saveUser(newUser, authID);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return null;
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/get/{userID}")
    public ResponseEntity<User> getUserByID(@PathVariable Integer userID) {
        try {
            return new ResponseEntity<>(userService.getUserByID(userID), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/get/authID")
    public ResponseEntity<User> getUserByAuthID() {
        String authID = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            return new ResponseEntity<>(userService.getUserByAuthID(authID), HttpStatus.OK);
        } catch(Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
