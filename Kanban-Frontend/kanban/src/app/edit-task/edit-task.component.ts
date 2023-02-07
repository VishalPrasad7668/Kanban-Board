import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { NgToastModule, NgToastService } from 'ng-angular-popup';
import { Task } from '../model/task';
import { TaskServiceService } from '../services/task-service.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {

  maxDate: Date = new Date();

  constructor(private activatedRoute: ActivatedRoute, private taskService: TaskServiceService, private route: Router, private popup: NgToastService) { this.maxDate.setDate(this.maxDate.getDate() + 1); }

  task: Task = {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = params.get("taskId") ?? 1;
      this.taskService.getSpecificTask(id).subscribe(data => {
        console.log(data);
        this.task = data;
        console.log(this.task)
      })
    });
  }

  onSubmit() {

    // this.taskService.updateTask(this.task).subscribe(
    //   response => {
    //     console.log(response);
    //     // // alert('Task Updated Successfully');
    //     // // this.popup.success({detail:"Updation",summary:"Task Updated Successfully",duration:5000})
    //     // Swal.fire(
    //     //   'Updated!',
    //     //   'Task Updated Successfully!!',
    //     //   'success'
    //     // )
    //     this.route.navigateByUrl("dashboard")

    //   }
    // )
    Swal.fire({
      title: 'Are you sure?',
      text: "Task Information will be updated!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Save!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.taskService.updateTask(this.task).subscribe(
          response => {
            console.log(response);
            // // alert('Task Updated Successfully');
            // // this.popup.success({detail:"Updation",summary:"Task Updated Successfully",duration:5000})
            Swal.fire(
              'Updated!',
              'Task Updated Successfully!!',
              'success'
            )
            this.route.navigateByUrl("dashboard")

          }
        )
      }

    }
    )

  }
}
