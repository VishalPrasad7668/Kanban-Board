import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private httpClient:HttpClient) { }

  url:String = "http://localhost:9000/userdata"

  addUser(userObject:any){
    return  this.httpClient.post(this.url+"/register",userObject);

  }

  getAllUsers(){
    return this.httpClient.get(this.url+"/users");
  }

  getSpecificUser(){
    return this.httpClient.get(this.url+"/specificuser/"+localStorage.getItem('emailId'));
  }

  updateUser(user:any){
    return  this.httpClient.put(this.url+"/user/"+localStorage.getItem('emailId'),user)
  }

  addTask(emailId:any,taskObject:any){
    return this.httpClient.post(this.url+"/task/"+emailId,taskObject);
  }
  
  url2:String = "http://localhost:9000/authdata"

  login(object:any){
    return this.httpClient.post(this.url2+"/login",object);
  }

  forgotPassword(email:String){
    return this.httpClient.get(this.url2+"/forgotpassword/"+email)
  }

}
