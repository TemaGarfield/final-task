import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Collection } from '../models/collection.model';
import { User } from '../models/user.model';
import { AdminService } from '../_services/admin.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserRoleService } from '../_services/user-role.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  collections?: Collection[];
  users?: User[];
  errorMessage: string = '';
  buttonText: string = '';
  show: boolean = false;
  isAdmin: boolean = false;


  form: any = {
    name: null,
    topic: null,
    description: null,
    userId: null,
  }

  currentUser: any;

  topics?: any[];

  constructor(private userRoleService: UserRoleService, private tokenStorageService: TokenStorageService, private adminService: AdminService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorageService.getUser().token != undefined) {
      this.currentUser = this.tokenStorageService.getUser();
      this.isAdmin = this.currentUser.roles.includes('ROLE_ADMIN');
      this.buttonText = this.show ? 'Hide' : 'Add';
    }
    this.retrieveCollections();
  }

  retrieveCollections(): void {
    this.userRoleService.getAll().subscribe(
      data => {
        this.collections = data;
      },
      error => {
        this.errorMessage = error.message;
        console.log(error);
      }
    );
  }

  deleteCollection(collectionId: any): void {
    this.userRoleService.delete(collectionId).subscribe(
      response => {
        this.ngOnInit();
      },
      error => {
        this.errorMessage = error.message;
        console.log(error);
      }
    );
  }

  onSubmit(f: NgForm): void {
    this.show = !this.show;

    let {userId, name, topic, description} = this.form;

    if (userId == null) {
      userId = this.currentUser.id;
    }

    this.userRoleService.saveCollection(userId, name, topic, description).subscribe(
      data => {
        this.form.name = null;
        this.form.description = null;
        this.form.userId = null;
        this.form.topic = null;
        this.ngOnInit();
      }
    );
  }

  showForm(): void {
    this.show = !this.show;
    this.buttonText = this.show ? 'Hide' : 'Add';

    if (this.isAdmin) {
      this.adminService.getAll().subscribe(
        data => {
          this.users = data;
          console.log(this.users);
        }
      );
    }

    this.userRoleService.getAllTopics().subscribe(
      data => {
        this.topics = data;
      }
    );  
  }

  editCollection(collection: Collection): void {
    this.userRoleService.setCollection(collection);
    this.router.navigate(['/user/edit-collection']);
  }

  direction: string[] = ['asc', 'asc', 'asc', 'asc', 'asc'];
  sort(sortType: string, sortNumber: number): void {
    const multiplier = (this.direction[sortNumber] == 'asc') ? 1 : -1;

    if (sortType == 'topic') {
      this.collections?.sort((a, b) => {
        return (((a as any)[sortType].topic ? ((a as any)[sortType].topic) : '') > ((b as any)[sortType].topic ? (b as any)[sortType].topic : '')) ? 1 * multiplier : -1 * multiplier
      });
    } else if (sortType == 'user') {
      this.collections?.sort((a, b) => {
        return (((a as any)[sortType].username ? ((a as any)[sortType].username) : '') > ((b as any)[sortType].username ? (b as any)[sortType].username : '')) ? 1 * multiplier : -1 * multiplier
      });
    } else {
      this.collections?.sort((a, b) => (((a as any)[sortType] ? ((a as any)[sortType]) : '') > ((b as any)[sortType] ? (b as any)[sortType] : '')) ? 1 * multiplier : -1 * multiplier);
    }

    this.direction[sortNumber] = (this.direction[sortNumber] == 'asc') ? 'desc' : 'asc';
  }

}
