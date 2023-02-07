import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit{
  constructor(private router:Router) { }

  ngOnInit(): void {
    
  }

  onSubmit(){
     Swal.fire(
      'Thank You!',
      'We got your request, We will reach you soon!!',
      'success'
    )
    this.router.navigateByUrl("home")
  }
}
