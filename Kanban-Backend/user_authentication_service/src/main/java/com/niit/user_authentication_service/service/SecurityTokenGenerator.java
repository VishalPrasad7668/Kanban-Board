package com.niit.user_authentication_service.service;

import com.niit.user_authentication_service.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);

}
