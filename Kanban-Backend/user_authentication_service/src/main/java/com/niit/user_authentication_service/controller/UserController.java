package com.niit.user_authentication_service.controller;

import com.niit.user_authentication_service.domain.User;
import com.niit.user_authentication_service.exception.UserAlreadyExitException;
import com.niit.user_authentication_service.exception.UserNotFoundException;
import com.niit.user_authentication_service.service.SecurityTokenGenerator;
import com.niit.user_authentication_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/authdata")
@RestController

public class UserController {
    private ResponseEntity responseEntity;
    @Autowired
    private  UserService userService;
    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExitException {

        try {
            responseEntity = new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);

        } catch (UserAlreadyExitException exception) {
            throw new UserAlreadyExitException();
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map = null;
        try{
            User user1 = userService.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
            if(user1.getEmailId().equals(user.getEmailId())){
                map = securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("forgotpassword/{emailId}")
    public ResponseEntity<?> forgot(@PathVariable String emailId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(userService.forgotPassword(emailId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
