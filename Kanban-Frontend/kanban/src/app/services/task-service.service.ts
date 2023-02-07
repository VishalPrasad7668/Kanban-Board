import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private httpClient: HttpClient) { }

  url: String = "http://localhost:9000/kanbantask"

  getAllTasks() {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }
    return this.httpClient.get(this.url + "/task/get/" + localStorage.getItem('emailId'),requestToken);
  }

  getAllProgressTasks(){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }
    return this.httpClient.get(this.url + "/task/getprogress/" + localStorage.getItem('emailId'),requestToken);
  }

  getAllCompletedTasks(){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }
    return this.httpClient.get(this.url + "/task/getcompleted/" + localStorage.getItem('emailId'),requestToken);
  }

  getAllArchievedTasks(){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }
    return this.httpClient.get(this.url + "/task/getarchived/" + localStorage.getItem('emailId'),requestToken);
  }

  addTask(taskObject: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }
    return this.httpClient.post(this.url + "/task/add/" + localStorage.getItem('emailId'), taskObject, requestToken);
  }

  deleteTask(taskId: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }

    return this.httpClient.delete(this.url + "/task/delete/" + localStorage.getItem('emailId') + '/' + taskId, requestToken);
  }

  deleteProgressTask(taskId:any){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }

    return this.httpClient.delete(this.url + "/task/deleteprogress/" + localStorage.getItem('emailId') + '/' + taskId, requestToken);
  }

  deleteCompletedTask(taskId:any){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }

    return this.httpClient.delete(this.url + "/task/deletecompleted/" + localStorage.getItem('emailId') + '/' + taskId, requestToken);
  }

  deleteArchivedTasks(taskId:any){
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders }

    return this.httpClient.delete(this.url + "/task/deletearchived/" + localStorage.getItem('emailId') + '/' + taskId, requestToken);
  }

  updateTask(taskObj: any) {
    let httpHeaders = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });

    let requestToken = { headers: httpHeaders,responseType:"text" as "json" }

    return this.httpClient.put(this.url + "/task/update/" + localStorage.getItem('emailId'), taskObj, requestToken);
  }

  getSpecificTask(taskId:any){
    let httpHeaders=new HttpHeaders({
      'Authorization' : 'Bearer' +localStorage.getItem('jwt')
     });
  
     let requestToken={ headers : httpHeaders }
    return this.httpClient.get(this.url+"/getTask/"+localStorage.getItem('emailId')+"/"+taskId,requestToken);
  }

  moveTaskFromToDo(taskId:any){
    return this.httpClient.post(this.url+"/movetask/"+localStorage.getItem('emailId')+"/"+taskId,null);
  }

  moveTaskFromProgressToToDo(taskId:any){
    return this.httpClient.post(this.url+"/movetasktodo/"+localStorage.getItem('emailId')+"/"+taskId,null);
  }

  moveTaskFromProgressToCompleted(taskId:any){
    return this.httpClient.post(this.url+"/movetaskcompleted/"+localStorage.getItem('emailId')+"/"+taskId,null);
  }

  moveTaskFromCompletedToArchive(taskId:any){
    return this.httpClient.post(this.url+"/movetaskarchive/"+localStorage.getItem('emailId')+"/"+taskId,null);
  }
}
