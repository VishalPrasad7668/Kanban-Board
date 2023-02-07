package com.niit.task_service.domain;

import org.springframework.data.annotation.Id;



public class Task {
    @Id
    private String taskId;
    private String taskTitle;
    private String taskDescription;
    private String taskDeadline;
    private String taskPriority;
    private String assignee;

    public Task() {
    }

    public Task(String taskId, String taskTitle, String taskDescription, String taskDeadline, String taskPriority, String assignee) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.taskDeadline = taskDeadline;
        this.taskPriority = taskPriority;
        this.assignee = assignee;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(String taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskDeadline='" + taskDeadline + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", assignee='" + assignee + '\'' +
                '}';
    }
}
