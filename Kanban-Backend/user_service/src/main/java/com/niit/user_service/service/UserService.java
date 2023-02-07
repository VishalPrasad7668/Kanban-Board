package com.niit.user_service.service;

import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.user_service.domain.User;
import com.niit.user_service.exception.UserAlreadyExistsException;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws UserAlreadyExistsException;

    User updateUser(String emailId,User user) throws UserNotFoundException;

    List<User> getAllUsers();

    User getSpecificUser(String emailId) throws UserNotFoundException;

    User addTaskForUser(String emailId, Task task) throws Exception;
}
