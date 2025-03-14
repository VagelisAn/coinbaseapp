import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { News } from '../../models/news.model';

const baseUrl = `http://localhost:8081/api/news`;

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<News[]> {
    return this.http.get<News[]>(`${baseUrl}`);
  }
}
