package com.niit.user_service.service;

import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.user_service.domain.User;
import com.niit.user_service.exception.MaximumTasksReachedException;
import com.niit.user_service.exception.UserAlreadyExistsException;
import com.niit.user_service.proxy.UserProxy;
import com.niit.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProxy userProxy;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        User user1 = userRepository.save(user);
        if(!(user1.getEmailId().isEmpty())){
            ResponseEntity responseEntity = userProxy.saveUser(user);
            System.out.println(responseEntity.getBody());
        }
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmailId());
        msg.setSubject("Registration");
        msg.setText("You have registered successfully. You can now login!!");
        javaMailSender.send(msg);
        return user1;
    }

    @Override
    public User updateUser(String emailId,User user) throws UserNotFoundException {
        if (userRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user1 = userRepository.findById(emailId).get();
        if(user1.getEmailId().equalsIgnoreCase(user.getEmailId())){
            user1.setFullName(user.getFullName());
            user1.setPhoneNumber(user.getPhoneNumber());
            user1.setAddress(user.getAddress());
            user1.setDepartment(user.getDepartment());
            user1.setPosition(user.getPosition());
        }
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);
        msg.setSubject("Updated Information");
        msg.setText("Your information have been updated as per your request!!");
        javaMailSender.send(msg);
        return userRepository.save(user1);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getSpecificUser(String emailId) throws UserNotFoundException {
        if (userRepository.findById(emailId).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        return user;
    }

    @Override
    public User addTaskForUser(String emailId, Task task) throws Exception {
        if(userRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        List<Task> taskList = user.getTaskList();
        if(taskList.size()<=3){
            System.out.println("This is inside if == "+taskList.size());
            Iterator<Task> taskIterator = taskList.iterator();
            while(taskIterator.hasNext()){
                if(taskIterator.next().getTaskId().equalsIgnoreCase(task.getTaskId())){
                    throw new TaskAlreadyExistException();
                }
            }
            if(user.getTaskList() == null){
                user.setTaskList(Arrays.asList(task));
            }
            else{
                taskList.add(task);
                user.setTaskList(taskList);
            }
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(emailId);
            msg.setSubject("Task Assigned");
            msg.setText("A task has been assigned to you. Please login to know further details!!");
            javaMailSender.send(msg);
            return userRepository.save(user);
        }
       throw new MaximumTasksReachedException();
    }
}
