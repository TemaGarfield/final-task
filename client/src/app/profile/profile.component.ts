import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Collection } from '../models/collection.model';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserRoleService } from '../_services/user-role.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  errorMessage: string ='';
  currentUser: any;
  collections?: Collection[];

  buttonText: string = '';
  show: boolean = false;
  form: any = {
    name: null,
    topic: null,
    description: null,
    userId: null,
  }

  topics?: any[];

  constructor(private token: TokenStorageService, private userRoleService: UserRoleService, private router: Router) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.buttonText = this.show ? 'Hide' : 'Add';
    this.retrieveUserCollection();
  }

  retrieveUserCollection(): void {
    this.userRoleService.getUserCollections(this.currentUser.id).subscribe(
      data => {
        this.collections = data;
      }, 
      error => {
        this.errorMessage = error.message;
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

  onSubmit(): void {
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


    this.userRoleService.getAllTopics().subscribe(
      data => {
        this.topics = data;
      }
    );  
  }

  editCollection(collection: Collection) {
    this.userRoleService.setCollection(collection);
    this.router.navigate(['/user/edit-collection']);
  }

  direction: string[] = ['asc', 'asc', 'asc', 'asc'];
  sort(sortType: string, sortNumber: number): void {
    const multiplier = (this.direction[sortNumber] == 'asc') ? 1 : -1;

    if (sortType == 'topic') {
      this.collections?.sort((a, b) => {
        return (((a as any)[sortType].topic ? ((a as any)[sortType].topic) : '') > ((b as any)[sortType].topic ? (b as any)[sortType].topic : '')) ? 1 * multiplier : -1 * multiplier
      });
    } else {
      this.collections?.sort((a, b) => (((a as any)[sortType] ? ((a as any)[sortType]) : '') > ((b as any)[sortType] ? (b as any)[sortType] : '')) ? 1 * multiplier : -1 * multiplier);
    }

    this.direction[sortNumber] = (this.direction[sortNumber] == 'asc') ? 'desc' : 'asc';
  }
}
