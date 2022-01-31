import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { User } from '../models/user.model';
import { AdminService } from '../_services/admin.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user?: User;
  userRoles?: any[];
  roles?: any[];
  form: any = {
    username: this.adminService.getUserToEdit()?.username,
    email: this.adminService.getUserToEdit()?.email,
    password: null
  }
  errorMessage: string = '';

  constructor(private adminService: AdminService, private router: Router, private token: TokenStorageService, private mainComponent: AppComponent) { }

  ngOnInit(): void {
    this.user = this.adminService.getUserToEdit();
    this.adminService.getAllRoles().subscribe(
      data => {
        this.roles = data;
        let userRoleNames:string[] = [];
        this.user?.roles?.forEach(role => userRoleNames.push(role.name));
        this.roles?.forEach(role => {
          if (userRoleNames.includes(role.name)){
            role.checked = true;
          } else {
            role.checked = false;
          }
        },
      );
      },
      error => {
        this.errorMessage = error.message;
      }
    ); 
  }

  onSubmit(): void {
    const { username, email, password } = this.form;
    const checkedOptions = this.roles?.filter(role => role.checked);
    const selectedValues: any[] | undefined = checkedOptions?.map(x => x.name);


    this.adminService.editUser(this.user?.id, username, email, password, selectedValues).subscribe(
      data =>  this.router.navigate(['/admin']).then(() => {
        if (this.user?.id == this.token.getUser().id && username != this.token.getUser().username) {
          this.mainComponent.logout();
          this.router.navigate(['/home']);
        }
      })
    );
  }
}
