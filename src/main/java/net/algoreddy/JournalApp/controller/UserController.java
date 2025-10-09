package net.algoreddy.JournalApp.controller;

import net.algoreddy.JournalApp.Service.UserService;
import net.algoreddy.JournalApp.entity.User;
import net.algoreddy.JournalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        // securitycontextholder . . . --> it make sure that the user is authenticated by checking the password &user avaliable in the database using the hasing
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        if (userInDb != null) {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?>deleteUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByuserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


//    @PutMapping
//    public ResponseEntity<?> updateUser(@RequestBody User user) {
//        // Get the currently authenticated user
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String loggedInUserName = authentication.getName();
//
//        // Find user in DB
//        User userInDb = userService.findByUserName(loggedInUserName);
//        if (userInDb == null) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//
//        // Update only allowed fields
//        if (    !user.getUserName().isBlank()) {
//            userInDb.setUserName(user.getUserName());
//        }
//
//        if (user.getPassword() != null && !user.getPassword().isBlank()) {
//            // Re-encode the password before saving!
//            userInDb.setPassword(UserService.passwordEncoder.encode(user.getPassword()));
//        }
//
//        // Save updated user
//        userService.saveEntry(userInDb);
//
//        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
//    }

}
