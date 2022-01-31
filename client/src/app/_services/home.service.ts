import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection } from '../models/collection.model';

// const baseUrl = 'http://localhost:8080/api/test/home';
const baseUrl = 'https://ancient-coast-85609.herokuapp.com/api/test/home';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'} )
}

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  getLastItems(): Observable<any[]> {
    return this.http.get<any[]>(baseUrl + '/get_last_items');
  }

  getMaxItemsCollections(): Observable<Collection[]> {
    return this.http.get<Collection[]>(baseUrl + '/get_max_items_collections');
  }
}
