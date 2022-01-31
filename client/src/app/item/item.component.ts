import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../_services/item.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserRoleService } from '../_services/user-role.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  itemId?: bigint;
  item?: any;
  comments?: any[];
  form: any = {
    comment: null,
  }

  isAuthorized: boolean = false;

  constructor(private route: ActivatedRoute, private userRoleService: UserRoleService, private tokenStorageService: TokenStorageService, private itemService: ItemService) {}

  ngOnInit(): void {
    this.isAuthorized = (this.tokenStorageService.getToken() != null) ? true : false;

    this.route.queryParams.subscribe(params => {
      this.itemService.getItemById(params['itemId']).subscribe(
        data => {
          this.item = data;
          console.log(this.item.comments.length);
          console.log(this.item.comments);
          if (this.item.comments.length != 0) {
            this.comments = this.item.comments;
          }
        }
      );
    });
  }

  onSubmit(): void {
    let { comment } = this.form;
    console.log(this.tokenStorageService.getUser());
    this.itemService.saveComment(this.item.id, this.tokenStorageService.getUser().id, comment).subscribe(
      response => {
        this.ngOnInit();
      }
    );
  }

}
