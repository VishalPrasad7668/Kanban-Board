import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import { UserDetailsDialogComponent } from '../user-details-dialog/user-details-dialog.component';

@Component({
  selector: 'app-alluser-details',
  templateUrl: './alluser-details.component.html',
  styleUrls: ['./alluser-details.component.css']
})
export class AlluserDetailsComponent implements OnInit{

  constructor(private userService:UserServiceService,private route:Router,private dialog:MatDialog){}

  userData:any
  page:any;
  myUserData:any;

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      response => {
        console.log(response)
        this.userData = response;
      }
    )
    this.userService.getSpecificUser().subscribe(
      data => {
        console.log(data)
        this.myUserData = data; 
      }
    )
  }
  onLogout(){
    localStorage.removeItem("emailId");
    localStorage.removeItem("jwt");
    this.route.navigate(['login'])
  }

  onDashboard(){
    this.route.navigateByUrl("dashboard")
  }

  onClick(email:any){
    // this.dialog.open(AssignTaskComponent)
    this.route.navigateByUrl("assign")
    localStorage.setItem('email', email)
  }

  openUserDetails(user:any){
    const dialogRef = this.dialog.open(UserDetailsDialogComponent,{
      width:'500px',
      data:{
        name : user.fullName,
        email : user.emailId,
        phoneNo : user.phoneNumber,
        address : user.address,
        dept : user.department,
        position : user.position

      }
    });
  }
  
}
