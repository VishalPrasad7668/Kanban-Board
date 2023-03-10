import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(private router:Router){}
  ngOnInit(): void {
    
  }

  // log = this.login.logout;
  onLogout(){
    localStorage.removeItem("emailId");
    localStorage.removeItem("jwt");
    this.router.navigate(['login'])
  }
}
