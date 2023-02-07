package com.niit.task_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.task_service.domain.Task;
import com.niit.task_service.exception.TaskAlreadyExistException;
import com.niit.task_service.exception.TaskNotFoundException;
import com.niit.task_service.exception.UserNotFoundException;
import com.niit.task_service.service.TaskService;
import com.niit.user_service.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;
    @InjectMocks
    private TaskController taskController;

    private Task task,task1;
    private List<Task> taskList;
    private User user;

    @BeforeEach
    public void setUp(){
        task = new Task("a1","Test","Testing","22-12-2022","High","assignee1");
        task1 = new Task("a1","Test1","Testing1","12-12-2022","Low","assignee2");
        taskList = Arrays.asList(task);

        user = new User("Bhuvan","bhuvan@123","test",888999988,"Ranchi","HR","Jr. Manager",taskList);
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }
    @AfterEach
    void tearDown(){
        user = null;
        task = null;
    }

    private static String jsonToString(final Object object) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(object);
            result = jsonContent;
        } catch(JsonProcessingException exception) {
            result = "JSON processing error";
        }

        return result;
    }

    @Test
    public void addTaskSuccess() throws Exception {
        when(taskService.addTaskForUser(any(),any())).thenReturn(user);
        mockMvc.perform(post("/kanbantask/task/add/bhuvan@123").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).addTaskForUser(any(),any());
    }

    @Test
    public void addTaskFailure() throws Exception{
        when(taskService.addTaskForUser(any(),any())).thenThrow(TaskAlreadyExistException.class);
        mockMvc.perform(post("/kanbantask/task/add/bhuvan@123").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).addTaskForUser(any(),any());

    }

    @Test
    public void getAllTaskSuccess() throws Exception {
        mockMvc.perform(get("/kanbantask/task/get/bhuvan@123").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).getAllTasksForUser(any());
    }

    @Test
    public void getAllTaskFailure() throws Exception {
        when(taskService.getAllTasksForUser(any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(get("/kanbantask/task/get/bhuvan@123").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).getAllTasksForUser(any());
    }

    @Test
    public void deleteTaskSuccess() throws Exception {
        when(taskService.deleteTaskForUser(any(),any())).thenReturn(user);
        mockMvc.perform(delete("/kanbantask/task/delete/bhuvan@123/a1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).deleteTaskForUser(any(),any());
    }

    @Test
    public void deleteTaskFailure() throws Exception {
        when(taskService.deleteTaskForUser(any(),any())).thenThrow(TaskNotFoundException.class);
        mockMvc.perform(delete("/kanbantask/task/delete/bhuvan@123/a1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).deleteTaskForUser(any(),any());
    }

    @Test
    public void updateTaskSuccess() throws Exception {
        when(taskService.updateTaskForUser(any(),any())).thenReturn(user);
        mockMvc.perform(put("/kanbantask/task/update/bhuvan@123").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task1)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).updateTaskForUser(any(),any());
    }

    @Test
    public void updateTaskFailure() throws Exception {
        when(taskService.updateTaskForUser(any(),any())).thenThrow(UserNotFoundException.class);
        mockMvc.perform(put("/kanbantask/task/update/bhuvan@123").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(task1)))
                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
        verify(taskService, times(1)).updateTaskForUser(any(),any());
    }
}
