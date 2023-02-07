package com.niit.task_service.controller;

import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.TaskNotFoundException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.task_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/kanbantask/")

public class TaskController {

    private final TaskService taskService;
    private ResponseEntity responseEntity ;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/task/add/{emailId}")
    public ResponseEntity<?> addTask(@PathVariable String emailId,@RequestBody Task task) throws TaskAlreadyExistException, UserNotFoundException {

        try{
            responseEntity = new ResponseEntity<>(taskService.addTaskForUser(emailId,task),HttpStatus.CREATED);
        } catch (TaskAlreadyExistException exception) {
            throw new TaskAlreadyExistException();
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/task/delete/{emailId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable String emailId,@PathVariable String taskId) throws TaskNotFoundException, UserNotFoundException {

        try{
            responseEntity = new ResponseEntity<>(taskService.deleteTaskForUser(emailId,taskId),HttpStatus.OK);
        } catch (TaskNotFoundException exception) {
            throw new TaskNotFoundException();
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @DeleteMapping("/task/deleteprogress/{emailId}/{taskId}")
    public ResponseEntity<?> deleteProgressTask(@PathVariable String emailId,@PathVariable String taskId) throws TaskNotFoundException, UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.deleteProgressTask(emailId,taskId),HttpStatus.OK);
        }catch (TaskNotFoundException exception) {
            throw new TaskNotFoundException();
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @DeleteMapping("/task/deletecompleted/{emailId}/{taskId}")
    public ResponseEntity<?> deleteCompletedTask(@PathVariable String emailId,@PathVariable String taskId) throws TaskNotFoundException, UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.deleteCompletedTask(emailId,taskId),HttpStatus.OK);
        }catch (TaskNotFoundException exception) {
            throw new TaskNotFoundException();
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @DeleteMapping("/task/deletearchived/{emailId}/{taskId}")
    public ResponseEntity<?> deleteArchivedTask(@PathVariable String emailId,@PathVariable String taskId) throws TaskNotFoundException, UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.deleteArchivedTask(emailId,taskId),HttpStatus.OK);
        }catch (TaskNotFoundException exception) {
            throw new TaskNotFoundException();
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("task/get/{emailId}")
    public ResponseEntity<?> getAllTheTasks(@PathVariable String emailId) throws UserNotFoundException {

        try {
            responseEntity = new ResponseEntity(taskService.getAllTasksForUser(emailId),HttpStatus.OK);
        }catch(UserNotFoundException exception){
            throw new UserNotFoundException();
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("task/getprogress/{emailId}")
    public ResponseEntity<?> getAllProgressTasks(@PathVariable String emailId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.getAllInProgressTasksForUser(emailId),HttpStatus.OK);
        }catch(UserNotFoundException exception){
            throw new UserNotFoundException();
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("task/getcompleted/{emailId}")
    public ResponseEntity<?> getAllCompletedTasks(@PathVariable String emailId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.getAllCompletedTasksForUser(emailId),HttpStatus.OK);
        }catch(UserNotFoundException exception){
            throw new UserNotFoundException();
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @GetMapping("task/getarchived/{emailId}")
    public ResponseEntity<?> getAllArchievedTasks(@PathVariable String emailId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.getAllArchievedTasksForUser(emailId),HttpStatus.OK);
        }catch(UserNotFoundException exception){
            throw new UserNotFoundException();
        } catch (Exception exception) {
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PutMapping("task/update/{emailId}")
    public ResponseEntity<?> updationOfTask(@PathVariable String emailId,@RequestBody Task task) throws TaskNotFoundException, UserNotFoundException {

        try {
            responseEntity = new ResponseEntity(taskService.updateTaskForUser(emailId,task),HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            throw  new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("getTask/{emailId}/{taskId}")
    public ResponseEntity<?> getSpecificTask(@PathVariable String emailId,@PathVariable String taskId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.getTaskOfUser(emailId,taskId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PostMapping("movetask/{emailId}/{taskId}")
    public ResponseEntity<?> moveTask(@PathVariable String emailId, @PathVariable String taskId) throws UserNotFoundException, TaskNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.moveTask(emailId,taskId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (TaskNotFoundException exception){
            throw new TaskNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PostMapping("movetasktodo/{emailId}/{taskId}")
    public ResponseEntity<?> moveTasktodo(@PathVariable String emailId,@PathVariable String taskId) throws UserNotFoundException, TaskNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.moveTaskfrominProgressToToDO(emailId,taskId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (TaskNotFoundException exception){
            throw new TaskNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PostMapping("movetaskcompleted/{emailId}/{taskId}")
    public ResponseEntity<?> moveTaskCompleted(@PathVariable String emailId,@PathVariable String taskId) throws UserNotFoundException, TaskNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.moveTaskFromInProgressToCompleted(emailId,taskId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (TaskNotFoundException exception){
            throw new TaskNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
    @PostMapping("movetaskarchive/{emailId}/{taskId}")
    public ResponseEntity<?> moveTaskArchive(@PathVariable String emailId,@PathVariable String taskId) throws UserNotFoundException, TaskNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(taskService.moveTaskFromCompletedToArchive(emailId,taskId),HttpStatus.OK);
        }catch (UserNotFoundException exception){
            throw new UserNotFoundException();
        }catch (TaskNotFoundException exception){
            throw new TaskNotFoundException();
        }catch (Exception exception){
            responseEntity = new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
