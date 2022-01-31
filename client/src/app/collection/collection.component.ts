import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Collection } from '../models/collection.model';
import { ItemService } from '../_services/item.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserRoleService } from '../_services/user-role.service';

@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.css']
})
export class CollectionComponent implements OnInit {

  collectionItems?: any[];
  collectionId?: bigint;
  collection?: Collection;
  collectionName?: string;
  isShow: boolean = false;
  isCreator: boolean = false;
  isAdmin: boolean = false;
  isEmpty: boolean = false;
  isAutorized: boolean = false;

  form: any = {
    name: null
  }

  constructor(private userRoleService: UserRoleService, private route: ActivatedRoute, private tokenStorage: TokenStorageService, private router: Router, private itemService: ItemService) { }

  ngOnInit(): void {
    this.isAutorized = (this.tokenStorage.getToken() != null) ? true : false;

    if (this.isAutorized) {
      this.isAdmin = this.tokenStorage.getUser().roles.includes("ROLE_ADMIN");
    }
    this.route.queryParams.subscribe(params => {
      this.collectionId = params['collectionId'];
      this.userRoleService.getCollectionById(this.collectionId).subscribe(
        data => {
          this.collection = data;
          this.collectionItems = this.collection.items;
          this.collectionName = this.collection.name;
          this.isCreator = (this.collection?.user.id == this.tokenStorage.getUser().id) ? true : false;
          this.isEmpty = (this.collectionItems?.length == 0) ? true : false;
        }
      );
    });
  }

  show(): void {
    this.isShow = !this.isShow;
  }

  onSubmit(): void {
    this.isShow = !this.isShow;

    let { name } = this.form;

    this.itemService.saveItem(name, this.collection?.id).subscribe(
      data => {
        this.form.name = null;
        this.ngOnInit();
      }
    );
  }

  deleteItem(id: bigint): void {
    this.itemService.deleteItem(id).subscribe(
      response => {
        this.ngOnInit();
      }
    );
  }

  editItem(item: any): void {
    item.collectionId = this.collectionId;
    this.itemService.setItem(item);
    this.router.navigate(['/item/edit-item']);
  }

  linkSetItem(item: any): void {
    this.itemService.setItem(item);
  }

}
