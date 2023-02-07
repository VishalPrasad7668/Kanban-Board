import { Component, OnInit } from '@angular/core';
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { AddTaskComponent } from '../add-task/add-task.component';
import { TaskServiceService } from '../services/task-service.service';
import { Router } from '@angular/router';
import { DisplayTaskComponent } from '../display-task/display-task.component';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-drag',
  templateUrl: './drag.component.html',
  styleUrls: ['./drag.component.css'],
})
export class DragComponent implements OnInit {

  userData:any


  constructor(private dialog: MatDialog, private taskService: TaskServiceService,private userService:UserServiceService, private route: Router) { }
  ngOnInit(): void {
    this.getAllTasks();
    this.getAllProgressTasks();
    this.getAllArchivedTasks();
    this.getAllCompletedTasks();
    this.userService.getSpecificUser().subscribe(
      data => {
        console.log(data)
        this.userData = data; 
      }
    )
  }

  todo: any

  done:any;

  inProgress: any;

  archive:any;

  drop(event: CdkDragDrop<any>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.container.data[0]);
    }
    // console.log(event.previousIndex)
    // console.log(event.currentIndex)
    // console.log(event.previousContainer.data)
    // console.log(event.container.data[0].taskId)
    
  }

  getAllTasks() {
    this.taskService.getAllTasks().subscribe(
      response => {
        console.log(response);
        this.todo = response;
      }
    )
  }
  
  getAllProgressTasks(){
    this.taskService.getAllProgressTasks().subscribe(
      response => {
        console.log(response)
        this.inProgress = response
      }
    )
  }
  
  getAllCompletedTasks(){
    this.taskService.getAllCompletedTasks().subscribe(
      response => {
        console.log(response)
        this.done = response
      }
    )
  }

  getAllArchivedTasks(){
    this.taskService.getAllArchievedTasks().subscribe(
      response => {
        console.log(response)
        this.archive = response
      }
    )
  }

  deleteTask(taskId: any) {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
          this.taskService.deleteTask(taskId).subscribe(
            response=>{
              console.log(response)
              // alert('Task Deleted Successfully!!');
              this.getAllTasks();
              
            }
          )
  
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
        // location.reload();
      }
    })
    
  }

  deleteProgressTask(taskId:any){
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
          this.taskService.deleteProgressTask(taskId).subscribe(
            response=>{
              console.log(response)
              // alert('Task Deleted Successfully!!');
              this.getAllProgressTasks();
              
            }
          )
  
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
        // location.reload();
      }
    })
  }

  deleteCompletedTask(taskId:any){
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
          this.taskService.deleteCompletedTask(taskId).subscribe(
            response=>{
              console.log(response)
              // alert('Task Deleted Successfully!!');
              this.getAllCompletedTasks();
              
            }
          )
  
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
        // location.reload();
      }
    })
  }

  deleteArchivedTask(taskId:any){
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
          this.taskService.deleteArchivedTasks(taskId).subscribe(
            response=>{
              console.log(response)
              // alert('Task Deleted Successfully!!');
              this.getAllArchivedTasks();
              
            }
          )
  
        Swal.fire(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
        // location.reload();
      }
    })
  }

  openDialog(): void {
    this.dialog.open(AddTaskComponent);

  }
  onLogout(){
    localStorage.removeItem("emailId");
    localStorage.removeItem("jwt");
    Swal.fire(
      'Log Out!',
      'User logged out Successfully!!',
      'success'
    )
    this.route.navigate(['login'])
  }
  onClick(){
    this.route.navigateByUrl("allusers")
  }
  submit(){
    // Swal.fire(
    //   'Congrats!',
    //   'You have completed this task!!',
    //   'success'
    // )
  }
  onInProgress(taskId:string){
      this.taskService.moveTaskFromToDo(taskId).subscribe(
        response => {
          console.log(response)
          location.reload();
        }
      )
  }
  onToDo(taskId:string){
    this.taskService.moveTaskFromProgressToToDo(taskId).subscribe(
      reponse => {
        console.log(reponse)
        location.reload();
      }
    )
    
  }
  onCompleted(taskId:string){
    // this.taskService.moveTaskFromProgressToCompleted(taskId).subscribe(
    //   response => {
    //     console.log(response)
    //     location.reload()
    //   }
    // )
    Swal.fire({
      title: 'Are you sure?',
      text: "You have completed this task?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, move it!'
    }).then((result) => {
      location.reload();
      if (result.isConfirmed) {
        location.reload();
        this.taskService.moveTaskFromProgressToCompleted(taskId).subscribe(
          response => {
            console.log(response)
            location.reload()
          }
        )
  
        Swal.fire(
          'Moved!',
          'Your task has been moved to completed.',
          'success'
        )
        // location.reload();
      }
    })
  }
  onArchive(taskId:string){
    this.taskService.moveTaskFromCompletedToArchive(taskId).subscribe(
      response => {
        console.log(response)
        location.reload()
      }
    )
  }
  openTaskDetails(item:any){
    const dialogRef = this.dialog.open(DisplayTaskComponent,{
      width:'500px',
      data:{
        title : item.taskTitle,
        id : item.taskId,
        description : item.taskDescription,
        deadline : item.taskDeadline,
        priority : item.taskPriority,
        assign: item.assignee

      }
    });
    
  }
}
