import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ItemService } from '../_services/item.service';
import { UserRoleService } from '../_services/user-role.service';

@Component({
  selector: 'app-edit-item',
  templateUrl: './edit-item.component.html',
  styleUrls: ['./edit-item.component.css']
})
export class EditItemComponent implements OnInit {

  constructor(private userRoleService: UserRoleService, private router: Router, private itemService: ItemService) { }

  errorMessage = '';
  item: any;

  form: any = {
    name: null,
  }

  ngOnInit(): void {
    this.item = this.itemService.getItem();
    console.log(this.item.collectionId);
    this.form.name = this.item.name;
  }

  onSubmit(): void {
    console.log(this.item.collectionId)
    const { name } = this.form;
    this.itemService.editItem(this.item.id, name, this.item.collectionId).subscribe(
      response => {
        this.router.navigate(['/user/collection-page'], {queryParams: {'collectionId': this.item.collectionId}});
      },
      error => {
        this.errorMessage = error.message;
      }
    );
  }

}
