import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DragComponent } from '../drag/drag.component';

@Component({
  selector: 'app-display-task',
  templateUrl: './display-task.component.html',
  styleUrls: ['./display-task.component.css']
})
export class DisplayTaskComponent implements OnInit {
  receivedData: any;

  constructor(public dialogRef: MatDialogRef<DragComponent>, @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log(data);
    this.receivedData = data;
    console.log(this.receivedData)

  }

  ngOnInit(): void {

  }
}
