import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  ngOnInit(): void {
    
  }
  constructor(private fb:FormBuilder,private _snackBar: MatSnackBar,private route:Router,private userService:UserServiceService){}

  hide = true;
  hide1 = true;

  registrationForm = this.fb.group({
    fullName: ['', Validators.required],
    emailId: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    confirmPassword: ['', [Validators.required, Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    phoneNumber: ['', [Validators.required, Validators.pattern(/^[6789]\d{9,9}$/)]],
    address: ['', Validators.required],
    department: ['', Validators.required],
    position: ['', Validators.required]
  }, { validators: [this.ConfirmedValidator('password','confirmPassword')] })


  get fullName() { return this.registrationForm.get("fullName") }
  get emailId() { return this.registrationForm.get("emailId") }
  get password() { return this.registrationForm.get("password") }
  get confirmPassword() { return this.registrationForm.get("confirmPassword") }
  get phoneNumber() { return this.registrationForm.get("phoneNumber") }
  get address() { return this.registrationForm.get("address") }
  get department() { return this.registrationForm.get("department") }
  get position() { return this.registrationForm.get("position")}

  
  
  ConfirmedValidator(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
      if (
        matchingControl.errors &&
        !matchingControl.errors['confirmedValidator']
      ) {
        return;
      }
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ confirmedValidator: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  } 

  data:any;
  addUser(){
    console.log(this.registrationForm.value);
    this.userService.addUser(this.registrationForm.value).subscribe(
      response => {
        this.data = response;
        console.log(response);
 
        this._snackBar.open('Congrats, you registered successfully!!', 'Success', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        })

       this.route.navigateByUrl("/login")

      }
    )
  }

  login()
  {
    console.log("log");
    this.route.navigateByUrl('login');
  }
  
  home(){
    this.route.navigateByUrl('home');
  }
}
