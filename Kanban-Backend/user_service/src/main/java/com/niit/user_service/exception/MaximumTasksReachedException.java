package com.niit.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Maximum no of tasks has been added")
public class MaximumTasksReachedException extends Exception{
}
