import { Component, DoCheck, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})

export class UserDetailsComponent implements OnInit{

  constructor(private userService:UserServiceService,private route:Router){
     
  }
 

  userData:any

  ngOnInit(): void {
    this.userService.getSpecificUser().subscribe(
      data => {
        console.log(data)
        this.userData = data; 
      }
    )
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

  onDashboard(){
    this.route.navigateByUrl("dashboard")
  }
}
