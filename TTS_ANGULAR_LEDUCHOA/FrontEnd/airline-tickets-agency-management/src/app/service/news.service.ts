
import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';
import {News} from '../model/news';
import {Category} from '../model/category';
import {environment} from '../../environments/environment';



import {TokenStorageService} from './auth/token-storage.service';
const API_URL = `${environment.apiUrl}`;

@Injectable({
  providedIn: 'root'
})
export class NewsService {



  constructor(private http: HttpClient) {
  }

  getAll(): Observable<News[]> {
    return this.http.get<News[]>(API_URL + '/news');
  }

  getAllCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(API_URL + '/news/category');
  }

  getCategoryById(id): Observable<Category> {
    return this.http.get<Category>(`${API_URL}/news/category/${id}`);
  }

  getById(id): Observable<News> {
    return this.http.get<News>(`${API_URL}/news/${id}`);
  }

  create(value): Observable<News> {
    return this.http.post<News>(API_URL + '/news', value);
  }

  remove(id) {
    return this.http.delete<News[]>(`${API_URL}/news/${id}`);
  }

  getImage(imageUrl: string): Observable<Blob> {
    return this.http.get(imageUrl, {responseType: 'blob'});
  }

  getAllNews(page: number): Observable<any> {
    return this.http.get<any>(API_URL + '/news/news-list?page=' + page);
  }

  getHotNews(): Observable<News[]> {
    return this.http.get<News[]>(API_URL + '/news/hot-news');
  }

  updateViews(id): Observable<News> {
    return this.http.get<News>(`${API_URL}/news/update-views/${id}`);
  }

  deleteNews(newsId: number) {
    return this.http.delete(`${API_URL}/news/news-delete/${newsId}`);
  }
  update(value, id) {
    return  this.http.put<News>(`${API_URL}/news/${id}`, value);
  }
}
