import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../_services/admin.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  errorMessage = '';

  form: any = {
    username: null,
    email: null,
    password: null,
  };

  roles?: any[];

  isSuccessful = true;

  constructor(private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
      this.adminService.getAllRoles().subscribe(
        data => {
          this.roles = data;
          this.roles?.map(function (current) {
            current.checked = false;
          });
          
        },
        error => {
          this.errorMessage = error.message;
          console.log(error);
        }
      )
  }

  onSubmit(): void {
    const { username, email, password } = this.form;

    const checkedOptions = this.roles?.filter(x => x.checked);
    const selectedValues: any[] | undefined = checkedOptions?.map(x => x.name);
    console.log(selectedValues);

    this.adminService.addUser(username, email, password, selectedValues).subscribe(
      data => {
        console.log(data);
        this.router.navigate(['/admin']);
      },
      err => {
        this.errorMessage = err.error.message;
      }
    );
  }
}
