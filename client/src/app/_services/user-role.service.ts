import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Collection } from '../models/collection.model';

// const baseUrl = 'http://localhost:8080/api/test/user';
const baseUrl = 'https://ancient-coast-85609.herokuapp.com/api/test/user'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'} )
}

@Injectable({
  providedIn: 'root'
})
export class UserRoleService {

  currentCollection?: Collection;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Collection[]> {
    return this.http.get<Collection[]>(baseUrl + '/get_all');
  }

  getUserCollections(id: any): Observable<Collection[]> {
    return this.http.get<Collection[]>(`${baseUrl}/get_all_user_collections/${id}`);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  getAllTopics(): Observable<any> {
    return this.http.get<any>(baseUrl + '/get_all_topics');
  }

  saveCollection(userId: bigint, name: string, topic: any, description: string): Observable<any> {
    return this.http.post(baseUrl + '/save_collection', {
      userId,
      name,
      topic,
      description,
    }, httpOptions);
  }

  getCollectionById(id: any): Observable<Collection> {
    return this.http.get<Collection>(`${baseUrl}/get_collection_by_id/${id}`);
  }


  editCollection(idCollection: bigint, name: string, topic: any, description: string, userId: bigint): Observable<any> {
    return this.http.post(`${baseUrl}/edit_collection/${idCollection}`, {
      userId,
      name,
      topic,
      description,
    }, httpOptions);
  }

  setCollection(collection: Collection): void {
    this.currentCollection = collection;
  }

  getCollection(): Collection | undefined {
    return this.currentCollection;
  }
}
