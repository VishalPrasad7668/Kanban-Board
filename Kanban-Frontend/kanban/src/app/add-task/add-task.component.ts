import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TaskServiceService } from '../services/task-service.service';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent {

  maxDate: Date = new Date();
  random:any = Math.floor(Math.random() * 100);
  constructor(private fb: FormBuilder, private route: Router, private taskService: TaskServiceService,private dialog:MatDialog,private userService:UserServiceService) {
     this.maxDate.setDate(this.maxDate.getDate() + 1);
     this.taskId?.setValue(this.random);
     
    }

  ngOnInit(): void {
    
  }
  

  addForm = this.fb.group({
    taskId: [''],
    taskTitle: ['', [Validators.required, Validators.minLength(10)]],
    assignee: ['', [Validators.required, Validators.pattern(/^[A-Z].*/)]],
    taskDescription: ['', [Validators.required, Validators.minLength(20)]],
    taskPriority: [''],
    taskDeadline: ['', Validators.required]

  })

  get taskId() { return this.addForm.get("taskId") }

  get taskTitle() { return this.addForm.get("taskTitle") }

  get assignee() { return this.addForm.get("assignee") }

  get taskDescription() { return this.addForm.get("taskDescription") }

  get taskPriority() { return this.addForm.get("taskPriority") }

  get taskDeadline() { return this.addForm.get("taskDeadline") }

  
  onSubmit() {
    console.log(this.addForm.value);
    this.taskService.addTask(this.addForm.value).subscribe(
      response => {
        console.log(response);
        alert('Task added successfully');
        this.dialog.closeAll();
        location.reload();
      }
    )
    

  }
}
