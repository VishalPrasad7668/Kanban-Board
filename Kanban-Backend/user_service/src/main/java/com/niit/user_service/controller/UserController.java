package com.niit.user_service.controller;

import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.user_service.domain.User;
import com.niit.user_service.exception.UserAlreadyExistsException;
import com.niit.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/userdata")

public class UserController {

    private ResponseEntity responseEntity;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            user.setTaskList(new ArrayList<>());
            user.setTaskList1(new ArrayList<>());
            user.setCompleted(new ArrayList<>());
            user.setArchive(new ArrayList<>());
//            List<String> fixedSizeList = Arrays.asList(new String[100]);
            responseEntity = new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException exception){
            throw new UserAlreadyExistsException();
        }catch(Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PutMapping("/user/{emailId}")
    public ResponseEntity<?> updateUserDetails(@PathVariable String emailId, @RequestBody User user) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(userService.updateUser(emailId,user),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/specificuser/{emailId}")
    public ResponseEntity<?> getSpecificUser(@PathVariable String emailId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(userService.getSpecificUser(emailId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("task/{emailId}")
    public ResponseEntity<?> addTaskForUser(@PathVariable String emailId, @RequestBody Task task) throws TaskAlreadyExistException, UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(userService.addTaskForUser(emailId,task),HttpStatus.CREATED);
        } catch (TaskAlreadyExistException exception) {
            throw new TaskAlreadyExistException();
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
