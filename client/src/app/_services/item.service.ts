import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// const baseUrl = 'http://localhost:8080/api/test/item';
const baseUrl = 'https://ancient-coast-85609.herokuapp.com/api/test/item';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'} )
}

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  currentItem?: any;

  constructor(private http: HttpClient) { }

  saveItem(name: string, collectionId: bigint): Observable<any> {
    return this.http.post(baseUrl + '/save_item', {
      name,
      collectionId
    }, httpOptions);
  }

  deleteItem(id: bigint): Observable<any> {
    return this.http.delete(`${baseUrl}/delete_item/${id}`);
  }

  editItem(id: bigint, name: string, collectionId: bigint): Observable<any> {
    return this.http.post(`${baseUrl}/edit_item`, {
      id,
      name,
      collectionId
    }, httpOptions)
  }

  setItem(item: any): void {
    this.currentItem = item;
  }

  getItem(): any {
    return this.currentItem;
  }

  getItemById(idItem: bigint): Observable<any> {
    return this.http.get<any>(`${baseUrl}/get_item/${idItem}`);
  }

  saveComment(idItem: bigint, userFromId: bigint, message: string): Observable<any> {
    return this.http.post(`${baseUrl}/save_comment/${idItem}`, {
      message,
      userFromId
    }, httpOptions);
  }
}
