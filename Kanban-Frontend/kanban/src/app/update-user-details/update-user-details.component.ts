import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-update-user-details',
  templateUrl: './update-user-details.component.html',
  styleUrls: ['./update-user-details.component.css']
})
export class UpdateUserDetailsComponent implements OnInit {
  constructor(private userService: UserServiceService, private route: Router) { }

  user: any

  ngOnInit(): void {
    this.userService.getSpecificUser().subscribe(
      response => {
        console.log(response)
        this.user = response
      }
    )

  }

  onSubmit() {
    // this.userService.updateUser(this.user).subscribe(
    //   response => {
    //     console.log(response)
    //     alert("User Updated Successfully")
    //     this.route.navigateByUrl('user-details')
    //   },(err) => {
    //     console.log(err)

    //     alert("invalid entry!! Please check the data and enter again")
    //   }
    // )
    Swal.fire({
      title: 'Are you sure?',
      text: "Your profile information will be updated!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, Save!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.userService.updateUser(this.user).subscribe(
          response => {
            console.log(response)
            // alert("User Updated Successfully")
            location.reload()
          }, (err) => {
            console.log(err)

            // alert("invalid entry!! Please check the data and enter again")
          }
        )
        Swal.fire(
          'Saved !',
          'Your information has been saved successfully.',
          'success'
        )
        this.route.navigateByUrl('user-details')
        
      }

    }
    )
  }
}
