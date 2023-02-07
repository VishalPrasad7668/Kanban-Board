package com.niit.task_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Task is already Present!!")
public class TaskAlreadyExistException extends Exception{
}
