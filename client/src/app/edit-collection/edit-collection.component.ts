import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Collection } from '../models/collection.model';
import { User } from '../models/user.model';
import { AdminService } from '../_services/admin.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserRoleService } from '../_services/user-role.service';

@Component({
  selector: 'app-edit-collection',
  templateUrl: './edit-collection.component.html',
  styleUrls: ['./edit-collection.component.css']
})
export class EditCollectionComponent implements OnInit {

  errorMessage: string = '';
  isAdmin: boolean = false;
  topics?: any[];
  users?: User[];
  selectedUser?: User;

  form: any = {
    collectionId: this.userRoleService.getCollection()?.id,
    name: this.userRoleService.getCollection()?.name,
    topic: null,
    description: this.userRoleService.getCollection()?.description,
    user: null,
  }

  constructor(private userRoleService: UserRoleService, private tokenStorageService: TokenStorageService, private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorageService.getUser != undefined) {
      this.isAdmin = (this.tokenStorageService.getUser().roles.includes("ROLE_ADMIN"));
    }

    this.userRoleService.getAllTopics().subscribe(
      data => {
        this.topics = data;
      },
      error => {
        this.errorMessage = error.message;
      }
    );

    this.adminService.getAll().subscribe(
      data => {
        this.users = data;
      },
      error => {
        this.errorMessage = error.message;
      }
    );

    if (this.form.user == null) {
      this.form.user = this.tokenStorageService.getUser();
    }
  }

  onSubmit(): void {

    const {collectionId, name, topic, description, user} = this.form;

    this.userRoleService.editCollection(collectionId, name, topic, description, user.id).subscribe(
      response => {
        this.router.navigate(['/user']);
      }
    );
  }
}
