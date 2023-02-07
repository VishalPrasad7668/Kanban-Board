package com.niit.user_authentication_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "User Already Exits ")
public class UserAlreadyExitException extends Exception {

}
