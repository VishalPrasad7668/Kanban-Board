package com.niit.task_service.service;

import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.TaskNotFoundException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.user_service.domain.User;
import java.util.List;

public interface TaskService {
    User addTaskForUser(String emailId,Task task) throws Exception;
    User deleteTaskForUser(String emailId,String taskId) throws UserNotFoundException,TaskNotFoundException;
    User deleteProgressTask(String emailId,String taskId) throws UserNotFoundException,TaskNotFoundException;
    User deleteCompletedTask(String emailId,String taskId) throws UserNotFoundException,TaskNotFoundException;
    User deleteArchivedTask(String emailId,String taskId) throws UserNotFoundException,TaskNotFoundException;
    List<Task> getAllTasksForUser(String emailId) throws UserNotFoundException;
    List<Task> getAllInProgressTasksForUser(String emailId) throws UserNotFoundException;
    List<Task> getAllCompletedTasksForUser(String emailId) throws UserNotFoundException;
    List<Task> getAllArchievedTasksForUser(String emailId) throws UserNotFoundException;
    User updateTaskForUser(String emailId,Task task) throws UserNotFoundException;

    Task getTaskOfUser(String emailId,String taskId) throws UserNotFoundException;
    User moveTask(String emailId,String taskId) throws UserNotFoundException, TaskNotFoundException;
    User moveTaskfrominProgressToToDO(String emailId,String taskId) throws UserNotFoundException, TaskNotFoundException;
    User moveTaskFromInProgressToCompleted(String emailId,String taskId) throws UserNotFoundException, TaskNotFoundException;
    User moveTaskFromCompletedToArchive(String emailId,String taskId) throws UserNotFoundException, TaskNotFoundException;
}
