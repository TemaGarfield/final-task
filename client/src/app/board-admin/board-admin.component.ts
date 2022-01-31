import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { User } from '../models/user.model';
import { AdminService } from '../_services/admin.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {
  users?: User[];
  errorMessage: string = '';

  constructor(private adminService: AdminService, private route: ActivatedRoute, private router: Router, private token: TokenStorageService, private mainComponent: AppComponent) { }

  ngOnInit(): void {
    this.retrieveUsers();
  }

  retrieveUsers(): void {
    this.adminService.getAll().subscribe(
      data => {
        this.users = data;
      },
      error => {
        this.errorMessage = error.message;
        console.log(error);
      }
    )
  }

  deleteUser(userId: any): void {
    this.adminService.delete(userId).subscribe(
      response => {
        if (userId == this.token.getUser().id) {
          this.router.navigate(['/home']).then(() => {
            this.mainComponent.logout();
          });
        } else {
          this.ngOnInit();
        }

      },
      error => {
        this.errorMessage = error.message;
        console.log(error);
      }
    );
  }

  addUser(): void {
    this.router.navigate(['/admin/add-user']);
  }

  editUser(user: User):void {
    this.adminService.setUserToEdit(user);
    this.router.navigate(['/admin/edit-user']);
  }
}
