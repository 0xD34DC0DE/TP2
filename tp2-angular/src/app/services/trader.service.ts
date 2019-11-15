import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TraderService {

  constructor(private http: HttpClient) { }

  getProducts(id: string) {
    return this.http.get<Product>(`${environment.productsUrl}/${id}`);
  }

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${environment.productsUrl}/all`);
  }
}
