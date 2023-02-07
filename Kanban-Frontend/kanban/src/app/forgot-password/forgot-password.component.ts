import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {

  constructor(private userService:UserServiceService){}
  ngOnInit(): void {
    
  }
  email:String = "";
  onSubmit(){
    this.userService.forgotPassword(this.email).subscribe(
      response => {
        console.log(response)
        Swal.fire(
          'Congrats!',
          'Password have been sent to your email!!',
          'success'
        )
        location.reload()
      },(err) => {
        console.log(err)
        
        // alert("invalid credentials")
        Swal.fire({
          title: 'Invalid Credentials, Please try again!!',
          text: 'If you are not registered, Register first!!',
          icon: 'error',
          confirmButtonText: 'OK'
        })
        location.reload()
      }
    )
  }
  

}
