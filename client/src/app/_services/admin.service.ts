import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

// const baseUrl = 'http://localhost:8080/api/test/admin'
const baseUrl = 'https://ancient-coast-85609.herokuapp.com//api/test/admin'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'} )
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  userToEdit?: User;

  constructor(private http: HttpClient) { }

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(baseUrl + "/get_all");
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  addUser(username: string, email:string, password: string, roles: any[] | undefined): Observable<any> {
    return this.http.post(baseUrl + "/save_user", {
      username,
      email,
      password,
      roles
    }, httpOptions);
  }

  getAllRoles(): Observable<any> {
    return this.http.get<any>(baseUrl + "/get_all_roles");
  }

  getUserRoles(id: any): Observable<any> {
    return this.http.get<any>(baseUrl + `/get_user_roles/${id}`);
  }

  setUserToEdit(user: User): void {
      this.userToEdit = user;
  }

  getUserToEdit(): User | undefined {
    return this.userToEdit;
  }

  editUser(id: BigInteger, username: string, email: string, password: string, roles: any[] | undefined): Observable<any> {
    return this.http.post(baseUrl + "/edit_user", {
      id,
      username,
      email,
      password,
      roles
    }, httpOptions);
  }

}
