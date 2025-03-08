import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user.model';
import { environment } from 'src/environments/environment.prod';

const baseUrl = `${environment.baseURL}/api/users`; 

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {} 
  getAll(): Observable<User[]> { 
      return this.http.get<User[]>(`${baseUrl}/all`); 
  } 
  
  get(id: number): Observable<User> { 
      return this.http.get<User>(`${baseUrl}/${id}`); 
  } 
  
  create(owner: User): Observable<User> { 
      return this.http.post<User>(`${baseUrl}`, owner); 
  } 
  
  register(owner: User): Observable<User> { 
      return this.http.post<User>(`${baseUrl}/registrations`, owner); 
  } 
  
  update(id: number, owner: User): Observable<User> { 
      return this.http.put<User>(`${baseUrl}/${id}`, owner); 
  } 
  
  delete(id: number): Observable<void> { 
      return this.http.delete<void>(`${baseUrl}/${id}`); 
  } 
  
  getUsingKeycloakId(id: string): Observable<User> { 
      return this.http.get<User>(`${baseUrl}/keycloak/${id}`); 
  } 
  // updateOwnerPet(id: number, pet: Pet): Observable<void> { 
  // return this.http.put<void>(`${baseUrl}/pet/${id}`, pet); 
  // } 
  }
