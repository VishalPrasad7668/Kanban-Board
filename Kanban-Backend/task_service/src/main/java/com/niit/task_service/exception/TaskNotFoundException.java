package com.niit.task_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Task you are searching for is not Present..")
public class TaskNotFoundException extends Exception{
}
