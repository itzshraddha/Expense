import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './user';
import { Observable, of } from 'rxjs'


@Injectable()
export class UserService {

  private usersUrl: string;
  private headers= new HttpHeaders()
  .set('content-type', 'application/json')
  .set('Access-Control-Allow-Origin', '*');

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/api';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl+'/userDetails');
  }

  public save(user: User) {
    console.log("Enter into service");
    console.log(this.usersUrl+'/users');
    return this.http.post<User>(this.usersUrl+'/users', user, { 'headers': this.headers });
  }
}
