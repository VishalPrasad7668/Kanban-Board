package com.niit.user_service.domain;

import com.niit.task_service.domain.Task;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Project {
    @Id
    private String projectId;
    private String projectName;
    private List<Task> taskList;

    public Project() {
    }

    public Project(String projectId, String projectName, List<Task> taskList) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.taskList = taskList;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
