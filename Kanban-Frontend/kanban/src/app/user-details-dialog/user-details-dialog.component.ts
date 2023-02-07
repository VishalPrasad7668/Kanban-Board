import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AlluserDetailsComponent } from '../alluser-details/alluser-details.component';

@Component({
  selector: 'app-user-details-dialog',
  templateUrl: './user-details-dialog.component.html',
  styleUrls: ['./user-details-dialog.component.css']
})
export class UserDetailsDialogComponent implements OnInit{
  receivedData:any;
  constructor(public dialogRef:MatDialogRef<AlluserDetailsComponent>, @Inject(MAT_DIALOG_DATA) public data:any) { 
    this.receivedData = data;
  }

  ngOnInit(): void {
  }
}
