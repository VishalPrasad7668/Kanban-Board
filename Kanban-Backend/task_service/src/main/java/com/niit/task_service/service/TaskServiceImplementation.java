package com.niit.task_service.service;

import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.TaskNotFoundException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.task_service.repository.TaskRepository;
import com.niit.user_service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public User addTaskForUser(String emailId, Task task) throws Exception {
        if(taskRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> taskList = user.getTaskList();
        List<Task> taskList1 = user.getTaskList1();

        if(taskList.size()<=3){
            System.out.println("This is inside if  " +taskList.size());
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
                taskList1.add(task);
                user.setTaskList(taskList);
            }
            return taskRepository.save(user);
        }
        throw new Exception();
    }

    @Override
    public User deleteTaskForUser(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> tasks = user.getTaskList();
        result = tasks.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        user.setTaskList(tasks);
        return taskRepository.save(user);
    }

    @Override
    public User deleteProgressTask(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> tasks = user.getTaskList1();
        result = tasks.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        user.setTaskList1(tasks);
        return taskRepository.save(user);
    }

    @Override
    public User deleteCompletedTask(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> tasks = user.getCompleted();
        result = tasks.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        user.setCompleted(tasks);
        return taskRepository.save(user);
    }

    @Override
    public User deleteArchivedTask(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> tasks = user.getArchive();
        result = tasks.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        user.setArchive(tasks);
        return taskRepository.save(user);
    }

    @Override
    public List<Task> getAllTasksForUser(String emailId) throws UserNotFoundException {
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return taskRepository.findById(emailId).get().getTaskList();
    }

    @Override
    public List<Task> getAllInProgressTasksForUser(String emailId) throws UserNotFoundException {
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return taskRepository.findById(emailId).get().getTaskList1();
    }

    @Override
    public List<Task> getAllCompletedTasksForUser(String emailId) throws UserNotFoundException {
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return taskRepository.findById(emailId).get().getCompleted();
    }

    @Override
    public List<Task> getAllArchievedTasksForUser(String emailId) throws UserNotFoundException {
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return taskRepository.findById(emailId).get().getArchive();
    }

    @Override
    public User updateTaskForUser(String emailId, Task task) throws UserNotFoundException {
        if(taskRepository.findById(emailId).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> tasks = user.getTaskList();
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()){
            Task task1 = iterator.next();
            if(task1.getTaskId().equalsIgnoreCase(task.getTaskId())){

                task1.setTaskTitle(task.getTaskTitle());
                task1.setTaskDescription(task.getTaskDescription());
                task1.setTaskDeadline(task.getTaskDeadline());
                task1.setTaskPriority(task.getTaskPriority());
                task1.setAssignee(task.getAssignee());
            }
        }
        user.setTaskList(tasks);
        return taskRepository.save(user);
    }

    @Override
    public Task getTaskOfUser(String emailId, String taskId) throws UserNotFoundException {
        if (taskRepository.findById(emailId).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> taskList = user.getTaskList();
        for (Task task:taskList) {
            if (task.getTaskId().equalsIgnoreCase(taskId)){
                
                return task;
            }
        }
        return taskList.get(0);
    }

    @Override
    public User moveTask(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if (taskRepository.findById(emailId).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> taskList = user.getTaskList();
        List<Task> taskList1 = user.getTaskList1();
        for (Task task:taskList) {
            if (task.getTaskId().equalsIgnoreCase(taskId)){
                if(user.getTaskList1() == null){
                    user.setTaskList1(Arrays.asList(task));
                }
                else{
                    taskList1.add(task);
                    user.setTaskList1(taskList1);
                }
            }
        }
        result = taskList.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        return taskRepository.save(user);
    }

    @Override
    public User moveTaskfrominProgressToToDO(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if (taskRepository.findById(emailId).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> taskList = user.getTaskList();
        List<Task> taskList1 = user.getTaskList1();
        for (Task task:taskList1) {
            if (task.getTaskId().equalsIgnoreCase(taskId)){
                if(user.getTaskList() == null){
                    user.setTaskList(Arrays.asList(task));
                }
                else{
                    taskList.add(task);
                    user.setTaskList(taskList);
                }
            }
        }
        result = taskList1.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        return taskRepository.save(user);
    }

    @Override
    public User moveTaskFromInProgressToCompleted(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if (taskRepository.findById(emailId).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> taskList1 = user.getTaskList1();
        List<Task> completed = user.getCompleted();
        for (Task task:taskList1){
            if(task.getTaskId().equalsIgnoreCase(taskId)){
                if(user.getCompleted() == null){
                    user.setCompleted(Arrays.asList(task));
                }
                else{
                    completed.add(task);
                    user.setCompleted(completed);
                }
            }
        }
        result = taskList1.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        return taskRepository.save(user);
    }

    @Override
    public User moveTaskFromCompletedToArchive(String emailId, String taskId) throws UserNotFoundException, TaskNotFoundException {
        boolean result;
        if (taskRepository.findById(emailId).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = taskRepository.findById(emailId).get();
        List<Task> completed = user.getCompleted();
        List<Task> archive = user.getArchive();
        for(Task task:completed){
            if(task.getTaskId().equalsIgnoreCase(taskId)){
                if(user.getArchive() == null){
                    user.setArchive(Arrays.asList(task));
                }
                else{
                    archive.add(task);
                    user.setArchive(archive);
                }
            }
        }
        result = completed.removeIf(obj->obj.getTaskId().equalsIgnoreCase(taskId));
        if(!result)
        {
            throw new TaskNotFoundException();
        }
        return taskRepository.save(user);
    }
}
