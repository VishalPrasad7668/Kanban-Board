package com.niit.user_authentication_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User Not Found ")
public class UserNotFoundException extends Exception{
}
