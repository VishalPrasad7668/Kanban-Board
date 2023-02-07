package com.niit.user_authentication_service.service;

import com.niit.user_authentication_service.domain.User;
import com.niit.user_authentication_service.exception.UserAlreadyExitException;
import com.niit.user_authentication_service.exception.UserNotFoundException;
import com.niit.user_authentication_service.repositroy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private JavaMailSender javaMailSender;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExitException {
        if (userRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExitException();
        }

        return userRepository.save(user);
    }

    @Override
    public User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException {
      User login =userRepository.findByEmailIdAndPassword(emailId,password);
      if (login==null){
          throw new UserNotFoundException();
      }
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);
        msg.setSubject("Login");
        msg.setText("You have successfully logged in.");
        javaMailSender.send(msg);
        return login;
    }

    @Override
    public User forgotPassword(String emailId) throws UserNotFoundException {
        if(userRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(emailId).get();
        String password = user.getPassword();
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);
        msg.setSubject("Password");
        msg.setText("Your login password is => " + password);
        javaMailSender.send(msg);
        return user;
    }

}
