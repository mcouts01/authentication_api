package couts.matt.authapplication.service;

import couts.matt.authapplication.data.dto.UserDTO;
import couts.matt.authapplication.entity.User;
import couts.matt.authapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(UserDTO userRequest, String authID) {
        log.info("Saving User with name " + userRequest.getFirstName());
        if(this.userRepository.findByAuthID(authID).isEmpty()) {
            User user = User.builder()
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .authID(authID)
                    .build();
            return userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
    }

    public User getUserByID(Integer userID) {
        log.info("Finding User with ID " + userID);
        return userRepository.findByID(userID);
    }

    public User getUserByAuthID(String authID) {
        log.info("Finding User with AuthID " + authID);
        Optional<User> user = userRepository.findByAuthID(authID);
        if(user.isPresent()) {
            return user.get();
        } else {
            log.error("User not found with AuthID " + authID);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
    }
}
