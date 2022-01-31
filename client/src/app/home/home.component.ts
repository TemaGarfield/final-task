import { Component, OnInit } from '@angular/core';
import { HomeService } from '../_services/home.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { UserService } from '../_services/user.service';
import { Collection } from '../models/collection.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  items?: any[];
  collections?: Collection[];

  constructor(private homeService: HomeService) { }

  ngOnInit(): void {

    this.homeService.getLastItems().subscribe(
      data => {
        this.items = data;
      }
    );

    this.homeService.getMaxItemsCollections().subscribe(
      data => {
        this.collections = data;
      }
    );
  }

}
