import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  ngOnInit(): void {

  }
  constructor(private fb:FormBuilder,private route: Router,private userService:UserServiceService, private dialog:MatDialog) { }
 
  loginForm = this.fb.group({
    emailId: ['', [Validators.required,Validators.email]],
    password: ['',Validators.required]
  })
  get emailId() { return this.loginForm.get("emailId") }
  get password() { return this.loginForm.get("password") }
 
  hide = true;
  
  data:any
  decodeData:any
  email:any
  public onSubmit() {
    this.userService.login(this.loginForm.value).subscribe(
      response => {
        console.log(response);
        this.data = response;

        console.log(this.data.token);
        this.decodeData=jwt_decode(this.data.token);
        console.log(this.decodeData);

        this.email = this.decodeData.sub;
        console.log(this.email);
        localStorage.setItem('emailId', this.email);
        localStorage.setItem('jwt', this.data.token);
        // alert('Login success');
        Swal.fire(
          'Congrats!',
          'Login Successfull!!',
          'success'
        )

         this.route.navigateByUrl('/dashboard');
      },(err) => {
        console.log(err)
        
        // alert("invalid credentials")
        Swal.fire({
          title: 'Error!',
          text: 'Invalid Credentials, Please try again!!',
          icon: 'error',
          confirmButtonText: 'OK'
        })
      }
    )
    
  }

  home(){
    this.route.navigateByUrl("home")
  }
  onForgot(){
    // location.reload()
    this.dialog.open(ForgotPasswordComponent);
  }


}
